package com.elearning.VideoExternalService;

import com.elearning.VideoExternalService.Dtos.UpdateVideoExternalDto;
import com.elearning.VideoExternalService.Dtos.UploadTokenDto;
import com.elearning.VideoExternalService.Exceptions.ApiVideoExceptionTranslator;
import com.elearning.VideoExternalService.Exceptions.ApiVideoRateLimiterException;
import com.elearning.entities.video.Mappers.VideoMapper;
import com.elearning.entities.video.Mappers.VideoStatusMapper;
import com.elearning.entities.video.Video;
import com.elearning.entities.video.VideoAssets;
import com.elearning.entities.video.VideoStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import video.api.client.ApiVideoClient;
import video.api.client.api.ApiException;
import video.api.client.api.models.Metadata;
import video.api.client.api.models.TokenCreationPayload;
import video.api.client.api.models.VideoThumbnailPickPayload;
import video.api.client.api.models.VideoUpdatePayload;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.*;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ApiVideoImpl implements ApiVideoService , ApiVideoUtils {

    private final ApiVideoClient client;
    private final ApiVideoExceptionTranslator exceptionTranslator;
    private final VideoMapper videoMapper;
    private final VideoStatusMapper videoStatusMapper;
    private static final Pattern timeCodePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(\\.\\d{2})?");
    private static final Duration MIN_TTL_DURATION = Duration.ofMinutes(15);
    private static final Duration MAX_TTL_DURATION = Duration.ofMinutes(60);
    private final String iFrameLinkFormat = "<iframe src=\"https://embed.api.video/vod/%s\" width=\"100%\" height=\"100%\" frameborder=\"0\" scrolling=\"no\" allowfullscreen=\"\"></iframe>";
    private final String playerLinkFormat = "https://embed.api.video/vod/%s";
    private final String hlsLinkFormat = "https://cdn.api.video/vod/%s/hls/manifest.m3u8";
    private final String mp4LinkFormat = "https://cdn.api.video/vod/%s/mp4/source.mp4";
    private final String thumbnailLinkFormat = "https://cdn.api.video/vod/%s/thumbnail.jpg";
    public ApiVideoImpl(ApiVideoClient client, ApiVideoExceptionTranslator apiVideoExceptionTranslator, VideoMapper videoMapper, VideoStatusMapper videoStatusMapper){
        this.client = client;
        this.exceptionTranslator = apiVideoExceptionTranslator;
        this.videoMapper = videoMapper;
        this.videoStatusMapper = videoStatusMapper;
    }

    @Override
    public UploadTokenDto generateUploadToken(Duration ttl){
        Objects.requireNonNull(ttl);
        if(ttl.compareTo(Duration.ofMinutes(15)) < 0 || ttl.compareTo(Duration.ofHours(1)) > 0)
            throw new IllegalArgumentException("upload token ttl must >= 15 minutes and <= 1 hour");

        var payload = new TokenCreationPayload();
        payload.setTtl((int)ttl.toSeconds());
        try{
            var token = client.uploadTokens().createToken(payload);
            return UploadTokenDto.from(token);
        } catch (ApiException e) {
            throw exceptionTranslator.translate(e);
        }
    }

    @Override
    public void deleteVideo(String videoId) {
        Objects.requireNonNull(videoId);
        if(videoId.isBlank())
            throw new IllegalArgumentException("Blank video Id !!");
        try{
            client.videos().delete(videoId);
        } catch (ApiException e) {
            throw exceptionTranslator.translate(e);
        }
    }

    @Override
    public Video updateVideo(String videoId, UpdateVideoExternalDto updateDto) {
        Objects.requireNonNull(videoId);
        Objects.requireNonNull(updateDto);
        if(videoId.isBlank())
            throw new IllegalArgumentException("Blank video Id !!");
        VideoUpdatePayload updatePayload = new VideoUpdatePayload();
        updatePayload.setPlayerId(updateDto.getPlayerId());
        updatePayload.setDescription(updateDto.getDesc());
        updatePayload.setMp4Support(updateDto.getIsMp4Supported());
        updatePayload.setPublic(updateDto.getIsPublic());
        updatePayload.setTranscript(updateDto.getEnableTranscript());
        if(updateDto.getMetaData() != null && !updateDto.getMetaData().isEmpty())
        {
            List<Metadata> metadataList = new ArrayList<>(updateDto.getMetaData().size());
            for(Map.Entry<String,String> entry : updateDto.getMetaData().entrySet()){
                metadataList.add(new Metadata(entry.getKey(),entry.getValue()));
            }
            updatePayload.setMetadata(metadataList);
        }
        try{
            video.api.client.api.models.Video apiVideo = client.videos().update(videoId,updatePayload);
            return videoMapper.from(apiVideo);
        } catch (ApiException e) {
            throw exceptionTranslator.translate(e);
        }
    }

    @Override
    public Video getVideo(String videoId) {
        Objects.requireNonNull(videoId);
        if(videoId.isBlank())
            throw new IllegalArgumentException("Blank video Id !!");
        try{
            video.api.client.api.models.Video apiVideo = client.videos().get(videoId);
            return videoMapper.from(apiVideo);
        } catch (ApiException e) {
            throw exceptionTranslator.translate(e);
        }
    }

    @Override
    public Video getVideoWithStatus(String videoId) {
        Video video = getVideo(videoId);
        video.setVideoStatus(getVideoStatus(videoId));
        return video;
    }

    @Override
    public VideoAssets getVideoAssets(String videoId) {
        var apiVid = getVideo(videoId);
        return apiVid.getAssetsDto();
    }

    @Override
    public VideoAssets getVideoAssets(Video video) {
        Objects.requireNonNull(video);
        if(!video.getPublic())
            return getVideoAssets(video.getId());
        URI mp4 = null , hls  = null,player = null ;
        URI thumb = null; String iframe = null;
        try{
            if(video.getMp4Support()){
                mp4 = new URI(mp4LinkFormat.formatted(video.getId()));
            }
            hls = new URI(hlsLinkFormat.formatted(video.getId()));
            player = new URI(playerLinkFormat.formatted(video.getId()));
            thumb = new URI(thumbnailLinkFormat.formatted(video.getId()));

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        iframe = iFrameLinkFormat.formatted(video.getId());
        return VideoAssets.builder().iFrame(iframe).player(player).hls(hls).mp4(mp4).thumbnail(thumb).build();
    }

    @Override
    public void setThumbnail(String videoId, String timeCode) {
        Objects.requireNonNull(videoId);
        Objects.requireNonNull(timeCode);
        if(videoId.isBlank())
            throw new IllegalArgumentException("Blank video Id !!");
        if(timeCode.isBlank())
            throw new IllegalArgumentException("empty time code for thumbnail , video id:" + videoId);
        if(!timeCodePattern.matcher(timeCode).matches())
            throw new IllegalArgumentException("invalid pattern for thumbnail timecode , provided:" + timeCode + " allowed: hh:mm:ss.ms");

        VideoThumbnailPickPayload payload = new VideoThumbnailPickPayload();
        payload.setTimecode(timeCode);
        try{

            client.videos().pickThumbnail(videoId,payload);
        } catch (ApiException e) {
            throw  exceptionTranslator.translate(e);
        }
    }

    @Override
    public VideoStatus getVideoStatus(String videoId) {
        Objects.requireNonNull(videoId);
        if(videoId.isBlank())
            throw new IllegalArgumentException("Blank video Id !!");
        try{
            video.api.client.api.models.VideoStatus apiStat = client.videos().getStatus(videoId);
            return videoStatusMapper.from(apiStat);
        } catch (ApiException e) {
            throw exceptionTranslator.translate(e);
        }
    }

    @Override
    public <T> T rateLimitRetry(Supplier<T> action) {
        try{
            return action.get();
        }catch (ApiVideoRateLimiterException e){
            try {
                TimeUnit.SECONDS.sleep(e.getRetryAfterInSeconds());
            } catch (InterruptedException ex) {
                // should never be interrupted
                // clear flag
                Thread.currentThread().interrupt();
                log.error("unexpected interrupt for rate limit retry utils");
            }
            return action.get();
        }
    }

    @Override
    public  void rateLimitRetry(Runnable runnable) {
        try{
            runnable.run();
        }catch (ApiVideoRateLimiterException e){
            try {
                TimeUnit.SECONDS.sleep(e.getRetryAfterInSeconds());
            } catch (InterruptedException ex) {
                // should never be interrupted
                // clear flag
                Thread.currentThread().interrupt();
                log.error("unexpected interrupt for rate limit retry utils");
            }
            runnable.run();
        }
    }


}

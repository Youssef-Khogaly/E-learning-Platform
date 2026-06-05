package com.elearning.Videos;

import com.elearning.Exceptions.NotFoundException;
import com.elearning.UserEnroll.IUserEnrollmentService;
import com.elearning.External.VideoExternalService.ApiVideoService;
import com.elearning.External.VideoExternalService.ApiVideoUtils;
import com.elearning.External.VideoExternalService.Dtos.UploadTokenDto;
import com.elearning.External.VideoExternalService.Exceptions.ApiVideoNotFoundException;
import com.elearning.Videos.Requests.VideoUpdatePayload;
import com.elearning.entities.video.EnQuality;
import com.elearning.entities.video.Video;
import com.elearning.Videos.Dto.VideoAssets;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Optional;


@Service
@AllArgsConstructor
public class VideoService {

    private final ApiVideoService apiVideoService;
    private final VideoJpaRepo videoJpaRepo;
    private final ApiVideoUtils utils;
    private final IUserEnrollmentService userEnrollmentService;
    UploadTokenDto generateUploadToken(Duration ttl){

        return apiVideoService.generateUploadToken(ttl);
    }

    public Optional<Video> findByIdAndOwnerId(long ownerId, String videoId)
    {
        return videoJpaRepo.findByIdAndVideoOwner_Id(videoId,ownerId);
    }
    public Video findById(String videoId){
        return videoJpaRepo.findById(videoId).orElseThrow(() ->new NotFoundException("Video id does not exists , Id:" + videoId));
    }
    public Video save(Video video){
        return videoJpaRepo.save(video);
    }

    public boolean isExists(String vidId){
        return videoJpaRepo.existsById(vidId);
    }
    public boolean isExists(Long usrId , String vidId){
        return isOwner(usrId,vidId);
    }
    public void insertQuality(String vidId , EnQuality quality)
    {
        try{
            videoJpaRepo.insertQuality(vidId,"_"+quality.getVal());
        } catch (DataIntegrityViolationException ignore) {
        }

    }


    public boolean isOwner(Long usrId , String vidId){
        return videoJpaRepo.isOwner(usrId,vidId);
    }
    public VideoAssets getVideoAssets(String videoId){
        if(!videoJpaRepo.existsById(videoId))
            throw new NotFoundException("Invalid video id , id:" + videoId);
        return apiVideoService.getVideoAssets(videoId);
    }
    public VideoAssets getVideoAssets(Video video){
        return apiVideoService.getVideoAssets(video.getId());
    }
    @Transactional
    public void delete(final String videoId){
        if(!isExists(videoId))
            return;

        videoJpaRepo.deleteById(videoId);
        // handle it better way later
        // maybe create video delete event and delete it async
        try{
            utils.rateLimitRetry(() -> apiVideoService.deleteVideo(videoId));
        }catch (ApiVideoNotFoundException ignore){
            return;
        }
    }
    @Transactional
    public void delete(final Long usrId,final String videoId){
        if(!isExists(usrId, videoId))
            return;
        videoJpaRepo.deleteById(videoId);
        // handle it better way later
        // maybe create video delete event and delete it async
        try{
            utils.rateLimitRetry(() -> apiVideoService.deleteVideo(videoId));
        }catch (ApiVideoNotFoundException ignore){
            return;
        }
    }

    public Video update(String vidId,VideoUpdatePayload payload){

        Video video = findById(vidId);
        video.setTitle(payload.title());
        video.setMetaData(payload.metaData());
        return save(video);
    }

    public Page<Video> getVideos(Long usrId , int page ,int size ,EnVideoSortBy sortBy , EnSortDir dir)
    {
        var sort = Sort.by(dir.toDirection(),sortBy.toString());
        var req = PageRequest.of(page,size, sort);
        return videoJpaRepo.findAllByVideoOwner_Id(usrId,req);
    }

}

package com.elearning.webhook.apiVideo;

import com.elearning.Users.UserJpaRepo;
import com.elearning.VideoExternalService.ApiVideoService;
import com.elearning.VideoExternalService.ApiVideoUtils;
import com.elearning.VideoExternalService.Exceptions.ApiVideoException;
import com.elearning.Videos.VideoService;
import com.elearning.Videos.temporaryName.ItemporaryNameService;
import com.elearning.entities.video.Video;
import com.elearning.webhook.apiVideo.interfaces.IvideoEncodedWebhookHandler;
import com.elearning.webhook.apiVideo.interfaces.VideoInitializationLock;
import com.elearning.webhook.apiVideo.models.VideoHookQualityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import video.api.client.api.models.VideoUpdatePayload;

@Slf4j
@Service("qualityHookHandlerImpl")
public class VideoQualityHookHandlerImpl implements IvideoEncodedWebhookHandler {

    private final ApiVideoUtils apiVideoUtils;
    private final VideoService videoService;
    private final ItemporaryNameService itemporaryNameService;
    private final UserJpaRepo userJpaRepo;
    private final VideoInitializationLock videoInitializationLock;
    private final ApiVideoService apiVideoService;

    public VideoQualityHookHandlerImpl(ApiVideoUtils apiVideoUtils, VideoService videoService, ItemporaryNameService itemporaryNameService, UserJpaRepo userJpaRepo, VideoInitializationLock videoInitializationLock, ApiVideoService apiVideoService) {
        this.apiVideoUtils = apiVideoUtils;
        this.videoService = videoService;
        this.itemporaryNameService = itemporaryNameService;
        this.userJpaRepo = userJpaRepo;
        this.videoInitializationLock = videoInitializationLock;
        this.apiVideoService = apiVideoService;
    }


    private boolean insertQualityIfExists( VideoHookQualityEvent event)
    {
        try{
            if(videoService.isExists(event.videoId()))
            {
                // atomic , silent fail in case of duplicates
                videoService.insertQuality(event.videoId(),event.quality());
                return true;
            }
        }
        catch (RuntimeException e) {
            log.error("failed processing video webhook: {}  \n", event, e);
            throw e;
        }

        return false;
    }
    @Override
    public void handle(VideoHookQualityEvent event) {
        Video vid = null;
        if(insertQualityIfExists(event))
        {
            return;
        }
        boolean didIAcquire = false;
        try{
            didIAcquire = videoInitializationLock.tryAcquire(event.videoId());
            if (didIAcquire)
            {
                if(insertQualityIfExists(event))
                {
                    return;
                }else {
                    // should acquire lock to avoid double api call or double video write
                    var updatePayload = new VideoUpdatePayload();
                    updatePayload.setPublic(false);
                    vid = apiVideoUtils.rateLimitRetry(() -> apiVideoService.updateVideo(event.videoId(),updatePayload));
                    // get original name and owner
                    var orgName = itemporaryNameService.getOrignalFileName(vid.getTitle());
                    vid.setVideoOwner(userJpaRepo.getReferenceById(orgName.usrId()));
                    vid.setTitle(orgName.orgFileName());
                    videoService.save(vid);
                }

            }else {
                throw new CannotAcquireLockException("Can not acquire init lock");
            }

        } catch (ApiVideoException ex) {
            log.error("failed processing video webhook: {}", event, ex);
            throw  ex;
        }finally {
            // only thread that hold can release
            if(didIAcquire)
                videoInitializationLock.release(event.videoId());
        }

    }
}

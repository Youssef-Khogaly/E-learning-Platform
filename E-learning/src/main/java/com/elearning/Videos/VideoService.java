package com.elearning.Videos;

import com.elearning.Exceptions.NotFoundException;
import com.elearning.UserEnroll.IUserEnrollmentService;
import com.elearning.VideoExternalService.ApiVideoService;
import com.elearning.VideoExternalService.ApiVideoUtils;
import com.elearning.VideoExternalService.Dtos.UploadTokenDto;
import com.elearning.VideoExternalService.Exceptions.ApiVideoNotFoundException;
import com.elearning.Videos.Requests.VideoUpdatePayload;
import com.elearning.entities.video.Video;
import com.elearning.entities.video.VideoAssets;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;


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

    public Video findById(String videoId){
        return videoJpaRepo.findById(videoId).orElseThrow(() ->new NotFoundException("Video id does not exists , Id:" + videoId));
    }
    public Video save(Video video){
        return videoJpaRepo.save(video);
    }

    private boolean isExists(String vidId){
        return videoJpaRepo.existsById(vidId);
    }
    private boolean isExists(Long usrId , String vidId){
        return isOwner(usrId,vidId);
    }

    public boolean canUserWatch(Long usrId , String videoId){
        return videoJpaRepo.canUserWatch(videoId,usrId);
    }


    public boolean isOwner(Long usrId , String vidId){
        return videoJpaRepo.isOwner(usrId,vidId);
    }
    VideoAssets getVideoAssets(String videoId){
        if(!videoJpaRepo.existsById(videoId))
            throw new NotFoundException("Invalid vidoe id , id:" + videoId);
        return apiVideoService.getVideoAssets(videoId);
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

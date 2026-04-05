package com.elearning.VideoExternalService;


import com.elearning.VideoExternalService.Dtos.UpdateVideoExternalDto;
import com.elearning.VideoExternalService.Dtos.UploadTokenDto;
import com.elearning.entities.video.Video;
import com.elearning.entities.video.VideoAssets;
import com.elearning.entities.video.VideoStatus;

import java.time.Duration;

public interface ApiVideoService {

    UploadTokenDto generateUploadToken(Duration ttl);
    void deleteVideo(String videoId);
    // return video without video status
    Video updateVideo(String videoId , UpdateVideoExternalDto updateDto);
    // return video without video status
    Video getVideo(String videoId);

    Video getVideoWithStatus(String videoId);
    /*
        api call
     */
    VideoAssets getVideoAssets(String videoId);
    // if video is public no api call
    // but if video is private will call api.video to generate private links
    VideoAssets getVideoAssets(Video video);
    void setThumbnail(String videoId , String timeCode);
    VideoStatus getVideoStatus(String videoId);


}

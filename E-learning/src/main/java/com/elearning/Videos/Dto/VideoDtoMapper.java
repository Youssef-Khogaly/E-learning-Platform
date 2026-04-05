package com.elearning.Videos.Dto;

import com.elearning.entities.video.Video;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VideoDtoMapper {

    public VideoDto from(Video video){
        Objects.requireNonNull(video);
        Objects.requireNonNull(video.getVideoStatus());
        return VideoDto.builder().videoId(video.getId())
                .title(video.getTitle()).isMp4Support(video.getMp4Support())
                .metaData(video.getMetaData())
                .videoStatus(video.getVideoStatus())
                .createAt(video.getCreateAt()).updateAt(video.getUpdateAt()).build();
    }
}

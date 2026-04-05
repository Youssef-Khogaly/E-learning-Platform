package com.elearning.Videos.Dto;

import com.elearning.entities.video.VideoStatus;
import lombok.Builder;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Map;

@Builder
public record VideoDto(String videoId , String title , Boolean isMp4Support ,
                       Map<String,String> metaData , VideoStatus videoStatus , Instant createAt , Instant updateAt) {
}

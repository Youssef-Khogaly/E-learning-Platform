package com.elearning.entities.video.Mappers;

import com.elearning.entities.video.VideoTechnicalMetadata;
import org.springframework.stereotype.Component;
import video.api.client.api.models.VideoStatusEncodingMetadata;

@Component
public class TechnicalMetaDataMapper {


    public VideoTechnicalMetadata from (VideoStatusEncodingMetadata videoStatusEncodingMetadata){
        if(videoStatusEncodingMetadata == null)
            throw  new NullPointerException("can't map to TechnicalMetaDataMapper , videoStatusEncodingMetadata is null");
        return VideoTechnicalMetadata.builder()
                .width(videoStatusEncodingMetadata.getWidth())
                .height(videoStatusEncodingMetadata.getHeight()).
                bitrate(videoStatusEncodingMetadata.getBitrate().toBigIntegerExact().longValueExact())
                .duration(videoStatusEncodingMetadata.getDuration())
                .framerate(videoStatusEncodingMetadata.getFramerate())
                .samplerate(videoStatusEncodingMetadata.getSamplerate())
                .videoCodec(videoStatusEncodingMetadata.getVideoCodec())
                .audioCodec(videoStatusEncodingMetadata.getAudioCodec())
                .aspectRatio(videoStatusEncodingMetadata.getAspectRatio()).build();
    }
}

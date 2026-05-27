package com.elearning.External.VideoExternalService.Dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Builder
@Getter
public class UpdateVideoExternalDto {
    private String playerId;
    private String title;
    private String desc;
    private Boolean isPublic;
    private Boolean isMp4Supported;
    private Map<String,String> metaData;
    private Boolean enableTranscript;
}

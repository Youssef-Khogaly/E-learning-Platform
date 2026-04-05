package com.elearning.entities.video;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URI;

@Builder
@Getter
@Setter
public class VideoAssets {

    private String iFrame;
    private URI player;
    private URI hls;
    private URI mp4;
    private URI thumbnail;
}

package com.elearning.entities.video;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VideoTechnicalMetadata {
    private Integer width;
    private Integer height;
    private Long bitrate;
    private Integer duration;
    private Integer framerate;
    private Integer samplerate;
    @Column(name = "video_codec")
    private String videoCodec;
    @Column(name = "audio_codec")
    private String audioCodec;
    @Column(name = "aspect_ratio")
    private String aspectRatio;
}

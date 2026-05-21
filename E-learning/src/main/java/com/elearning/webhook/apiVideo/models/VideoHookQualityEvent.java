package com.elearning.webhook.apiVideo.models;

import com.elearning.entities.video.EnQuality;
import lombok.Builder;

import java.time.Instant;

@Builder
public record VideoHookQualityEvent(String webhookId,String type, Instant emittedAt, String videoId, String encoding, EnQuality quality) {

    @Override
    public String toString() {
        return "VideoHookQualityEvent{" +
                "webhookId='" + webhookId + '\'' +
                ", type='" + type + '\'' +
                ", videoId='" + videoId + '\'' +
                ", emittedAt=" + emittedAt +
                ", encoding='" + encoding + '\'' +
                ", quality=" + quality +
                '}';
    }
}

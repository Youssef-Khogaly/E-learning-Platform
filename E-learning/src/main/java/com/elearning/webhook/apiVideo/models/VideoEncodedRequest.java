package com.elearning.webhook.apiVideo.models;

import com.elearning.entities.video.EnQuality;
import lombok.Builder;

import java.time.Instant;

@Builder
public record VideoEncodedRequest(String type, Instant emittedAt, String videoId, String encoding, EnQuality quality){};
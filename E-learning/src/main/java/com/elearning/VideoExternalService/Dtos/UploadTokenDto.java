package com.elearning.VideoExternalService.Dtos;

import lombok.Builder;
import video.api.client.api.models.UploadToken;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@Builder
public record UploadTokenDto(String token , Duration ttl , Instant createAt , Instant expiresAt) {

    public static UploadTokenDto from(UploadToken uploadTokenApiVideo){
        if(uploadTokenApiVideo.getToken() == null)
            throw new NullPointerException("Upload token api.video is null");
        if(uploadTokenApiVideo.getTtl() == null)
            throw new NullPointerException("Upload token api.video ttl is null");
        if(uploadTokenApiVideo.getCreatedAt() == null)
            throw new NullPointerException("Upload token api.video create at is null");
        if(uploadTokenApiVideo.getExpiresAt() == null)
            throw new NullPointerException("Upload token api.video expires at is null");
        return UploadTokenDto.builder().token(uploadTokenApiVideo.getToken())
                .ttl(Duration.ofSeconds(uploadTokenApiVideo.getTtl()))
                .createAt(uploadTokenApiVideo.getCreatedAt().toInstant())
                .expiresAt(uploadTokenApiVideo.getExpiresAt().toInstant()).build();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UploadTokenDto that)) return false;
        return Objects.equals(token(), that.token());
    }

    @Override
    public int hashCode() {
        return Objects.hash(token());
    }
}

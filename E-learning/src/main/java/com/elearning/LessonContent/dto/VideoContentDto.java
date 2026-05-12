package com.elearning.LessonContent.dto;

import com.elearning.Videos.Dto.VideoAssets;
import com.elearning.entities.video.EnQuality;
import lombok.Builder;

import java.util.Collection;

@Builder
public record VideoContentDto(boolean isPlayable, Collection<EnQuality>qualities,VideoAssets assets) {
}

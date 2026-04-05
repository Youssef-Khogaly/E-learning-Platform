package com.elearning.entities.video.Mappers;


import com.elearning.entities.video.Video;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import video.api.client.api.models.Metadata;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class VideoMapper {

    private VideoAssetsMapper assetsDtoMapper;

    public Video from(video.api.client.api.models.Video video){
        return Video.builder().id(video.getVideoId())
                .title(video.getTitle())
                .isPublic(video.getPublic())
                .assetsDto(assetsDtoMapper.from(video.getAssets()))
                .metaData(video.getMetadata() == null ? null : video.getMetadata().stream().collect(
                        Collectors.toMap(Metadata::getKey,
                                Metadata::getValue)
                ))
                .isMp4Support(video.getMp4Support())
                .createAt(video.getCreatedAt() == null ? null : video.getCreatedAt().toInstant())
                .updateAt(video.getUpdatedAt() == null ? null : video.getUpdatedAt().toInstant())
                .build();


    }
}

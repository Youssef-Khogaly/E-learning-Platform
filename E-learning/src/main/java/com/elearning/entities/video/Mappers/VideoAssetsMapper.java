package com.elearning.entities.video.Mappers;

import com.elearning.entities.video.VideoAssets;
import org.springframework.stereotype.Component;

@Component
public class VideoAssetsMapper {

    public VideoAssets from(video.api.client.api.models.VideoAssets assets){
        if(assets == null)
                return  null;
        return VideoAssets.builder().iFrame(assets.getIframe()).player(assets.getPlayer()).hls(assets.getHls())
                .mp4(assets.getMp4()).thumbnail(assets.getThumbnail()).build();
    }
}

package com.elearning.entities.video.Mappers;


import com.elearning.entities.video.EnQuality;
import com.elearning.entities.video.EnVideoStatus;
import com.elearning.entities.video.VideoStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import video.api.client.api.models.Quality;

import java.util.Objects;
import java.util.stream.Collectors;

import static com.elearning.entities.video.EnVideoStatus.UPLOADING;

@AllArgsConstructor
@Component
public class VideoStatusMapper {

    private TechnicalMetaDataMapper technicalMetaDataMapper;

    public VideoStatus from(video.api.client.api.models.VideoStatus status){
        Objects.requireNonNull(status);
        EnVideoStatus s = null;
        switch (status.getIngest().getStatus())
        {
            case INGESTED -> s = EnVideoStatus.READY;
            case INGESTING -> s = EnVideoStatus.PROCESSING;
            case UPLOADING -> s = EnVideoStatus.UPLOADING;
            case UPLOADED -> s = EnVideoStatus.UPLOADED;
        }
        return VideoStatus.builder()
                .status(s).fileSize(status.getIngest().getFilesize())
                .isPlayable(status.getEncoding().getPlayable())
                .encodedQualities(status.getEncoding().getQualities()
                        .stream().filter(q -> q.getStatus() == Quality.StatusEnum.ENCODED).map(q -> EnQuality.from(q.getQuality())).collect(Collectors.toSet()))
                .techMetaData(technicalMetaDataMapper.from(status.getEncoding().getMetadata())).build();
    }
}

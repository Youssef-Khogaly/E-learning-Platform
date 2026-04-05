package com.elearning.entities.video;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class VideoStatus {
    @Enumerated(EnumType.STRING)
    private EnVideoStatus status;
    private Integer fileSize;
    private Boolean isPlayable;
    @ElementCollection(targetClass = EnQuality.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "video_encoded_qualities",
            joinColumns = @JoinColumn(name = "videoId")
    )
    @Column(name = "quality")
    private Set<EnQuality> encodedQualities = new HashSet<>();
    @Embedded
    private VideoTechnicalMetadata techMetaData;


    public void addQuality(EnQuality quality){
        if(encodedQualities.contains(quality))
            return;
        encodedQualities.add(quality);

    }
    public void removeQuality(EnQuality quality){
        encodedQualities.remove(quality);
    }
}

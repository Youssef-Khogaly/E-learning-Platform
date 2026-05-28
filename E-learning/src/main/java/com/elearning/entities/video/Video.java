package com.elearning.entities.video;

import com.elearning.Lessons.Lesson;
import com.elearning.Videos.Dto.VideoAssets;
import com.elearning.entities.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Persistable;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videos")
public class Video implements Persistable<String> {
    @Id
    private String id;
    private String title;
    private Boolean isPublic;
    private Boolean isMp4Support;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User  videoOwner;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String,String> metaData = new HashMap<>();
    @Transient
    @JsonIgnore
    private VideoAssets assetsDto;
    @Embedded
    private VideoStatus videoStatus;
    @Column(updatable = false)
    private Instant createAt;
    private Instant updateAt;
    public String addMetaData(String key , String value){
        return metaData.put(key,value);
    }
    public String removeMetaData(String key){
        return metaData.remove(key);
    }

    @Transient
    private boolean isNew = true;
    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PostPersist
    public void markOld(){
        isNew = false;
    }

    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getMp4Support() {
        return isMp4Support;
    }

    public void setMp4Support(Boolean mp4Support) {
        isMp4Support = mp4Support;
    }

    public User getVideoOwner() {
        return videoOwner;
    }

    public void setVideoOwner(User videoOwner) {
        this.videoOwner = videoOwner;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public VideoAssets getAssetsDto() {
        return assetsDto;
    }

    public void setAssetsDto(VideoAssets assetsDto) {
        this.assetsDto = assetsDto;
    }

    public VideoStatus getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(VideoStatus videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Video video)) return false;
        return Objects.equals(getId(), video.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}

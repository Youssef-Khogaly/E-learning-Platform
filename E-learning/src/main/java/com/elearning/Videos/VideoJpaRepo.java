package com.elearning.Videos;

import com.elearning.entities.video.EnQuality;
import com.elearning.entities.video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface VideoJpaRepo extends JpaRepository<Video,String> {

    @Query("select exists (select 1 from Video v where v.id = :vidId and v.videoOwner.id = :usrId)")
    boolean isOwner(Long usrId,String vidId);


    Optional<Video> findByIdAndVideoOwner_Id(String id, Long videoOwnerId);
    Page<Video> findAllByVideoOwner_Id(Long videoOwnerId , Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = """
        insert into video_encoded_qualities(videoId,quality) values (:vidId,:quality)
        """ , nativeQuery = true)
    void insertQuality(String vidId , String quality);


}

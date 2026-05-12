package com.elearning.Videos;

import com.elearning.entities.video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VideoJpaRepo extends JpaRepository<Video,String> {

    @Query("select l.isPreview from Video v right join Lesson l on l.video.id is not null  and v.id = l.video.id")
    boolean isPreview(String videoId);
    @Query("select exists (select 1 from Video v where v.id = :vidId and v.videoOwner.id = :usrId)")
    boolean isOwner(Long usrId,String vidId);

    @Query("""
        select count (v.id) > 0 from Video v left join v.lesson l left join l.section s left join s.course c where v.id = :vidId
                and (
                      l.isPreview or c.instructor.id = :usrId or 
                              exists (select 1 from UserEnrollment e where e.user.id = :usrId and e.course.id = c.id)  
                        )
        """)
    boolean canUserWatch(String vidId , Long usrId);

    Optional<Video> findByIdAndVideoOwner_Id(String id, Long videoOwnerId);
    Page<Video> findAllByVideoOwner_Id(Long videoOwnerId , Pageable pageable);
}

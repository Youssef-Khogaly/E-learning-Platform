package com.elearning.LessonContent;


import com.elearning.Exceptions.BadRequestException;
import com.elearning.Exceptions.NotFoundException;
import com.elearning.Exceptions.UnAuthorizedException;
import com.elearning.Lessons.*;
import com.elearning.Security.services.AuthenticationService;
import com.elearning.UserEnroll.UserEnrollmentService;
import com.elearning.Videos.VideoService;
import com.elearning.entities.LessonContent;
import com.elearning.entities.users.User;
import com.elearning.entities.users.UserRoles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class LessonContentService {

    private final LessonContentRepo lessonContentRepo;
    private final LessonAuthService lessonAuthService;
    private final LessonService lessonService;
    private final UserEnrollmentService userEnrollmentService;
    private final VideoService videoService;
    private final LessonStateValidator lessonStateValidator;
    private final int READING_SPEED = 100;
    // it fetch lesson, section, course together
    public LessonContent getContentOrThrow(long courseId,long sectionId,long lessonId)
    {
        return lessonContentRepo.findByIdAndLessonAndCourse(courseId,sectionId,lessonId).orElseThrow(() -> new NotFoundException("Lesson is not found"));
    }
    @Transactional(readOnly = true)
    public LessonContent findByIdAndCourseIdAndSectionId(long courseId,long sectionId,long lessonId)
    {
        var lesson = lessonService.getLessonOrThrow(courseId,sectionId,lessonId);
        var currentUser = AuthenticationService.getCurrentUser();
        boolean canAccess = currentUser.isPresent() && lessonAuthService.canRead(lesson.getSection().getCourse(),lesson,userEnrollmentService.findByUserAndCourse(currentUser.get().getId(), courseId).orElse(null));
        if(!canAccess)
        {
            throw new UnAuthorizedException("Not Authorized");
        }
        return lessonContentRepo.findById(lessonId).orElseThrow(() -> new NotFoundException("Lesson not found") );
    }
    @Transactional
    public void updateVideoContent(final long courseId,final long sectionId,final long lessonId,final String videoId)
    {
        var content = getContentOrThrow(courseId,sectionId,lessonId);
        var lesson = content.getLesson();
        var section = lesson.getSection();
        var course = section.getCourse();
        if(lesson.getType() != LessonType.VIDEO)
        {
            throw new BadRequestException("Invalid lesson content, must be video and can not change type");
        }
        lessonAuthService.canWriteOrThrow(course,lesson);
        lessonStateValidator.canUpdateContent(lesson);
        var currentUser = AuthenticationService.getCurrentUser(); // should never return null

        var newVid = videoService.findByIdAndOwnerId(currentUser.get().getId(), videoId).orElseThrow(() -> new BadRequestException("video does not exist in your videos"));
        content.setVideo(newVid);
        content.setDuration(newVid.getVideoStatus().getTechMetaData().getDuration());

        return;
    }
    @Transactional
    public void updateTxtContent(final long courseId,final long sectionId,final long lessonId,final String txt)
    {
        var content = getContentOrThrow(courseId,sectionId,lessonId);
        var lesson = content.getLesson();
        var section = lesson.getSection();
        var course = section.getCourse();
        if(lesson.getType() != LessonType.TXT)
        {
            throw new BadRequestException("Invalid lesson content, must be Txt and can not change type");
        }
        lessonAuthService.canWriteOrThrow(course,lesson);
        lessonStateValidator.canUpdateContent(lesson);
        long wordsCount = Arrays.stream(txt.split("//s+")).filter(String::isBlank).count();
        int newDuration = (int)((((double)wordsCount)/READING_SPEED)*60.0);
        content.setDuration(newDuration);
        content.setText(txt);
        return;
    }
}

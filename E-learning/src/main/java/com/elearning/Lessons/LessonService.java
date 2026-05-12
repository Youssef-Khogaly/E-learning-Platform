package com.elearning.Lessons;

import com.elearning.Courses.Course;
import com.elearning.Courses.CourseAuthorization;
import com.elearning.Courses.CourseService;
import com.elearning.Exceptions.BadRequestException;
import com.elearning.Exceptions.NotFoundException;
import com.elearning.Exceptions.UnAuthorizedException;
import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.mappers.LessonMapperResolver;
import com.elearning.Sections.SectionService;
import com.elearning.UserEnroll.UserEnrollmentService;
import com.elearning.entities.UserEnrollment;
import com.elearning.entities.users.User;
import com.elearning.entities.users.UserRoles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LessonService {
    private final LessonJpaRepo lessonJpaRepo;
    private final LessonAuthService lessonAuthService;
    private final LessonMapperResolver lessonMapperResolver;
    private final CourseService courseService;
    private final UserEnrollmentService userEnrollmentService;
    private final SectionService sectionService;
    private final CourseAuthorization courseAuthorization;
    private final LessonStateTransitionService lessonStateTransitionService;



    @Transactional(readOnly = true)
    public Collection<LessonDto> findAllForUser(Long courseId , Long sectionId)
    {
        Long userId = 1L; // get current user id from security context, null in case of  anonymous user
        UserEnrollment enroll = null;
        Course course = null;
        User usr = new User();
        usr.setId(1L);
        usr.setRole(UserRoles.Student);

        course = courseService.findById(courseId);

        // should not see anything about this course
        if(!courseAuthorization.canRead(usr,course))
            throw new UnAuthorizedException("Not Authorized");


        enroll = userEnrollmentService.findByUserAndCourse(userId,courseId).orElse(null);
        var lessons = lessonJpaRepo.findAllByCourseAndSection(courseId,sectionId);

        return lessonMapperResolver.resolve(usr,course,lessons,enroll);
    }


    public Lesson create(long courseId, long sectionId,String title,int index,boolean isPreview,LessonType type)
    {
        var section = sectionService.findById(sectionId);
        var course = section.getCourse();
        if(course.getId() != courseId)
            throw new NotFoundException("section with Id:" + sectionId +" not found in course with Id:" + courseId);
        // current user
        User currUsr = new User();
        currUsr.setId(1L);

        if(courseAuthorization.canWrite(currUsr,course)){
           var lesson = new Lesson();
           lesson.setSection(section);
           lesson.setIndex(index);
           lesson.setIsPreview(isPreview);
           lesson.setTitle(title);
           lesson.setState(LessonState.DRAFT);
           lesson.setType(type);
           return lessonJpaRepo.save(lesson);
        }
        throw new UnAuthorizedException("Not authorized");
    }

    @Transactional
    public Lesson update(long courseId, long sectionId,long lessonId,String title,int index,boolean isPreview)
    {
        var lesson = getLessonOrThrow(courseId,sectionId,lessonId);
        canWriteOrThrow(lesson);
        lesson.setTitle(title);
        lesson.setIsPreview(isPreview);
        lesson.setIndex(index);
        return lesson;
    }

    @Transactional
    public void delete(long courseId, long sectionId,long lessonId)
    {
        var lesson = getLessonOrThrow(courseId,sectionId,lessonId);
        canWriteOrThrow(lesson);
        if(lesson.getState() == LessonState.DRAFT) {
            lessonJpaRepo.delete(lesson);
            return;
        }

        throw new BadRequestException("Only draft lessons can be deleted");
    }
    @Transactional
    public Lesson publish(long courseId,long sectionId,long lessonId)
    {
        var lesson = getLessonOrThrow(courseId,sectionId,lessonId);
        canWriteOrThrow(lesson);
        return lessonStateTransitionService.publishLesson(lesson);
    }
    @Transactional
    public Lesson unpublish(long courseId,long sectionId,long lessonId)
    {
        var lesson = getLessonOrThrow(courseId,sectionId,lessonId);
        canWriteOrThrow(lesson);

        return lessonStateTransitionService.unPublishLesson(lesson);
    }

    public Lesson getLessonOrThrow(long courseId,long sectionId,long lessonId)
    {
        return lessonJpaRepo.findByWithSectionAndCourse(courseId,sectionId,lessonId).orElseThrow(() ->  new NotFoundException("lesson with id:" + lessonId +" is not found"));
    }
    private void canWriteOrThrow(Lesson lesson){
        // current user
        User currUsr = new User();
        currUsr.setId(1L);
        if(!lessonAuthService.canWrite(currUsr,lesson.getSection().getCourse(),lesson)){
            throw new UnAuthorizedException("Not Authorized");
        }
    }
}

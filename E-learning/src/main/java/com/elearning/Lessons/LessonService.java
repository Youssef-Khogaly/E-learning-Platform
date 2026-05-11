package com.elearning.Lessons;

import com.elearning.Courses.Course;
import com.elearning.Courses.CourseAuthorization;
import com.elearning.Courses.CourseService;
import com.elearning.Exceptions.NotFoundException;
import com.elearning.Exceptions.UnAuthorizedException;
import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.mappers.LessonMapperResolver;
import com.elearning.Lessons.mappers.LessonOwnerDtoMapper;
import com.elearning.Sections.SectionService;
import com.elearning.UserEnroll.UserEnrollmentService;
import com.elearning.entities.Section;
import com.elearning.entities.UserEnrollment;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LessonService {
    private final LessonJpaRepo lessonJpaRepo;
    private final LessonAuthService lessonAuthService;
    private final LessonMapperResolver lessonMapperResolver;
    private final CourseService courseService;
    private final UserEnrollmentService userEnrollmentService;
    private final LessonOwnerDtoMapper lessonOwnerDtoMapper;
    private final SectionService sectionService;
    private final CourseAuthorization courseAuthorization;
    public Collection<LessonDto> findAllForUser(Long courseId , Long sectionId)
    {
        Long userId = 1L; // get current user id from security context, null in case of  anonymous user
        UserEnrollment enroll = null;
        Course course = null;
        User usr = null;
        if(userId != null) // anonymous user
            enroll = userEnrollmentService.findByUserAndCourse(userId,courseId).orElse(null);

        var lessons = lessonJpaRepo.findAllByCourseAndSection(courseId,sectionId);
        // to avoid fetching course twice, since it is already fetched inside enroll entity
        if(enroll == null)
        {
            course = courseService.findById(courseId);
        }
        else {
            usr = enroll.getUser();
            course = enroll.getCourse();
        }
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
           lesson.setStatus(LessonStatus.DRAFT);
           lesson.setType(type);
           return lessonJpaRepo.save(lesson);
        }
        throw new UnAuthorizedException("Not authorized");
    }

    public Lesson update(long courseId, long sectionId,int lessonId,String title,int index,boolean isPreview,LessonType type)
    {
        var lesson = lessonJpaRepo.findByWithSectionAndCourse(courseId,sectionId,lessonId).orElseThrow(() ->  new NotFoundException("lesson with id:" + lessonId +" is not found"));;
        var course = lesson.getSection().getCourse();
        var section = lesson.getSection();
        // current user
        User currUsr = new User();
        currUsr.setId(1L);
        if(courseAuthorization.canWrite(currUsr,course))
        {
            lesson.setType(type);
            lesson.setTitle(title);
            lesson.setIsPreview(isPreview);
            lesson.setIndex(index);
            lessonJpaRepo.save(lesson);
        }
        throw new UnAuthorizedException("Not authorized");
    }

}

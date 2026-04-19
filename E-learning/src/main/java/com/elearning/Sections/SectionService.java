package com.elearning.Sections;

import com.elearning.Courses.Repo.CourseJpaRepo;
import com.elearning.Courses.CourseService;
import com.elearning.Exceptions.BadRequestException;
import com.elearning.Exceptions.NotAllowedOperation;
import com.elearning.Exceptions.NotFoundException;
import com.elearning.Exceptions.UnAuthorizedException;
import com.elearning.entities.Section;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SectionService {

    private final CourseService courseService;
    private final SectionJpaRepo sectionJpaRepo;
    private final CourseJpaRepo courseJpaRepo;
    private void courseExists(Long courseId){
        if(!courseService.isExists(courseId)){
            throw new NotFoundException("Course with id:" + courseId +" is not found");
        }
    }
    private void canEditCourse(Long usrId , Long courseId){
        if(!courseService.canEditCourse(usrId,courseId)){
            throw new UnAuthorizedException("Un authorized to edit this course");
        }
    }
    private void checkSectionIndex(Long courseId , Integer index)
    {
        if(existsByCourseIdAndSectionIdx(courseId,index))
            throw new BadRequestException("section index already exists");
    }
    public Collection<Section> getCourseSections(Long courseId)
    {
        courseExists(courseId);
        return sectionJpaRepo.findAllByCourse_Id(courseId);
    }
    public Section createSection(Long usrId , Long courseId,Integer index , String title){
        courseExists(courseId);
        // handle authorization better way later
        canEditCourse(usrId,courseId);
        checkSectionIndex(courseId,index);
        Section section = new Section();
        section.setCourse(courseJpaRepo.getReferenceById(courseId));
        section.setIndex(index);
        section.setTitle(title);
        return  sectionJpaRepo.save(section);
    }


    public boolean existsByCourseIdAndSectionIdx(Long courseId , Integer index)
    {
        return sectionJpaRepo.existsByCourse_IdAndIndex(courseId,index);
    }

    public Section updateSection(Long usrId ,Long courseId,Long sectionId,Integer index , String title){
        courseExists(courseId);
        canEditCourse(usrId,courseId);
        checkSectionIndex(courseId,index);
        var section = sectionJpaRepo.findById(sectionId).orElseThrow(() -> new NotFoundException("Section with id:" + sectionId + " does not exists"));
        section.setTitle(title);
        section.setIndex(index);
        return  sectionJpaRepo.save(section);
    }
    public boolean isEmptySection(Long sectionId){
        return sectionJpaRepo.isEmptySection(sectionId);
    }
    public boolean isSectionDeletable(Long sectionId)
    {
        return isEmptySection(sectionId);
    }
    public void deleteSection(Long usrId , Long courseId , Long sectionId){

        canEditCourse(usrId,courseId);
        if(isSectionDeletable(sectionId))
            sectionJpaRepo.deleteById(sectionId);
        else
            throw new NotAllowedOperation("Can not delete section that has lessons delete lessons first");

    }
}

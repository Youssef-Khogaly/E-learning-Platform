package com.elearning.Courses;

import com.elearning.Users.UserJpaRepo;
import com.elearning.Videos.EnSortDir;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@Validated
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;
    private final UserJpaRepo userJpaRepo;
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO>getCourse(@PathVariable @NotNull @Range(min = 1) Long courseId){
        var course = courseService.findById(courseId, CourseState.PUBLISHED);
        return ResponseEntity.ok(courseDtoMapper.form(course));
    }
    @GetMapping
    public ResponseEntity<Page<CourseDTO>>getCourses(
            @RequestParam(value = "p",required = false,defaultValue = "0")
            @Positive Integer page
            ,@RequestParam(value = "s",required = false , defaultValue = "20") @Range(min = 20,max = 50) Integer size
            ,@RequestParam(value = "sortBy" , defaultValue =  "price") EnCourseSortBy sortBy
            , @RequestParam(value = "direction" , defaultValue = "DES") EnSortDir dir
    ){
        var ret = courseService.findAll(CourseState.PUBLISHED,size,page,sortBy,dir).map(courseDtoMapper::form);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/mine")
    public ResponseEntity<Page<CourseDTO>>getCoursesForOwner(
            @RequestParam(value = "p",required = false,defaultValue = "0")
            @Positive Integer page
            , @RequestParam(value = "s",required = false , defaultValue = "20") @Range(min = 20,max = 50) Integer size
            , @RequestParam(value = "sortBy" , defaultValue =  "price") EnCourseSortBy sortBy
            , @RequestParam(value = "direction" , defaultValue = "DES") EnSortDir dir
    ){
        var ret = courseService.findAllforInstructor(1L,size,page,sortBy,dir).map(courseDtoMapper::form);
        return ResponseEntity.ok(ret);
    }


}

package com.elearning.Courses.Controller;

import com.elearning.Courses.*;
import com.elearning.Courses.Requests.CoursePostRequest;
import com.elearning.Courses.Requests.CoursePutRequest;
import com.elearning.Users.UserJpaRepo;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.users.User;
import com.elearning.util.Money;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@Validated
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;
    // student api
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO>getCourse(@PathVariable @NotNull @Range(min = 1) Long courseId){
        var course = courseService.findById(courseId, CourseState.PUBLISHED);

        return ResponseEntity.ok(courseDtoMapper.form(course));
    }
    // student api
    @GetMapping
    public ResponseEntity<Page<CourseDTO>>getCourses(
            @RequestParam(value = "p",required = false,defaultValue = "0")
            @PositiveOrZero Integer page
            ,@RequestParam(value = "s",required = false , defaultValue = "20") @Range(min = 20,max = 50) Integer size
            ,@RequestParam(value = "sortBy" , defaultValue =  "PRICE") EnCourseSortBy sortBy
            , @RequestParam(value = "direction" , defaultValue = "DES") EnSortDir dir
    ){
        var ret = courseService.findAll(CourseState.PUBLISHED,size,page,sortBy,dir).map(courseDtoMapper::form);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/mine")
    public ResponseEntity<Page<CourseDTO>>getCoursesForOwner(
            @RequestParam(value = "p",required = false,defaultValue = "0")
            @PositiveOrZero Integer page
            , @RequestParam(value = "s",required = false , defaultValue = "20") @Range(min = 20,max = 50) Integer size
            ,@RequestParam(value = "status" , required = false , defaultValue = "PUBLISHED") CourseState state
            , @RequestParam(value = "sortBy" , defaultValue =  "price") EnCourseSortBy sortBy
            , @RequestParam(value = "direction" , defaultValue = "DES") EnSortDir dir
    ){
        var ret = courseService.findAllForInstructorWithState(2L,state,size,page,sortBy,dir).map(courseDtoMapper::form);
        return ResponseEntity.ok(ret);
    }

    @PostMapping
    public ResponseEntity<Void> createCourse(@Valid @RequestBody CoursePostRequest coursePostRequest)
    {
        var course = courseService.create(coursePostRequest.title(),coursePostRequest.desc(),coursePostRequest.price());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@Valid @RequestBody CoursePutRequest coursePutRequest, @PathVariable @NotNull @Positive Long id)
    {
        var course = courseService.findById(id);
        course.setPrice(coursePutRequest.price());
        course.setDesc(coursePutRequest.desc());
        course.setTitle(coursePutRequest.title());
        var dto = courseDtoMapper.form(courseService.save(course));
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Void>publishCourse(@PathVariable @NotNull @Positive Long id)
    {
        courseService.updateState(id,CourseState.PUBLISHED);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}/unPublish")
    public ResponseEntity<Void>unPublishCourse(@PathVariable @NotNull @Positive Long id)
    {
        courseService.updateState(id,CourseState.UNPUBLISHED);
        return ResponseEntity.ok().build();
    }

}

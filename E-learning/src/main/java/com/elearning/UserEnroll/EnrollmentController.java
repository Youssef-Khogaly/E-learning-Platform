package com.elearning.UserEnroll;

import com.elearning.Courses.CourseDTO;
import com.elearning.Courses.EnCourseSortBy;
import com.elearning.Videos.EnSortDir;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my-courses")
@AllArgsConstructor
public class EnrollmentController {

    private final UserEnrollmentService userEnrollmentService;
    private final EnrollmentDtoMapper enrollmentDtoMapper;
    @GetMapping("/enrolled")
    public ResponseEntity<Page<EnrollmentDTO>> getCourses(
            @RequestParam(value = "p",required = false,defaultValue = "0")
            @Positive Integer page
            , @RequestParam(value = "s",required = false , defaultValue = "20") @Range(min = 5,max = 50) Integer size
            , @RequestParam(value = "sortBy" , defaultValue =  "enrollDate") EnEnrollmentSortBy sortBy
            , @RequestParam(value = "direction" , defaultValue = "DES") EnSortDir dir
    )
    {
        var enrollments = userEnrollmentService.findAllEnrollement(1L,size,page,sortBy,dir).map(enrollmentDtoMapper::from);

        return ResponseEntity.ok(enrollments);
    }
}

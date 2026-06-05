package com.elearning.UserEnroll;

import com.elearning.Security.services.AuthenticationService;
import com.elearning.Videos.EnSortDir;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class EnrollmentController {

    private final UserEnrollmentService userEnrollmentService;
    private final EnrollmentDtoMapper enrollmentDtoMapper;
    @GetMapping("/enrolled/")
    public ResponseEntity<Page<EnrollmentDTO>> getCourses(
            @RequestParam(value = "p",required = false,defaultValue = "0")
            @PositiveOrZero Integer page
            , @RequestParam(value = "s",required = false , defaultValue = "20") @Range(min = 5,max = 50) Integer size
            , @RequestParam(value = "sortBy" , defaultValue =  "EnrollDate") EnEnrollmentSortBy sortBy
            , @RequestParam(value = "direction" , defaultValue = "DES") EnSortDir dir
    )
    {
        var currentUsr = AuthenticationService.getCurrentUser();
        if(currentUsr.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var enrollments = userEnrollmentService.findAllEnrollement(currentUsr.get().getId(), size,page,sortBy,dir).map(enrollmentDtoMapper::from);

        return ResponseEntity.ok(enrollments);
    }
}

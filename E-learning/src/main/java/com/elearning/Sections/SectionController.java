package com.elearning.Sections;

import com.elearning.Sections.Requests.SectionPostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@Validated
public class SectionController {


    @GetMapping("/{courseId}/sections")
    public ResponseEntity<Void> getSections(@PathVariable @NotNull @Range(min = 1) Long courseId){
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{courseId}/sections")
    public ResponseEntity<Void>createSection(@PathVariable @NotNull @Range(min = 1) Long courseId , @Valid @NotNull @RequestBody SectionPostRequest request){
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{courseId}/sections/{sectionId}")
    public ResponseEntity<Void>updateSection(@PathVariable @NotNull @Range(min = 1) Long courseId ,@PathVariable @NotNull @Range(min = 1) Long sectionId  ,@RequestBody SectionPostRequest request){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{courseId}/sections/{sectionId}")
    public ResponseEntity<Void>deleteSection(@PathVariable @NotNull @Range(min = 1) Long courseId ,@PathVariable @NotNull @Range(min = 1) Long sectionId,@RequestBody SectionPostRequest request){
        return ResponseEntity.ok().build();
    }
}

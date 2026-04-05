package com.elearning.Lessons;

import com.elearning.Lessons.Requests.LessonPostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@Validated
public class LessonController {

    @GetMapping("/{courseId}/sections/{sectionId}/lessons")
    public ResponseEntity<Void> getLessons(@PathVariable @NotNull @Positive Long courseId, @PathVariable @NotNull @Positive Integer sectionId){
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{courseId}/sections/{sectionId}/lessons")
    public ResponseEntity<Void>createLesson(@PathVariable @NotNull @Positive Long courseId ,
                                            @PathVariable @NotNull @Positive Integer sectionId ,
                                            @RequestBody @Valid LessonPostRequest request){
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}")
    public ResponseEntity<Void>updateLesson(@PathVariable @NotNull @Positive Long courseId ,@PathVariable @NotNull @Positive Long sectionId ,
                                            @PathVariable @NotNull @Positive Integer lessonId ,
                                            @RequestBody @Valid LessonPostRequest request ){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}")
    public ResponseEntity<Void>deleteLesson(@PathVariable @NotNull @Positive Long courseId , @PathVariable @NotNull @Positive Long sectionId, @PathVariable @NotNull @Positive Integer lessonId){
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/watch-link")
    public ResponseEntity<Void>updateLesson(@PathVariable @NotNull @Positive Long courseId ,@PathVariable @NotNull @Positive Long sectionId ,
                                            @PathVariable @NotNull @Positive Integer lessonId){
        return ResponseEntity.ok().build();
    }

}

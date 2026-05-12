package com.elearning.Lessons;

import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.Requests.LessonPostRequest;
import com.elearning.Lessons.Requests.LessonPutRequest;
import com.elearning.Lessons.mappers.LessonOwnerDtoMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/courses")
@Validated
@AllArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final LessonOwnerDtoMapper lessonOwnerDtoMapper;
    @GetMapping("/{courseId}/sections/{sectionId}/lessons")
    public ResponseEntity<Collection<LessonDto>> getLessons(@PathVariable @NotNull @Positive Long courseId, @PathVariable @NotNull @Positive Long sectionId){

        var lessonsCollection = lessonService.findAllForUser(courseId,sectionId);

        return ResponseEntity.ok(lessonsCollection);
    }
    @PostMapping("/{courseId}/sections/{sectionId}/lessons")
    public ResponseEntity<Void>createLesson(@PathVariable @NotNull @Positive Long courseId ,
                                            @PathVariable @NotNull @Positive Long sectionId ,
                                            @RequestBody @Valid LessonPostRequest request){

        var lesson = lessonService.create(courseId,sectionId,request.title(),request.index(),request.isPreview(),request.type());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(lesson.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}")
    public ResponseEntity<LessonDto>updateLesson(@PathVariable @NotNull @Positive Long courseId ,@PathVariable @NotNull @Positive Long sectionId ,
                                            @PathVariable @NotNull @Positive Long lessonId ,
                                            @RequestBody @Valid LessonPutRequest request ){

        var lesson = lessonService.update(courseId,sectionId,lessonId,request.title(),request.index(),request.isPreview());
        return ResponseEntity.ok(lessonOwnerDtoMapper.from(lesson,true));
    }

    @PutMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/publish")
    public ResponseEntity<LessonDto>publishLesson(@PathVariable @NotNull @Positive Long courseId ,@PathVariable @NotNull @Positive Long sectionId ,
                                                 @PathVariable @NotNull @Positive Long lessonId){
        var lesson = lessonService.publish(courseId,sectionId,lessonId);
        return ResponseEntity.ok(lessonOwnerDtoMapper.from(lesson,true));
    }
    @PutMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/unpublish")
    public ResponseEntity<LessonDto>unpublishLesson(@PathVariable @NotNull @Positive Long courseId ,@PathVariable @NotNull @Positive Long sectionId ,
                                                 @PathVariable @NotNull @Positive Long lessonId){
        var lesson = lessonService.unpublish(courseId,sectionId,lessonId);
        return ResponseEntity.ok(lessonOwnerDtoMapper.from(lesson,true));
    }
    @DeleteMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}")
    public ResponseEntity<Void>deleteLesson(@PathVariable @NotNull @Positive Long courseId , @PathVariable @NotNull @Positive Long sectionId, @PathVariable @NotNull @Positive Long lessonId){

        lessonService.delete(courseId,sectionId,lessonId);
        return ResponseEntity.ok().build();
    }
}

package com.elearning.Sections;

import com.elearning.Sections.DTO.SectionDTO;
import com.elearning.Sections.DTO.SectionDtoMapper;
import com.elearning.Sections.Requests.SectionPostRequest;
import com.elearning.entities.Section;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Validated
public class SectionController {
    private final SectionService sectionService;
    private final SectionDtoMapper sectionDtoMapper;
    // public api
    @GetMapping("/{courseId}/sections")
    public ResponseEntity<List<SectionDTO>> getSections(@PathVariable @NotNull @Positive Long courseId){
        var sections = sectionService.getCourseSections(courseId).stream().map(sectionDtoMapper::from).toList();
        return ResponseEntity.ok().body(sections);
    }
    // // should make sure that the user is the course owner
    @PostMapping("/{courseId}/sections")
    public ResponseEntity<Void>createSection(@PathVariable @NotNull @Positive Long courseId
            , @Valid @NotNull @RequestBody SectionPostRequest request){
        var section = sectionService.createSection(courseId,request.index(),request.title());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    // should make sure that the user is the course owner
    @PutMapping("/{courseId}/sections/{sectionId}")
    public ResponseEntity<SectionDTO>updateSection(@PathVariable @NotNull @Positive Long courseId
            ,@PathVariable @NotNull @Positive Long sectionId  ,@RequestBody SectionPostRequest request){

        var ret = sectionDtoMapper.from(sectionService.updateSection(courseId,sectionId,request.index(),request.title()));
        return ResponseEntity.ok().body(ret);
    }
    // should make sure that the user is the course owner
    @DeleteMapping("/{courseId}/sections/{sectionId}")
    public ResponseEntity<Void>deleteSection(@PathVariable @NotNull @Positive Long courseId
            ,@PathVariable @NotNull @Positive Long sectionId){

        sectionService.deleteSection(courseId,sectionId);
        return ResponseEntity.ok().build();
    }
}

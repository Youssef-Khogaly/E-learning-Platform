package com.elearning.LessonContent;

import com.elearning.Exceptions.BadRequestException;
import com.elearning.LessonContent.Requests.PutContentReq;
import com.elearning.LessonContent.dto.LessonContentDto;
import com.elearning.LessonContent.dto.TxtContentDto;
import com.elearning.LessonContent.dto.VideoContentDto;
import com.elearning.Lessons.LessonAuthService;
import com.elearning.Lessons.LessonType;
import com.elearning.External.VideoExternalService.ApiVideoUtils;
import com.elearning.Videos.VideoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Validated
public class LessonContentController {

    private final LessonContentService lessonContentService;
    private final LessonAuthService lessonAuthService;
    private final VideoService videoService;
    private final ApiVideoUtils apiVideoUtils;
    @GetMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/content")
    public ResponseEntity<LessonContentDto> getContent(@PathVariable @NotNull @Positive Long courseId ,
                                                       @PathVariable @NotNull @Positive Long sectionId ,
                                                       @PathVariable @NotNull @Positive Long lessonId){
        var lessonContent = lessonContentService.findByIdAndCourseIdAndSectionId(courseId,sectionId,lessonId);
        Object content = null;
        if(lessonContent.getLesson().getType() == LessonType.TXT)
        {
            content = TxtContentDto.builder().text(lessonContent.getText()).build();
        }
        else if(lessonContent.getLesson().getType() == LessonType.VIDEO)
        {
            var vid = lessonContent.getVideo();
            if(vid != null)
            {
                var assets = apiVideoUtils.rateLimitRetry(() -> videoService.getVideoAssets(vid));
                content = VideoContentDto.builder().isPlayable(vid.getVideoStatus().getIsPlayable())
                        .qualities(vid.getVideoStatus().getEncodedQualities())
                        .assets(assets).build();
            }

        }else {
            throw new RuntimeException("Unexpected lesson type");
        }
        var dto = LessonContentDto.builder().id(lessonContent.getId())
                .duration(lessonContent.getDuration())
                .type(lessonContent.getLesson().getType())
                .content(content)
                .build();
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/content")
    public ResponseEntity<Void>putContent(@PathVariable @NotNull @Positive Long courseId ,
                                          @PathVariable @NotNull @Positive Long sectionId ,
                                          @PathVariable @NotNull @Positive Long lessonId,
                                          @RequestBody @Valid PutContentReq req
                                          )
    {
        if(req.type() == LessonType.TXT)
        {
            lessonContentService.updateTxtContent(courseId,sectionId,lessonId,req.content());
        }
        else if( req.type() == LessonType.VIDEO)
        {
            lessonContentService.updateVideoContent(courseId,sectionId,lessonId,req.content());
        }
        else {
            throw new BadRequestException("Unsupported lesson type");
        }

        return ResponseEntity.ok().build();
    }
}

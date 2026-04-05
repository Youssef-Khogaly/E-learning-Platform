package com.elearning.Videos;


import com.elearning.VideoExternalService.Dtos.UploadTokenDto;
import com.elearning.Videos.Dto.VideoDto;
import com.elearning.Videos.Dto.VideoDtoMapper;
import com.elearning.Videos.Requests.VideoUpdatePayload;
import com.elearning.Videos.temporaryName.GenerateTemporaryNameCommand;
import com.elearning.Videos.temporaryName.ItemporaryNameService;
import com.elearning.entities.video.Video;
import com.elearning.entities.video.VideoAssets;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.hibernate.query.SortDirection;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/videos")
@AllArgsConstructor
public class VideoController {

    private final ItemporaryNameService itemporaryNameService;
    private final VideoService videoService;
    private final VideoDtoMapper videoDtoMapper;
    // only instructors or admin
    @PostMapping("/upload-tokens")
    public ResponseEntity<UploadTokenDto>generateUploadToken(@RequestParam @NotNull @Range(min = 900 , max = 3600) Integer ttl){
        return ResponseEntity.ok(videoService.generateUploadToken(Duration.ofSeconds((long)ttl)));
    }
    // only instructors or admins
    @PostMapping("/tmp-name")
    public ResponseEntity<Map<String,String>>generateTemporaryFileName(@RequestParam("n") String orgFileName)
    {
        String tempName = itemporaryNameService.generateTempName(new GenerateTemporaryNameCommand(orgFileName,"1"));

        return ResponseEntity.ok(Map.of("temporaryFileName",tempName));
    }

    @GetMapping("/{vidId}/assets")
    public ResponseEntity<VideoAssets> getVideoAssets(@PathVariable @NotNull(message = "video id is null") @NotBlank(message = "blank video id")
                                                          String vidId){
        if(!videoService.canUserWatch(1L,vidId))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(videoService.getVideoAssets(vidId));
    }
    @PatchMapping("/{vidId}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable @NotNull(message = "video id is null") @NotBlank(message = "blank video id")
                                                      String vidId , @RequestBody @Valid VideoUpdatePayload payload){
        if(!videoService.isOwner(1L,vidId))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(videoDtoMapper.from(videoService.update(vidId,payload)));
    }
    @GetMapping
    public ResponseEntity<Page<VideoDto>>getVideos(@RequestParam(value = "p",required = false,defaultValue = "0")
                                                       @Positive Integer page
            , @RequestParam(value = "s",required = false , defaultValue = "20") @Range(min = 20,max = 50) Integer size
            , @RequestParam(value = "sortBy" , defaultValue =  "createAt") EnVideoSortBy sortBy
                                                   , @RequestParam(value = "direction" , defaultValue = "DES") EnSortDir dir){

        Page<Video> videoPage = videoService.getVideos(1L,page,size,sortBy,dir);

        Page<VideoDto> dtos = videoPage.map(videoDtoMapper::from);
        return ResponseEntity.ok(dtos);
    }
    @DeleteMapping("/{vidId}")
    public ResponseEntity<Void>deleteVideo(@PathVariable @NotNull(message = "video id is null") @NotBlank(message = "blank video id")
                                               String vidId )
    {
        videoService.delete(1L,vidId);
        return ResponseEntity.ok().build();
    }
}

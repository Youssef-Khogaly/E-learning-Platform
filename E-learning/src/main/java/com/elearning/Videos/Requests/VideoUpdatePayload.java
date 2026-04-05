package com.elearning.Videos.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record VideoUpdatePayload(@NotBlank(message = "blank video title is not allowed")
                                 @NotNull(message = "video title can not be null") String title
                                , @NotNull Map<String,String> metaData) {
}

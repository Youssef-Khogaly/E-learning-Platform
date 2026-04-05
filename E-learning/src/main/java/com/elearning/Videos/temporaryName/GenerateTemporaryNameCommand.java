package com.elearning.Videos.temporaryName;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record GenerateTemporaryNameCommand(
        @NotNull
        @Pattern(regexp = "/^(?=.{1,128}$)[a-zA-Z0-9](?:[ a-zA-Z0-9_-]*[a-zA-Z0-9])?$/" ,
                message = "Invalid file name. Use 1–128 characters: letters, numbers, spaces, underscore (_) or dash (-). Must start and end with a letter or number.")
        String orgFileName , String userId){
}

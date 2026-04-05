package com.elearning.Videos.temporaryName;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public interface ItemporaryNameService {


    static public record OrignalFileName(Long usrId , String orgFileName){};
    public String generateTempName(GenerateTemporaryNameCommand command);
    public OrignalFileName getOrignalFileName(String temporaryFileName);
}


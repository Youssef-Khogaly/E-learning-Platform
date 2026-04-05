package com.elearning.entities.video;


import lombok.Getter;

@Getter
public enum     EnVideoStatus {
    UPLOADING("uploading"),UPLOADED("uploaded" ),PROCESSING("processing"),READY("ready");
    private final String str;

    EnVideoStatus(String str) {

        this.str = str;
    }

}

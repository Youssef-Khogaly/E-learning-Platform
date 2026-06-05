package com.elearning.entities.video;


import lombok.Getter;

@Getter
public enum     EnVideoStatus {
    UPLOADING("UPLOADING"),UPLOADED("UPLOADED" ),PROCESSING("PROCESSING"),READY("READY");
    private final String str;

    EnVideoStatus(String str) {

        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}

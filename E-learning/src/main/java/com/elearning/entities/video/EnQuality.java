package com.elearning.entities.video;

public enum EnQuality {
    _360p("360p") , _480p("480p"),_720p("720p"),_1080p("1080p"),_2190p("2190p");
    String val;
    EnQuality(String str) {
    }

    @Override
    public String toString() {
        return this.val;
    }

}

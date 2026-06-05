package com.elearning.entities.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import video.api.client.api.models.Quality;

public enum EnQuality {
    _240p("240p"),_360p("360p") , _480p("480p"),_720p("720p"),_1080p("1080p"),_2160p("2160p");
    final String val;
    EnQuality(String str) {
        this.val = str;
    }


    @JsonValue
    public String getVal()
    {
        return val;
    }

    @JsonCreator
    public static EnQuality fromVal(String qalString)
    {
        for(EnQuality q : values())
        {
            if(q.getVal().equalsIgnoreCase(qalString))
                return q;
        }
        throw new IllegalArgumentException("Unknown Quality: " + qalString);
    }

    public static EnQuality from(Quality.QualityEnum apiQuality)
    {
        return switch (apiQuality)
        {
            case _240P ->  _240p;
            case _360P -> _360p;
            case _480P -> _480p;
            case _720P -> _720p;
            case _1080P -> _1080p;
            case _2160P -> _2160p;
        };
    }
    @Override
    public String toString() {
        return this.val;
    }

}

package com.elearning.entities.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnQuality {
    _240p("240p"),_360p("360p") , _480p("480p"),_720p("720p"),_1080p("1080p"),_2160p("2160p");
    String val;
    EnQuality(String str) {
        this.val = str;
    }


    @JsonValue
    public String getVal()
    {
        return val;
    }

    @JsonCreator
    public static EnQuality fromVal(String val)
    {
        for(EnQuality q : values())
        {
            if(q.getVal().equalsIgnoreCase(val))
                return q;
        }
        throw new IllegalArgumentException("Unknown Quality: " + val);
    }
    @Override
    public String toString() {
        return this.val;
    }

}

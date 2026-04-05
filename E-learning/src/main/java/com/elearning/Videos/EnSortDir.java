package com.elearning.Videos;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Sort;

public enum EnSortDir {
    ASC("ASC") , DES("DES");
    private String str;

    EnSortDir(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
    public SortDirection toSortDirection(){
        return switch (this){
            case ASC -> SortDirection.ASCENDING;
            case DES -> SortDirection.DESCENDING;
        };
    }
    public Sort.Direction toDirection(){
        return switch (this){
            case ASC -> Sort.Direction.ASC;
            case DES -> Sort.Direction.DESC;
        };
    }
}

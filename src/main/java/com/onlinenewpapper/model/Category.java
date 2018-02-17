package com.onlinenewpapper.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * Created by rbuga on 2/16/2018.
 */
@AllArgsConstructor
public enum Category {
    POLITICS("politics"),
    ECONOMY("economy"),
    HI_TECH("hi_tech"),
    ISRAEL("israel"),
    WORLD("world");

    private String nameCategory;


    @JsonValue
    public String getNameCategory() {
        return nameCategory;
    }

    @JsonCreator
    public static Category getByNameCategory(String nameCategory){
        if(nameCategory == null){
            return null;
        }
        for(Category category : values()){
            if(category.getNameCategory().equals(nameCategory)){
                return category;
            }
        }
        throw new IllegalArgumentException("Category not supported");
    }
}

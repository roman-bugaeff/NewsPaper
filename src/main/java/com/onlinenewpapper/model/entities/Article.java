package com.onlinenewpapper.model.entities;

import com.onlinenewpapper.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by rbuga on 2/17/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
    private Integer id;
    private String title;
    private String body;
    List<Category> categories;
    List<Comment> comments;
}

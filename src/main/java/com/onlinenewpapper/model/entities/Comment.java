package com.onlinenewpapper.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by rbuga on 2/17/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    private Integer id;
    private String body;
    private Date createdOn;
    private User createdBy;
}

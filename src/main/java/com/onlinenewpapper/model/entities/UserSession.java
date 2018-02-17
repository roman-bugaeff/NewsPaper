package com.onlinenewpapper.model.entities;

import lombok.*;

/**
 * Created by rbuga on 2/14/2018.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserSession {
    private String sessionId;
    private User user;
    private Boolean isValid;
}

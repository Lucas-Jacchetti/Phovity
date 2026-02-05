package com.lucasjacc.dev.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummaryDto {
    private Long id;
    private String username;
    private String profileImageUrl;
}
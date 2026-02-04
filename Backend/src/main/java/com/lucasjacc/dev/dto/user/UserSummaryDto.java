package com.lucasjacc.dev.dto.user;

import lombok.Data;

@Data
public class UserSummaryDto {
    private Long id;
    private String username;
    private String profileImageUrl;
}
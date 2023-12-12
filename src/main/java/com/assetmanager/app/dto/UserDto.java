package com.assetmanager.app.dto;

import com.assetmanager.app.model.entity.UserRole;

public record UserDto(String username, String email, UserRole role) {
}

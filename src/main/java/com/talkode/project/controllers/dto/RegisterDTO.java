package com.talkode.project.controllers.dto;

import com.talkode.project.entities.enums.Role;

public record RegisterDTO(String name, String username, String password) {
}

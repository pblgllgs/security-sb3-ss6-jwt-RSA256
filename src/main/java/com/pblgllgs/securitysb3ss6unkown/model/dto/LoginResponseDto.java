package com.pblgllgs.securitysb3ss6unkown.model.dto;

import com.pblgllgs.securitysb3ss6unkown.model.entity.ApplicationUser;

public record LoginResponseDto(ApplicationUser user, String jwt) {
}

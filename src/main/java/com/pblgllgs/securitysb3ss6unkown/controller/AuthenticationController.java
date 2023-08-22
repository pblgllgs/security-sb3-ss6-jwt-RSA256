package com.pblgllgs.securitysb3ss6unkown.controller;

import com.pblgllgs.securitysb3ss6unkown.model.dto.AuthenticationRequestDto;
import com.pblgllgs.securitysb3ss6unkown.model.dto.LoginResponseDto;
import com.pblgllgs.securitysb3ss6unkown.model.dto.RegistrationDto;
import com.pblgllgs.securitysb3ss6unkown.model.entity.ApplicationUser;
import com.pblgllgs.securitysb3ss6unkown.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDto registrationDto) throws RoleNotFoundException {
        return authenticationService.registerUser(registrationDto.username(),registrationDto.password());
    }

    @PostMapping("/authenticate")
    public LoginResponseDto authenticate(@RequestBody AuthenticationRequestDto authenticationDto){
        return authenticationService.authenticate(authenticationDto.username(), authenticationDto.password());
    }
}

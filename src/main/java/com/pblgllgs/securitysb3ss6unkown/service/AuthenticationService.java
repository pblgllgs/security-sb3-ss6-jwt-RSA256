package com.pblgllgs.securitysb3ss6unkown.service;

import com.pblgllgs.securitysb3ss6unkown.model.dto.LoginResponseDto;
import com.pblgllgs.securitysb3ss6unkown.model.entity.ApplicationUser;
import com.pblgllgs.securitysb3ss6unkown.model.entity.Role;
import com.pblgllgs.securitysb3ss6unkown.repository.RoleRepository;
import com.pblgllgs.securitysb3ss6unkown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ApplicationUser registerUser(String username, String password) throws RoleNotFoundException {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> userRole = roleRepository.findByAuthority("USER");
        if (userRole.isEmpty()) {
            throw new RoleNotFoundException("Role not Found");
        }
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole.get());
        return userRepository.save(new ApplicationUser(username, encodedPassword, authorities));
    }

    public LoginResponseDto authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(authentication);
            Optional<ApplicationUser> user= userRepository.findByUsername(username);
            if (user.isEmpty()){
                throw new UsernameNotFoundException("User not found");
            }
            return new LoginResponseDto(user.get(),token);
        }catch (AuthenticationException e){
            return new LoginResponseDto(null,"");
        }
    }
}

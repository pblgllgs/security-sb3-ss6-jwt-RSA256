package com.pblgllgs.securitysb3ss6unkown;

import com.pblgllgs.securitysb3ss6unkown.model.entity.ApplicationUser;
import com.pblgllgs.securitysb3ss6unkown.model.entity.Role;
import com.pblgllgs.securitysb3ss6unkown.repository.RoleRepository;
import com.pblgllgs.securitysb3ss6unkown.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SecuritySb3Ss6UnkownApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritySb3Ss6UnkownApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if(roleRepository.findByAuthority("ADMIN").isEmpty()){
                Role adminRole = roleRepository.save(new Role("ADMIN"));
                Role userRole = roleRepository.save(new Role("USER"));

                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                ApplicationUser admin = new ApplicationUser(1,"admin",passwordEncoder.encode("password"),roles);
                userRepository.save(admin);
            }

        };
    }


}

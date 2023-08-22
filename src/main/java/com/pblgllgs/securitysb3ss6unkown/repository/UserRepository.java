package com.pblgllgs.securitysb3ss6unkown.repository;

import com.pblgllgs.securitysb3ss6unkown.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {
    Optional<ApplicationUser> findByUsername(String email);
}

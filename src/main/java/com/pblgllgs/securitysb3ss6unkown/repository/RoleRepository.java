package com.pblgllgs.securitysb3ss6unkown.repository;

import com.pblgllgs.securitysb3ss6unkown.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByAuthority(String authority);
}

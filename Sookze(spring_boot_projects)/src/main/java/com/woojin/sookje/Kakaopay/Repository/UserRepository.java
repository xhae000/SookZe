package com.woojin.sookje.Kakaopay.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.woojin.sookje.Kakaopay.Entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @EntityGraph(attributePaths = "authority")
    public Optional<UserEntity> findOneWithAuthorityByUsername(String username);
}

package com.woojin.sookje.Kakaopay.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woojin.sookje.Kakaopay.Entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @EntityGraph(attributePaths = "authority")
    public Optional<UserEntity> findOneWithAuthorityByUsername(String username);

    @Query(value = "select u.id from user_ u where u.username = :username", nativeQuery = true)
    public Optional<Long> findUserIdByUsername(@Param("username") String username);

    @Query(value = "select u.username from user_ u where u.id = :id", nativeQuery = true)
    public String findUsernameById(Long id);
}

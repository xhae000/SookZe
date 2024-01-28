package com.woojin.sookje.Kakaopay.Entity;

import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Enum.UserDtoType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "user")
public class UserEntity {

    // public UserEntity(UserDto userDto, UserDtoType type){
    //     switch (type) {
    //         case SIGN_UP:
    //             this.username = userDto.getUsername();
    //             this.password = userDto.getPassword();
    //             this.isActivated = true;
    //             this.authorities = Collections.singleton(
    //                 AuthorityEntity.builder()
    //                 .authorityName("ROLE_USER")
    //                 .build()
    //             );     

    //             break;
    //     }
    // }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, length = 256)
    private Long id;

    @Column(name = "username", nullable = false, length = 256)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    @JsonIgnore
    private String password;

    @JsonIgnore
    @Column(name = "isActivated")
    private boolean isActivated;

    @ManyToMany
    @JoinTable( // JoinTable은 테이블과 테이블 사이에 별도의 조인 테이블을 만들어 양 테이블간의 연관관계를 설정 하는 방법
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority", referencedColumnName = "authority_name")})
    private Set<AuthorityEntity> authorities;
}

package com.woojin.sookje.Kakaopay.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.woojin.sookje.Kakaopay.Entity.UserEntity;
import com.woojin.sookje.Kakaopay.Enum.UserEntityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    public UserDto(UserEntity userEntity, UserEntityType type){
        switch (type) {
            case NORMAL:
                this.username = userEntity.getUsername();
                break;
        }
    }

    private Long id;
    private String username;
    // 직렬화(java to json)만 허용  
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}

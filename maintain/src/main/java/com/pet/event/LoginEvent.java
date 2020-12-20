package com.pet.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginEvent {
    private String userName;
    private String pwd;
    private String nickName;
}

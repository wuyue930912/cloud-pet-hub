package com.pet.dto;

import lombok.Data;

@Data
public class AddUserDTO {
    private String userName;
    private String userPwd;
    private String userPhone;
    private String userEmail;
}

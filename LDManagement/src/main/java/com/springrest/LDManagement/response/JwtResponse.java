package com.springrest.LDManagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {

    private String jwtToken;
    private String userRole;
    private Long userId;

}

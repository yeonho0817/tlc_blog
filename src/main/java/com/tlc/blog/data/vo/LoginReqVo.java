package com.tlc.blog.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqVo {
    private String userId;
    private String userPw;
}

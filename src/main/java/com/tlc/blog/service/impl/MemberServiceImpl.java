package com.tlc.blog.service.impl;

import com.tlc.blog.data.entity.Member;
import com.tlc.blog.data.enumerate.Authorization;
import com.tlc.blog.data.repository.MemberRepository;
import com.tlc.blog.data.vo.LoginReqVo;
import com.tlc.blog.data.vo.SignUpReqVo;
import com.tlc.blog.error.Error;
import com.tlc.blog.error.ErrorSpec;
import com.tlc.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public void login(LoginReqVo loginReqVo) {
        Member member = memberRepository.findByUserIdAndUserPwAndDeleted(loginReqVo.getUserId(), loginReqVo.getUserPw(), false)
                .orElseThrow(() -> {
                    throw Error.of(ErrorSpec.LoginFailed);
                });
        log.info("id: {}, userId: {}님 로그인 성공", member.getId(), member.getUserId());
    }

    @Transactional
    @Override
    public void signUp(SignUpReqVo signUpReqVo) {
        Member member = new Member(signUpReqVo.getUserId(), signUpReqVo.getUserPw(), Authorization.ROLE_USER, false);
        memberRepository.save(member);
    }

    @Override
    public void list() {

    }

    @Override
    public void delete() {

    }
}

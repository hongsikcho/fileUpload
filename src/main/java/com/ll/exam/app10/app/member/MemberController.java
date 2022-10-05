package com.ll.exam.app10.app.member;


import com.ll.exam.app10.app.fileUpload.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String showJoin(){
        return "member/join";
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(String username , String password, String email, MultipartFile profileImg){
        Member oldMember = memberService.getMemberByUsername(username);

        if(oldMember != null)
            return "이미 가입된 회원입니다.";

        Member member = memberService.create(username, password, email, profileImg);

        return "가입완료";
    }
}

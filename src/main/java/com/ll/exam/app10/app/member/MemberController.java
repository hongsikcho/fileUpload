package com.ll.exam.app10.app.member;


import com.ll.exam.app10.app.fileUpload.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


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
    public String join(String username , String password, String email, MultipartFile profileImg , HttpSession session){
        Member oldMember = memberService.getMemberByUsername(username);

        if(oldMember != null)
            return "redirect:/?errorMsg=alreadyLogin ";

        Member member = memberService.create(username, password, email, profileImg);

        session.setAttribute("loginMemberId",member.getId());

        return "redirect:/member/profile";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model){
        Long loginedMemberId = (Long) session.getAttribute("loginMemberId");
        boolean isLogined = loginedMemberId == null;

        if(isLogined == true)
            return "redirect:/?errorMsg=needToLogin ";

        Member member = memberService.getMemberById(loginedMemberId);
        model.addAttribute("loginMember",member);
        return "member/profile";
    }
}

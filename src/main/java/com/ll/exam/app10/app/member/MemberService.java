package com.ll.exam.app10.app.member;

import com.ll.exam.app10.app.fileUpload.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;
    private final MemberRepository memberRepository;

    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    public Member create(String username, String password, String email, MultipartFile profileImg) {
        String profileImgRelPath = "member/" + UUID.randomUUID().toString() + ".png";
        File profileImgFile = new File(genFileDirPath + profileImgRelPath);

        profileImgFile.mkdirs();

        try {
            profileImg.transferTo(profileImgFile);
        } catch (Exception e) {
            throw new RuntimeException("안돼");
        }

        Member member = Member.builder()
                .username(username)
                .email(email)
                .password(password)
                .profileImg(profileImgRelPath)
                .build();
        memberRepository.save(member);
        return member;
    }
}

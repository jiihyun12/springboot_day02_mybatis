package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class MapperTest {

    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberVO memberVO;

    /* @Test
     public void getTimeTest() {
         log.info( "{}", timeMapper.getTime());
     }
     */
    @Test
    public void getTimeTest() {
        log.info("--------------------");
        log.info( "{}", timeMapper.getTime2());
        log.info("--------------------");

    }

    @Test
    public void memberInsertTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("ksh1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("홍길동");

        memberMapper.insert(memberVO);
    }

    @Test
    public void selectTimeTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("hjh1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("함지현");

        Optional<MemberVO> foundMember = memberMapper.select(memberVO);

        foundMember.ifPresent(member -> {
        log.info("{}", member);
        });
        foundMember.ifPresent((MemberVO member)->{
            log.info("{}", member);
        });
    }

    @Test
    public void selectAllTest(){
        memberMapper.selectAll().forEach((MemberVO memberVO) -> {
            log.info("{}", memberVO);
        });
    }

    @Test
    public void updateTest() {

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("hjh1234@gmail.com");
        memberVO.setMemberPassword("5678");

        // 회원 조회 먼저 한 후 -> 회원수정
        memberVO.setMemberEmail("hjh1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO).ifPresent((MemberVO member) -> {
            //회원수정
            member.setMemberName("강감찬");
            memberMapper.update(member);
        });
    }

    @Test
    public void deleteTest() {
        // 회원 조회
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("hjh1234@gmail.com");
        memberVO.setMemberPassword("5678");
        memberMapper.select(memberVO).map(MemberVO::getId).ifPresent( (Long id)->{

            //회원탈퇴
            /*MemberMapper.delete(id);*/
        });
    }

}



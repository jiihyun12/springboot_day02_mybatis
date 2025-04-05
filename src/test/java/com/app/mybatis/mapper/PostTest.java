package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import com.app.mybatis.domain.PostDTO;
import com.app.mybatis.domain.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@SpringBootTest
public class PostTest {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insert() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("hjh1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO)
                .map(MemberVO::getId)
                .ifPresent((Long memberId) -> {
                    PostVO postVO = new PostVO();
                    postVO.setMemberId(memberId);
                    postVO.setPostContent("포스트글자3");
                    postVO.setPostTitle("포스트 제목3");

                    postMapper.insert(postVO);
                });
    }

    @Test
    public void selectAllTest() {
        List<PostDTO> posts = postMapper.selectAll();
//       posts.forEach(postDTO -> {
//            log.info("{}", postDTO);
//        });

        posts.stream().map(PostDTO::toString).forEach(log::info);

    }

    @Test
    public void selectTest() {
        postMapper.select(2L).map(PostDTO::toString).ifPresent(log::info);
    }

    @Test
    public void updateTest() {
//        1. select 정보를 들고온다
        postMapper.select(2L).ifPresent((postDTO) -> {
//        2. VO 가져온다
            PostVO postVO = new PostVO();
//        3. 수정한다.
            postDTO.setPostTitle("수정된 제목2");
            postDTO.setPostContent("수정된 내용2");

//        4. 업데이터 쿼리 날린다.
            postVO.setId(postDTO.getId());
            postVO.setPostTitle(postDTO.getPostTitle());
            postVO.setPostContent(postDTO.getPostContent());
            postMapper.update(postVO);

        });
    }


    @Test
    public void deleteTest() {
        postMapper.select(2L).map(PostDTO::getId)
                .ifPresent(postMapper::delete);
    }


    // 한 사람이 50개 글을 작성하도록 코딩
    @Test
    public void insertPostTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("ksh1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO)
                .map(MemberVO::getId)
                .ifPresent((Long memberId) -> {
                    for (int i = 0; i < 50; i++) {
                        PostVO postVO = new PostVO();
                        postVO.setMemberId(memberId);
                        postVO.setPostTitle("테스트 작성글" + (i + 1));
                        postVO.setPostContent("테스트 컨텐츠" + (i + 1));

                        postMapper.insert(postVO);
                    }
                });

    }

    @Test
    public void updateReadCountTest() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            Long id = Long.valueOf(random.nextInt(51));
            postMapper.select(id)
                    .map(PostDTO::getId)
                    .ifPresent(postMapper::updateReadCount);
        }
    }

    // 동적 쿼리 정렬
    // 아무것도 전달하지 않을 때
    @Test
    public void selectWithOrderTest() {
        String order = null;
        if (order == null) {
            order = "";
        }
        postMapper.selectAllWithOrder(order)
                .stream().map(PostVO::toString).forEach(log::info);
    }


    //postMApper에 동적쿼리 추가하기
    // 만약 ORDER가 "asc"라면 오름차순으로 정렬하기;


    @Test
    public void selectWithParamsTest() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("order", "popular");
        params.put("cursor", 1);
        params.put("derection", "desc");

        postMapper.selectAllWithLimit(params)
                .stream().map(PostVO::toString).forEach(log::info);


    }

}




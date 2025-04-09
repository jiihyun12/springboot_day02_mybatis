package com.app.mybatis.mapper;

import com.app.mybatis.domain.PostDTO;
import com.app.mybatis.domain.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    // 게시글 작성
    public void insert(PostVO postVO);

    // 게시글 목록
    public List<PostDTO> selectAll();

    // 게시글 조회(단일)
    public Optional<PostDTO> select(Long id);

    // 게시글 수정
    public void update(PostVO postVO);

    // 게시글 삭제
    public void delete(Long id);

    // 조회수 추가
    public void updateReadCount(Long id);


    // 게시글 목록(정렬)
    // 상황마다 다르나 order로 처리
    public List<PostVO> selectAllWithOrder(String order);


}

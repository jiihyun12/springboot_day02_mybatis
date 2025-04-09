package com.app.mybatis.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PostVO {
   private Long id;
   private String postTitle;
   private String postContent;
   private Long memberId;
   private Long postReadCount;
}

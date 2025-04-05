package com.app.mybatis.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping ("/test/*") // FC 역할을 해주는중
public class TestController {

//    test/ex
//    Get방식
    @GetMapping("ex") // 요청 경로
//    Post방식
//    @PostMapping
    public String ex(){ // 메서드가 처리
        return "first"; // 응답 경로

        // view resolver가 first를 들고온다. 근데 templates까지의 경로 + 확장자까지 붙여서 갖고온다.
        // first.html을 만든다.
    }

    @GetMapping("/ex01")
    public void ex01(HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello World");
    }

}

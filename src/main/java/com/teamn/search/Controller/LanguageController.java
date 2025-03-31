package com.teamn.search.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LanguageController {

    //ajax를 이용해서 언어변경후 현재페이지에 적용
    @GetMapping("changeLang")
    public @ResponseBody boolean changeLang() {

        return true;
    }

    //언어변경을 적용 후 다른 페이지로 이동할때(a href로 활용)
    @GetMapping("/setLanguage")
    public String setLanguage(@RequestParam("lang") String lang) {

        return "redirect:/";
    }

}

package com.teamn.search.Controller;

import com.teamn.search.DTO.CreateDTO;
import com.teamn.search.DTO.DetailDTO;
import com.teamn.search.DTO.ListDTO;
import com.teamn.search.Service.SearchService;
import com.teamn.search.Util.PagenationUtil;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.teamn.search.Util.PagenationUtil.Pagination;


@Slf4j
@Controller//제어권이 있는 클래스
@RequiredArgsConstructor
//@RestController
public class SearchController {

    private final SearchService searchService;
    private final PagenationUtil pagenationUtil;

    //목록
    @GetMapping(value = {"/","/list"})
    //()안에 입력인수 등록, 출력값이 있으면 Model 추가
    public String listView(
            @PageableDefault(page = 1)Pageable pageable,
            @RequestParam(value = "type",defaultValue = "") String type,
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            Model model
    )
    {
        //서비스연동
        Page<ListDTO> listDTOS = searchService.list(pageable, type, keyword);
        System.out.println("ㅇㅇㅇㅇ"+listDTOS.toString());

        //페이지정보 가공 - 직접 적으로 클래스에 있는 메서드를 가져 와서 사요
        Map<String, Integer> pageInfo = Pagination(listDTOS);
//        Map<String, Integer> pageInfo = pagenationUtil.Pagination(listDTOS);

        //값전달
        model.addAttribute("list", listDTOS);
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);

        model.addAllAttributes(pageInfo);

        return "search/list";  //String 연관

    }

    //삽입
    @GetMapping("/create")
    public String createView(){

        return "search/create";
    }

    @PostMapping("/create")
    public String createPoce(CreateDTO createDTO){
        log.info("Create 진입 했니");

        //서비스를 통해서 내부처리
        searchService.create(createDTO);

        return "redirect:/list";

    }

    //삭제
    @GetMapping("/delete")
    public String deleteProc(Integer id){
        //서비스 처리
        searchService.delete(id);

        return "redirect:/";
    }

    //상세보기
    @GetMapping("/detail")
    public String detailView(Integer id,Model model) {

        //서비스처리
        DetailDTO detailDTO =searchService.detail(id);

        model.addAttribute("data",detailDTO);

        return "/search/detail";
    }

    //수정
    @GetMapping("/modify")
    public String modifyView(Integer id,Model model){
        //서비스처리(수정폼에 기존내욜을 출력)
        DetailDTO detailDTO =searchService.detail(id);

        model.addAttribute("data",detailDTO);

        return "/search/modify";
    }

    @PostMapping("/modify")
    public String modifyProc(DetailDTO detailDTO){
        System.out.println(detailDTO.toString());

        //서비스처리(수정된 내용을 저장)
        searchService.modifity(detailDTO);


        return "redirect:/";

    }

}

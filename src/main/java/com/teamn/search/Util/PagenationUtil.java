package com.teamn.search.Util;
/*
공용모듈
*필요한 때무다 사용가능한 메서드
*/


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //공용모듈 클래스
public class PagenationUtil {
    //가공할 페이지 정보를 전달 받아서 각 페이지 번호의 정보를 저장해서 전달

    public static Map<String , Integer> Pagination (Page < ?> page) {
        //첫페이지(1) 이전(현재-1) 현재페이지 이후(현재+1) 마지막페이지(페이지 마지막)
        //데이터 베이스 페이지 번호(0) - > 화면에 출력할 현재 페이지 번호
        int currentpage = page.getNumber() + 1; //시작은 0부터 이기 때문에 필수
        int totalPages = page.getTotalPages();//전체 페이지 번호(마지막 번호)
        int blockLimit = 10; //화면에 출력할 페이지 번호의 갯수 페이지 번호의 갯수 처음이전 1,2,3,4, 다음끝

        Map<String, Integer> pageInfo = new HashMap<>();

        //앞에 시작번호가 0보다 작으면 1값으로 설정
        int startPage = Math.max(1, currentpage - blockLimit / 2); //화면에 출력되는 시작 페이지 번호

        //1페이지 +5 = 6 => 1 = > 5    1,2,3,4,5
        int endPage = Math.min(totalPages, startPage + blockLimit - 1);//화면에 출력되는 끝나는 페이지 번호

        //1과 현재 페이지 번호중 큰수를 저장 1,0(존재X) ==>1
        int prevPage = Math.min(1, currentpage - 1);//이전 페이지 번호(0번이면 존재 불가능)

        //마지막페이지번호와 다음 중 작은수를 저장 10,11(존재X) ==>?10
        int nextPage = Math.min(totalPages, currentpage + 1); //다음 페이지 번호(마지막페이지 보다 크면 존재 불가능)
        int lastPage = totalPages;    //마지막 페이지 번호

        //계산한 페이지 정보를 저장해서 전달
        //map에 추가할 때 put("변수명", 데이터값)
        pageInfo.put("startPage", startPage);
        pageInfo.put("endPage", endPage);
        pageInfo.put("prevPage", prevPage);
        pageInfo.put("currentpage", currentpage);
        pageInfo.put("nextPage", nextPage);
        pageInfo.put("lastPage", lastPage);


        //페이지 정보를 한번에 반환
        return pageInfo;

    }

}

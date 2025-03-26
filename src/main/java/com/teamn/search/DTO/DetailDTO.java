package com.teamn.search.DTO;

import lombok.*;

import java.time.LocalDateTime;

//상세보기
@Getter@Setter@AllArgsConstructor
@ToString
@NoArgsConstructor
public class DetailDTO {

    private Integer id; //일령번호

    private String subject; //제목

    private String content; //내용

    private String author;  //작성자

    private LocalDateTime modDate;  //수정일자

}

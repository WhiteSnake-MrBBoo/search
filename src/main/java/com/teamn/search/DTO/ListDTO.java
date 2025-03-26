package com.teamn.search.DTO;


import lombok.*;

import java.time.LocalDateTime;

//목록에 사용할DTO
@Getter@Setter@ToString@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListDTO {

    private Integer id; //일령번호

    private String subject; //제목

    private String author;  //작성자

    private LocalDateTime modDate;

}

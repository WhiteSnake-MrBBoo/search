package com.teamn.search.Entity;


import com.teamn.search.DTO.SearchDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//lombok을 이용해서 메소드를 자동화 합니다.--이클립스는 lombok이 수동 설치임
//테이블 조인 작업시 반드시 @toString은 사용하면 안된다.
//toString시 재귀함수로 무한반복 -> 오류

@Getter @Setter@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@SequenceGenerator(//자동숫자를 생성하고, 정보를 저장
    name = "search_seq",//테이블+seq
        sequenceName = "search_seq",
        initialValue = 1,   //시작값
        allocationSize = 1  //크기
 )  //Entity와 1:1로만 사용

@EntityListeners(AuditingEntityListener.class)  //createDate, LastModifyDate불가능
public class SearchEntity {

    @Id //기본키
    @Column(name = "id", nullable = false)  //name =필드명, nullable=생략, length=길이 = >생략하면 변수명으로 자동생성
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "search_seq") //기본키의 사용방법, 순차처리(1,2,3), 불규칠
    private Integer id; //번호

    @Column(name = "subject", nullable = false, length = 50)
    private String subject; //제목

    @Column(name = "content" , length = 200)
    private String content; //내용

    @Column(name = "author",length = 20)
    private String author;  //작성자

    @CreatedDate    //생성시 자동생성
    @Column(name = "regDate")
    private LocalDateTime regDate; //등록일자

    @LastModifiedDate   //최근(최종)수정시 자동생성
    @Column(name = "modDate")
    private LocalDateTime modDate; //수정일자


    //생성자, Getter, Setter, toString, Build 메소드


    //modelmapper 사용안하면 이곳에 변환 메소드

//    public DTO2Entity(SearchDTO searchDTO) {
//    }



}

//1.변수선언
//2.Column 지정
//3.Join 지정
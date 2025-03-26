package com.teamn.search.Service;

import com.teamn.search.DTO.CreateDTO;
import com.teamn.search.DTO.DetailDTO;
import com.teamn.search.DTO.ListDTO;
import com.teamn.search.Entity.SearchEntity;
import com.teamn.search.Repository.SearchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

//주석요령, 설명
//Todo: 앞으로 작업할 지시내용
//FixMe : 수정할 부분에 지시 내용

@Service    //문제해결 영역
@Transactional  //일괄처리(생략가능)
@RequiredArgsConstructor
public class SearchService {
    //연계클래스
    private final SearchRepository searchRepository;

    private final ModelMapper modelMapper;


    //Controller에 메소더의 ()안에 인수를 보고 작성
    //목록
    public Page<ListDTO> list(Pageable pageable,
                              String type,String keyword
    )
    {
        //페이지정보
        int currentPage = pageable.getPageNumber()-1;
        int limits = 10;
        Pageable temp =
                PageRequest.of(currentPage,limits, Sort.by(Sort.Direction.DESC,"id"));

        //todo : 조건별 조회 추가
        //2가지 구분 (조건대상), 검색어

        //전체조회
        //검색이 여러개 이면 저장변수를 분리
        Page<SearchEntity> searchEntities;
        //구분값이 있으면 if문으로 (switch) 분리
        //문자열 비교시 == 부정확 사용X , equals로 대신 사용

        if (type.equals("s") && keyword != null) {
            searchEntities = searchRepository.findBySubjectContaining(keyword,temp);

        }else if (type.equals("c") && keyword != null) {
            searchEntities = searchRepository.findByContentContaining(keyword,temp);

        }else if (type.equals("a") && keyword != null) {
            searchEntities = searchRepository.findByAuthorContaining(keyword,temp);

        }else if (type.equals("sc") && keyword != null) {
            searchEntities = searchRepository.findBySubjectContainingOrContentContaining(keyword,keyword,temp);

        } else if (type.equals("sca") && keyword != null) {
            searchEntities = searchRepository.findBySubjectContainingOrContentContainingOrAuthorContaining(keyword,keyword,keyword,temp);
        } else {    //해당 사항이 없으면

            //전체조회
            searchEntities = searchRepository.findAll(temp);
        }


        //변환
        Page<ListDTO> listDTOS = searchEntities.map(data -> modelMapper.map(data, ListDTO.class));

        return listDTOS;
    }

    //삽입
    public void create(CreateDTO createDTO){

        //변환(DTO > Entity) 외부데이터를 내부용으로 변환
        SearchEntity searchEntity = modelMapper.map(createDTO, SearchEntity.class);

        //SQL 처리
        searchRepository.save(searchEntity);

        //처리


    }

    //삭제
    public void delete(Integer id){

        searchRepository.deleteById(id);

    }

    //상세보기
    public DetailDTO detail(Integer id){

        Optional<SearchEntity> searchEntity =
                searchRepository.findById(id);

        //출력
        DetailDTO detailDTO =modelMapper.map(searchEntity,DetailDTO.class);

        return detailDTO;
    }

    //수정
    public void modifity(DetailDTO detailDTO){

        Optional<SearchEntity> temp
                = searchRepository.findById(detailDTO.getId());

        //변환
        SearchEntity searchEntity = modelMapper.map(detailDTO,SearchEntity.class);
        searchEntity.setRegDate(temp.get().getRegDate());

        //SQL처리
        searchRepository.save(searchEntity);


    }



}

package com.teamn.search.Repository;

import com.teamn.search.Entity.SearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {
    // 제목으로 검색
    Page<SearchEntity> findBySubjectContaining(String subject, Pageable pageable);

    // 내용으로 검색
    Page<SearchEntity> findByContentContaining(String content, Pageable pageable);

    // 작성자로 검색
    Page<SearchEntity> findByAuthorContaining(String author, Pageable pageable);

    // 제목 + 내용으로 검색
    Page<SearchEntity> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);

    // 제목 + 내용 + 작성자로 검색
    Page<SearchEntity> findBySubjectContainingOrContentContainingOrAuthorContaining(String subject, String content, String author, Pageable pageable);

    // JPQL 쿼리 수정
    @Query("SELECT b FROM SearchEntity b WHERE b.subject LIKE %:keyword% OR b.content LIKE %:keyword% OR b.author LIKE %:keyword%")
    Page<SearchEntity> searchAll(@Param("keyword") String keyword, Pageable pageable);
}









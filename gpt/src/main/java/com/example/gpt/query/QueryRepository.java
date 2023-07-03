package com.example.gpt.query;

import com.example.gpt.query.model.MyQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<MyQuery, Long> {
//    @org.springframework.data.jpa.repository.Query("SELECT q FROM MyQuery q WHERE q.user.id = :userId ORDER BY q.createdAt DESC")
//    List<MyQuery> findAllByUserId(Long userId);
}
package com.practice.board.board.repository;

import com.practice.board.common.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    long countByUserUserId(Long userId);
    List<Board> findAllByOrderByCreatedAtDesc();
    List<Board> findByTitleContainingOrderByCreatedAtDesc(String keyword);
}
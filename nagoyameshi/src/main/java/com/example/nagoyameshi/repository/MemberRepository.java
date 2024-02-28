package com.example.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	public Page<Member> findByNameLikeOrFuriganaLike(String namekeyword, String furiganaKeyword, Pageable pageable);
}

package com.sbkim.springboot_graphql.repository;

import com.sbkim.springboot_graphql.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

}

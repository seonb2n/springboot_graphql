package com.sbkim.springboot_graphql.repository;

import com.sbkim.springboot_graphql.domain.Content;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}

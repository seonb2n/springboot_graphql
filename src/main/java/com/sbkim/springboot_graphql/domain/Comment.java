package com.sbkim.springboot_graphql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COMMENTS")
@Getter
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;


    private String commentBody;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    protected Comment() {
    }

    private Comment(String commentBody, Content content) {
        this.commentBody = commentBody;
        this.content = content;
    }

    public static Comment of(String commentBody, Content content) {
        return new Comment(commentBody, content);
    }
}

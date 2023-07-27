package com.sbkim.springboot_graphql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Table(name = "CONTENTS")
@Getter
public class Content extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    private String contentTitle;

    private String contentBody;

    @OneToMany
    private List<Comment> commentList;

    protected Content() {
    }

    public Content(String contentTitle, String contentBody) {
        this.contentTitle = contentTitle;
        this.contentBody = contentBody;
        this.commentList = new ArrayList<>();
    }

    public static Content of(String contentTitle, String contentBody) {
        return new Content(contentTitle, contentBody);
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.setContent(this);
    }
}

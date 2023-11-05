package com.server.sopt.seminar.response.post;

import com.server.sopt.seminar.domain.Post;

public record PostGetResponse (
        Long id,
        String title,
        String content
){
    public static PostGetResponse of(Post post){
        return new PostGetResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent()
        );
    }
}

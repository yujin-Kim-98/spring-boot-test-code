package com.example.demo.post.controller.response;

import com.example.demo.post.domain.Post;
import com.example.demo.user.controller.response.UserResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

    private Long id;
    private String content;
    private Long createdAt;
    private Long modifiedAt;
    private UserResponse writer;

    public static PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .writer(UserResponse.toUserResponse(post.getWriter()))
                .build();
    }

}

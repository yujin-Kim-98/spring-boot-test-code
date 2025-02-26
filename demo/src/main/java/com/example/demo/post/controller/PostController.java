package com.example.demo.post.controller;

import com.example.demo.user.controller.UserController;
import com.example.demo.post.controller.response.PostResponse;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.post.infrastructure.PostEntity;
import com.example.demo.post.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물(posts)")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserController userController;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(toResponse(postService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable long id, @RequestBody PostUpdate postUpdateDto) {
        return ResponseEntity
                .ok()
                .body(toResponse(postService.update(id, postUpdateDto)));
    }

    public PostResponse toResponse(PostEntity postEntity) {
        PostResponse postResponse = new PostResponse();

        postResponse.setId(postEntity.getId());
        postResponse.setContent(postEntity.getContent());
        postResponse.setCreatedAt(postEntity.getCreatedAt());
        postResponse.setModifiedAt(postEntity.getModifiedAt());
        postResponse.setWriter(userController.toResponse(postEntity.getWriter()));

        return postResponse;
    }

}

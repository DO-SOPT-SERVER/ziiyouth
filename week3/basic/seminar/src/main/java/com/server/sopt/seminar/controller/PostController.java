package com.server.sopt.seminar.controller;

import com.server.sopt.seminar.request.post.PostCreateRequest;
import com.server.sopt.seminar.request.post.PostUpdateRequest;
import com.server.sopt.seminar.response.post.PostGetResponse;
import com.server.sopt.seminar.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {
    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
    private final PostService postService;

    /**
     * 게시글 생성 API
     */
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                           @RequestBody PostCreateRequest request){
        URI location = URI.create("/api/post/" + postService.create(request, memberId));
        return ResponseEntity.created(location).build();
    }

    /**
     * 특정 게시글 조회 API
     */
    @GetMapping("{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getById(postId));
    }

    /**
     * 전체 게시글 조회 API
     */
    @GetMapping()
    public ResponseEntity<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId){
        return ResponseEntity.ok(postService.getPosts(memberId));
    }

    /**
     * 게시글 수정 API
     */
    @PatchMapping("{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request){
        postService.editContent(postId, request);
        return ResponseEntity.noContent().build();
    }


    /**
     * 게시글 삭제 API
     */
    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }

}

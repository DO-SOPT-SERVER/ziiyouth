package com.server.sopt.seminar.service;

import com.server.sopt.seminar.domain.Category;
import com.server.sopt.seminar.domain.Member;
import com.server.sopt.seminar.domain.Post;
import com.server.sopt.seminar.repository.MemberRepository;
import com.server.sopt.seminar.repository.PostRepository;
import com.server.sopt.seminar.request.post.PostCreateRequest;
import com.server.sopt.seminar.request.post.PostUpdateRequest;
import com.server.sopt.seminar.response.post.PostGetResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CategoryService categoryService;

    @Transactional
    public String create(PostCreateRequest request, Long memberId){
        Member member = memberRepository.findByIdOrThrow(memberId);
        Post post = postRepository.save(
                Post.builder()
                        .member(member)
                        .title(request.title())
                        .content(request.content())
                        .build()
        );
        return post.getId().toString();
    }

    public PostGetResponse getById(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new EntityNotFoundException("해당하는 게시글이 없습니다."));
        return PostGetResponse.of(post, getCategoryByPost(post));
    }

    public List<PostGetResponse> getPosts(Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        List<Post> posts = postRepository.findAllByMemberId(memberId);

        return posts.stream().map(post -> PostGetResponse.of(post, getCategoryByPost(post))).toList();
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findByIdOrThrow(postId);
        post.updateContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = postRepository.findByIdOrThrow(postId);
        postRepository.delete(post);
    }

    private Category getCategoryByPost(Post post) {
        return categoryService.getByCategoryId(post.getCategoryId());
    }

}

package com.server.sopt.seminar.service;

import com.server.sopt.seminar.domain.Member;
import com.server.sopt.seminar.domain.Post;
import com.server.sopt.seminar.exception.BusinessException;
import com.server.sopt.seminar.external.S3Service;
import com.server.sopt.seminar.repository.MemberRepository;
import com.server.sopt.seminar.repository.PostRepository;
import com.server.sopt.seminar.request.post.PostCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

    private static final String POST_IMAGE_FOLDER_NAME = "posts/";

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final S3Service s3Service;

    @Transactional
    public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
        try {
            final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
            Member member = memberRepository.findByIdOrThrow(memberId);
            Post post = postRepository.save(
                    Post.builderWithImageUrl()
                            .title(request.title())
                            .content(request.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build());
            return post.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteByIdV2(Long postId) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new BusinessException("해당하는 게시글이 없습니다."));
            s3Service.deleteImage(post.getImageUrl());
            postRepository.deleteById(postId);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
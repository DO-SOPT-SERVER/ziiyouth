package com.server.sopt.seminar.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    // columnDefinition 안 달아주면 기본적으로 varchar로 저장된다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 연관관계의 주인은 Post (1:다 쪽에서는 다 쪽이 주인!)

    @Builder
    public Post(String title, String content, Member member){
        this.title = title;
        this.content = content;
        this.member = member;
    }
}

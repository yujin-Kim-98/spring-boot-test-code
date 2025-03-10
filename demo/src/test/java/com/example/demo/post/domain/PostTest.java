package com.example.demo.post.domain;

import com.example.demo.mock.TestClockHolder;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostTest {

    @Test
    public void PostCreate으로_게시물을_만들_수_있다() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("helloworld")
                .build();

        User writer = User.builder()
                .email("yujin123.kim@gmail.com")
                .nickname("yujji")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaaaaa-a-a-aaaaa")
                .build();

        // when
        Post post = Post.create(writer, postCreate, new TestClockHolder(1678530673958L));

        // then
        assertThat(post.getContent()).isEqualTo("helloworld");
        assertThat(post.getCreatedAt()).isEqualTo(1678530673958L);
        assertThat(post.getWriter().getEmail()).isEqualTo("yujin123.kim@gmail.com");
        assertThat(post.getWriter().getNickname()).isEqualTo("yujji");
        assertThat(post.getWriter().getAddress()).isEqualTo("Seoul");
        assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(post.getWriter().getCertificationCode()).isEqualTo("aaaaaaaaaaa-a-a-aaaaa");
    }

    @Test
    public void PostUpdate로_게시물을_수정할_수_있다() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .content("foobar")
                .build();

        User writer = User.builder()
                .id(1L)
                .email("yujin123.kim@gmail.com")
                .nickname("yujji")
                .address("Seoul")
                .certificationCode("aaaaaaa-aa-aaaaaa")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(0L)
                .build();

        Post post = Post.builder()
                .id(1L)
                .content("hello")
                .createdAt(1678530673958L)
                .modifiedAt(0L)
                .writer(writer)
                .build();

        // when
        post = post.update(postUpdate, new TestClockHolder(1678530673958L));

        // then
        assertThat(post.getContent()).isEqualTo("foobar");
        assertThat(post.getModifiedAt()).isEqualTo(1678530673958L);
    }

}

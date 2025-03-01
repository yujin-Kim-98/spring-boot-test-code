package com.example.demo.user.domain;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UserTest {

    @Test
    public void User는_UserCreate_객체로_생성할_수_있다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("yujin123.kim@gmail.com")
                .nickname("yujji")
                .address("Seoul")
                .build();

        // when
        User user = User.create(userCreate, new TestUuidHolder("aaaaaaa-aaaaa-aaaaa-aaaaaaa"));

        // then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo("yujin123.kim@gmail.com");
        assertThat(user.getNickname()).isEqualTo("yujji");
        assertThat(user.getAddress()).isEqualTo("Seoul");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("aaaaaaa-aaaaa-aaaaa-aaaaaaa");
    }

    @Test
    public void User는_UserUpdate_객체로_데이터를_업데이트_할_수_있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("yujin123.kim@gmail.com")
                .nickname("yujji")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaaa-aaaa-aaa-aaaaaaaa")
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("yujji-1")
                .address("Incheon")
                .build();

        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("yujin123.kim@gmail.com");
        assertThat(user.getNickname()).isEqualTo("yujji-1");
        assertThat(user.getAddress()).isEqualTo("Incheon");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(user.getCertificationCode()).isEqualTo("aaaaaaaaa-aaaa-aaa-aaaaaaaa");
        assertThat(user.getLastLoginAt()).isEqualTo(100L);
    }

    @Test
    public void User는_로그인을_할_수_있고_로그인시_마지막_로그인_시간이_변경된다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("yujin123.kim@gmail.com")
                .nickname("yujji")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaaa-aaaa-aaa-aaaaaaaa")
                .build();

        // when
        user = user.login(new TestClockHolder(200L));

        // then
        assertThat(user.getLastLoginAt()).isEqualTo(200L);
    }

    @Test
    public void User는_유효한_인증_코드로_계정을_활성화_할_수_있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("yujin123.kim@gmail.com")
                .nickname("yujji")
                .address("Seoul")
                .status(UserStatus.PENDING)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaaa-aaaa-aaa-aaaaaaaa")
                .build();

        // when
        user = user.certificate("aaaaaaaaa-aaaa-aaa-aaaaaaaa");

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    public void User는_잘못된_인증_코드로_계정을_활성화_하려하면_에러를_던진다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("yujin123.kim@gmail.com")
                .nickname("yujji")
                .address("Seoul")
                .status(UserStatus.PENDING)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaaa-aaaa-aaa-aaaaaaaa")
                .build();

        // when
        // then
        assertThatThrownBy(() -> {
            user.certificate("sldkf;lskd;lfks;ldfkslkgksjdhkajsd;ljdglsd");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }

}

package market.agriculture.entity;

import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberTest {

    @DisplayName("회원가입 테스트")
    @Test
    public void testCreateMember() {
        //given
        String username = "user1234";
        String password = "password1234";
        String nickname = "nickname1234";
        Address address = new Address("경기도", "평택시", "경기대로1166");
        Phone phoneNumber = new Phone("010", "62904016");
        Role role = Role.Customer;

        //when
        Member member = Member.createMember(username, password, nickname, address, phoneNumber, role);

        //then
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(member.getPassword()).isEqualTo(password);
        assertThat(member.getNickname()).isEqualTo(nickname);
        assertThat(member.getAddress()).isEqualTo(address);
        assertThat(member.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(member.getRole()).isEqualTo(role);
        assertThat(member.getBalance()).isEqualTo(100000L);
    }

    @DisplayName("회원 기본정보 수정")
    @Test
    public void testUpdateBasicInfo() {
        //given
        Member member = Member.createMember("user1234", "password1234", "nickname1234",
                new Address("경기도", "평택시", "경기대로1166"), new Phone("010", "62904016"), Role.Customer);
        String newUsername = "newUser";
        String newNickname = "newNickname";
        Address newAddress = new Address("경기도", "숸시", "병창로");

        //when
        member.updateBasicInfo(newUsername, newNickname, newAddress);

        // then
        assertThat(member.getUsername()).isEqualTo(newUsername);
        assertThat(member.getNickname()).isEqualTo(newNickname);
        assertThat(member.getAddress()).isEqualTo(newAddress);
    }

    @DisplayName("회원 비밀번호 수정")
    @Test
    public void testUpdatePassword() {
        //given
        Member member = Member.createMember("user1234", "password1234", "nickname1234",
                new Address("경기도", "평택시", "경기대로1166"), new Phone("010", "62904016"), Role.Customer);
        String newPassword = "newPassword";

        //when
        member.updatePassword(newPassword);

        //then
        assertThat(member.getPassword()).isEqualTo(newPassword);
    }

    @DisplayName("회원 역할")
    @Test
    public void testIsSeller() {
        Member seller = Member.createMember("seller", "password", "seller", null, null, Role.Seller);
        Member customer = Member.createMember("customer", "password", "customer", null, null, Role.Customer);

        //when, then
        assertThat(seller.isSeller()).isTrue();
        assertThat(customer.isSeller()).isFalse();
    }
}
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberSerivceTest {

    MemberSerivce memberSerivce ;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberSerivce = new MemberSerivce(memberRepository);
    }


    @AfterEach //각 테스트 끝날때마다 저장소 지우기
    public void aftterEach(){
        memberRepository.clearStore();

    }

    /*회원가입*/
    @Test
    void 회원가입() { //영어권 사람들과 일하는게 아니면 TEST는 그냥 한글로 적어도 된다. 실제로도 씀
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberSerivce.join(member);

        //then
        Member findMember = memberSerivce.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberSerivce.join(member1);

        //이 예외가(memberService에서 설정해놓은) 터져야 된다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberSerivce.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


       /* try {
            memberSerivce.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123213");

        }*/


        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}
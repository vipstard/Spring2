package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberSerivce {

    private final MemberRepository memberRepository;


    public MemberSerivce(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*회원가입*/
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); //중복회원 검증 함수 호출

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {//중복회원 검증함수
        memberRepository.findByName(member.getName())
            .ifPresent(member1 -> { // 자바 람다식?, ifPresent: NULL이 아니면 밑에 코드 동작
            //옵셔널로 한번 감싸준다.
                throw new IllegalStateException("이미 존재하는 회원입니다."); //
        });
    }

    /* 전체회원조회*/
    public List<Member> findMember(){
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

package hello.core.member;

public interface MemberRepository {

    void save(Member member); //DB에 저장

    Member findById(Long memberId); // DB에서 찾기

}

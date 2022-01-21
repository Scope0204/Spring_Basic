package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service -> @Component이 등록 되어있다.
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원 가입
    public Long join(Member member){
        // 조건 : 같은 이름이 있는 중복 회원은 x
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // ctrl + t 를 사용해서 만든 메서드추출
        /*
        Optional<Member> result = memberRepository.findByName(member.getName()); // command + option + v

        //result.get(); // 널을 반환할 수도 있기 때문에 직접반환하는 것은 안좋다
        result.ifPresent(m -> { //Optional 메소드
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // findByName의 반환값이 어차피 Optional이니 이런식으로 생략 가능
        memberRepository.findByName(member.getName())
                        .ifPresent(m ->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }


    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}

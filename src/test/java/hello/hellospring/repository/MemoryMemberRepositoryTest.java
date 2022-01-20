package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; //2.2.x를 선택하면 junit 버전이 5로 올라가서 정상 동작하지 않습니다. gradle 설정도 달라지구요.

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메소드가 실행이 끝날때마다 실행.
    public void afterEach(){
        repository.clearStore(); //저장소를 싹 지움
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member)); // sout + tap을 누르면 자동 완성됨
//        Assertions.assertEquals(member, result); // (Expected, Actual) =  (기대한값, 실제값) -> 같으면 true, 아니면 err
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6 : vscode shift a 같은 기능. 전체 수정
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);


    }
}

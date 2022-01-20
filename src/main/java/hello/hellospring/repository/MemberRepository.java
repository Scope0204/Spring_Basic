package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional : jdk8에서 나온 기능
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 지금까지 저장된 모든 회원 리스트를 반환
}

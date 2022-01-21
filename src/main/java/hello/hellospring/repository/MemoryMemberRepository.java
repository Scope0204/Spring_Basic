package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    // 실무에서는 동시성 문제가 발생할 수 있어서 HashMap보다는 ConCurrentHashMap을 자주사용
    private static Map<Long,Member> store = new HashMap<>(); // 자바7버전 이후로 <>로 생략가능 (어차피 해당 타입변수만 사용가능하므로 추론가능)
    private static long sequence = 0L; // 동시성 문제로 잘 안씀 이렇게

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 셋팅
        store.put(member.getId(), member); // id store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        return store.get(id) // 단 null이 반환될 경우가 있다
        return Optional.ofNullable(store.get(id)); // null이여도 클라이언트에서 처리

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //filter : 조건에 안맞는 요소 제외 , findAny : 스트림의 요소를 (아무거나) 하나를 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 저장소 store에 있는 멤버 반환
    }

    public void clearStore() {
        store.clear(); // store을 비워줌
    }

}


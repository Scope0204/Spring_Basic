package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //@Bean : 스프링빈에 등록
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 해당 멤버 리포지토리의 서비스를 제공
    }

    @Bean
    public MemberRepository memberRepository() {

//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}

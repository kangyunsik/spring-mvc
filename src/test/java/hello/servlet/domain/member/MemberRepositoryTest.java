package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //GIVEN
        Member member = new Member("hello",20);

        //WHEN
        memberRepository.save(member);

        //THEN
        Member findMember = memberRepository.findById(member.getId());
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void findAll(){
        //GIVEN
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",20);

        //WHEN
        memberRepository.save(member1);
        memberRepository.save(member2);

        //THEN
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);
    }
}
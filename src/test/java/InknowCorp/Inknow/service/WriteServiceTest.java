package InknowCorp.Inknow.service;

import InknowCorp.Inknow.domain.DisclosureType;
import InknowCorp.Inknow.domain.Member;
import InknowCorp.Inknow.domain.Write;
import InknowCorp.Inknow.repository.WriteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WriteServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    WriteService writeService;
    @Autowired
    WriteRepository writeRepository;

    @Test
    public void 일기작성() throws Exception {
        // given
        Member member = new Member();
        member.setHiddenName("수빈");
        em.persist(member);

        String content = "테스트 일기";
        DisclosureType type = DisclosureType.CLOSE;

        // when
        Long writeId = writeService.write(member.getId(), content, type);

        // then
        Write getWrite = writeRepository.findOneWrite(writeId);

        assertEquals("일기 작성 시 상태는 OPEN", DisclosureType.CLOSE, getWrite.getDisclosureType());
        assertEquals("일기를 쓴 작성자는 수빈", member, getWrite.getMember());
        assertEquals("일기의 내용은 테스트 일기", content, getWrite.getDiary().getContent());
    }

    @Test(expected = IllegalStateException.class)
    public void 일기_공개여부_변경() throws Exception {
        // given
        Member member = new Member();
        member.setHiddenName("수빈");
        em.persist(member);

        String content = "테스트 일기";
        DisclosureType type = DisclosureType.OPEN;

        // when
        Long writeId = writeService.write(member.getId(), content, type);
        Write getWrite = writeRepository.findOneWrite(writeId);

//        DisclosureType beforeType = getWrite.getDisclosureType();

        // then
        getWrite.changeDisclosureType();
//        DisclosureType afterType = getWrite.getDisclosureType();

//        assertEquals("기존의 일기 공개 상태는 CLOSE", DisclosureType.CLOSE, beforeType);
//        assertEquals("일기 공개 상태를 바꾼 후는 OPEN", DisclosureType.OPEN, afterType);
        fail("이미 공개로 설정해 바꿀 수 없다는 예외가 발생해야한다.");
    }
}
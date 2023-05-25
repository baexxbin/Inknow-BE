package InknowCorp.Inknow;

import InknowCorp.Inknow.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            LocalDateTime dateTime = LocalDateTime.of(2023, 5, 25, 2, 55);
            Member member = createMember("aaa", 10, dateTime, 3,"asdf@asdf.com", "userA");

            em.persist(member);

            Diary diary1 = Diary.createDiary("첫번째 일기");
            em.persist(diary1);

            Diary diary2 = Diary.createDiary("두번째 일기");
            em.persist(diary2);

            Write write1 = Write.createWrite(member, diary1, DisclosureType.OPEN);
            Write write2 = Write.createWrite(member, diary2, DisclosureType.OPEN);

            em.persist(write1);
            em.persist(write2);
        }

        public void dbInit2() {
            LocalDateTime dateTime = LocalDateTime.of(2023, 5, 25, 3, 0);
            Member member = createMember("bbb", 10, dateTime, 3, "qwer@qwer.com", "userB");

            em.persist(member);

            Diary diary1 = Diary.createDiary("냥냥냥");
            em.persist(diary1);

            Diary diary2 = Diary.createDiary("멍멍멍");
            em.persist(diary2);

            Write write1 = Write.createWrite(member, diary1, DisclosureType.OPEN);
            Write write2 = Write.createWrite(member, diary2, DisclosureType.OPEN);

            em.persist(write1);
            em.persist(write2);

        }

        private static Member createMember(String openName, Integer point, LocalDateTime dateTime,  Integer scrap, String email, String hidden) {
            Member member = new Member();
            member.setEmail(email);
            member.setHiddenName(hidden);

            Information information = new Information();
            information.setOpenName(openName);
            information.setPoint(point);
            information.setCounter(dateTime);
            information.setScrapLimit(scrap);

            member.setInformation(information);
            return member;
        }
    }
}

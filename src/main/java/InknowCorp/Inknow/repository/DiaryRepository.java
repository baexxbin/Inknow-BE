package InknowCorp.Inknow.repository;

import InknowCorp.Inknow.domain.Diary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class DiaryRepository {

    private final EntityManager em;

    public void save(Diary diary) {
        if (diary.getId() == null) {
            em.persist(diary);
        } else {
            em.merge(diary);
        }
    }

}

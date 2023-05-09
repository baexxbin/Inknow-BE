package InknowCorp.Inknow.repository;

import InknowCorp.Inknow.domain.Write;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WriteRepository {
    private final EntityManager em;

    public void save(Write write) {
        em.persist(write);
    }

    public Write findOneWrite(Long id) {
        return em.find(Write.class, id);
    }

    // 특정 사용자가 작성한 모든 일기찾기
//    public List<Write> findAllDiaryByMember()
}

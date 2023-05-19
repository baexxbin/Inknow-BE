package InknowCorp.Inknow.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberQuestionRepository {

    private final EntityManager em;
}

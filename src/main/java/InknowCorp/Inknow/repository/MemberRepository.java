package InknowCorp.Inknow.repository;

import InknowCorp.Inknow.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 회원 저장
    public void save(Member member) {
        em.persist(member);
    }

    // 회원 조회 (id를 통한 단건 조회)
    public Member findOneMember(Long id) {
        return em.find(Member.class, id);
    }

    // 모든 회원 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 비공개 이름으로 회원 목록 검색
    public List<Member> findByHiddenName(String hiddenName) {
        return em.createQuery("select m from Member m where m.hiddenName =:hidden_name", Member.class)
                .setParameter("hidden_name", hiddenName)
                .getResultList();
    }

    // 이메일로 회원 목록 검색
    public List<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email =:email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }
}
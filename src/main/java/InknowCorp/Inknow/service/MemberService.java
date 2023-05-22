package InknowCorp.Inknow.service;

import InknowCorp.Inknow.domain.Information;
import InknowCorp.Inknow.domain.Member;
import InknowCorp.Inknow.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if ((long) findMembers.size() > 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public Member findOneMember(Long memberId) {
        return memberRepository.findOneMember(memberId);
    }

    /*
        현재 information null값으로 인한 오류 발생, 회원가입 완료 후 코드 마무리
     */
    @Transactional
    public void update(Long id, String openName) {
        Member member = memberRepository.findOneMember(id);
        Information information = member.getInformation();
        information.setOpenName(openName);
        member.setInformation(information);
    }
}
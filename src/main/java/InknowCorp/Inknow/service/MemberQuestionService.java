package InknowCorp.Inknow.service;

import InknowCorp.Inknow.repository.MemberQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQuestionService {
    private final MemberQuestionRepository memberQuestionRepository;

    // 회원의 질문리스트 가져오기
}

package InknowCorp.Inknow.service;

import InknowCorp.Inknow.domain.Diary;
import InknowCorp.Inknow.domain.DisclosureType;
import InknowCorp.Inknow.domain.Member;
import InknowCorp.Inknow.domain.Write;
import InknowCorp.Inknow.repository.DiaryRepository;
import InknowCorp.Inknow.repository.MemberRepository;
import InknowCorp.Inknow.repository.WriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WriteService {

    private final WriteRepository writeRepository;
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;

    /*
       작성
     */
    @Transactional
    public Long write(Long memberId, String content, DisclosureType type){

        // 엔티티 조회
        Member member = memberRepository.findOneMember(memberId);

        // 일기 생성
        Diary diary = Diary.createDiary(content);

        // 작성 생성
        Write write = Write.createWrite(member, diary, type);

        // 작성 저장
        writeRepository.save(write);

        return write.getId();
    }

    /*
        작성한 일기 공개로 변경
     */
    @Transactional
    public Long changeDiaryOpen(Long writeId) {
        Write write = writeRepository.findOneWrite(writeId);
        write.changeDisclosureType();
        return write.getId();
    }

    // 검색
}

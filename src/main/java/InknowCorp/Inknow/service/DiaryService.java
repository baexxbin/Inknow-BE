package InknowCorp.Inknow.service;

import InknowCorp.Inknow.domain.Diary;
import InknowCorp.Inknow.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    @Transactional
    public void saveDiary(Diary diary) {
        diaryRepository.save(diary);
    }
}

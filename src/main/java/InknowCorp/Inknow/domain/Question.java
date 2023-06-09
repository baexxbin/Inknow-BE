package InknowCorp.Inknow.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Question {
    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<MemberQuestion> memberQuestions = new ArrayList<>();

    private String topic;


    /*
        비즈니스 로직
     */
    // 특정 질문을 받은 회원목록 가져오기
    public List<Member> getMembers() {
        List<Member> members = new ArrayList<>();
        for (MemberQuestion memberQuestion : memberQuestions) {
            members.add(memberQuestion.getMember());
        }
        return members;
    }

}

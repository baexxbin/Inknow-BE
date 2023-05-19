package InknowCorp.Inknow.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "hidden_name")
    private String hiddenName;

    private String email;

    @Embedded
    private Information information;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberQuestion> memberQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Write> writes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Write> scraps = new ArrayList<>();

    /*
        비즈니스 로직
     */

    // 회원이 지금까지 받은 질문 리스트 가져오기
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        for (MemberQuestion memberQuestion : memberQuestions) {
            questions.add(memberQuestion.getQuestion());
        }
        return questions;
    }
}
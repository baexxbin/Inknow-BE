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

    @Embedded
    private Information information;

//    @OneToMany(mappedBy = "member")
//    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberQuestion> memberQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Diary> scraps;

}
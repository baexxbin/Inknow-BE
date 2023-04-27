package InknowCorp.Inknow.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Question {
    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "write_id")
    private Write write;

    private String topic;

    @Enumerated(EnumType.STRING)
    private RespondStatus respondStatus;    // 응답상태 [RESPONDED, UNRESPONDED, RESPONSE_EXPIRED]

    private LocalDateTime expiration;

}

package InknowCorp.Inknow.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "writes")
public class Write {
    @Id @GeneratedValue
    @Column(name = "write_id")
    private Long id;

    @OneToOne(mappedBy = "write", fetch = FetchType.LAZY)
    private Question question;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    private LocalDateTime writeDate;

    @Enumerated(EnumType.STRING)
    private DisclosureType disclosureType;  // 일기 공개 여부 [OPEN, CLOSE]
}

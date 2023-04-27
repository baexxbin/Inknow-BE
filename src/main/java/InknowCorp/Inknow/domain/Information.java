package InknowCorp.Inknow.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
@Getter
public class Information {

    private String openName;
    private String email;
    private int point;
    private int scrapLimit;
    private LocalDateTime counter;
}

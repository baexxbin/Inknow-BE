package InknowCorp.Inknow.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
@Getter
public class Information {

    private String openName;
    private Integer point;
    private Integer scrapLimit;
    private LocalDateTime counter;
}

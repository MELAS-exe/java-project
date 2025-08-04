package entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Embeddable
@Data
public class OpeningHours {

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d \\/ ([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$",
             message = "Les plages horaires doivent être sous la forme HH:mm-HH:mm / HH:mm-HH:mm")
    private String monday;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d \\/ ([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$",
            message = "Les plages horaires doivent être sous la forme HH:mm-HH:mm / HH:mm-HH:mm")
    private String tuesday;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d \\/ ([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$",
            message = "Les plages horaires doivent être sous la forme HH:mm-HH:mm / HH:mm-HH:mm")
    private String wednesday;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d \\/ ([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$",
            message = "Les plages horaires doivent être sous la forme HH:mm-HH:mm / HH:mm-HH:mm")
    private String thursday;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d \\/ ([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$",
            message = "Les plages horaires doivent être sous la forme HH:mm-HH:mm / HH:mm-HH:mm")
    private String friday;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d \\/ ([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$",
            message = "Les plages horaires doivent être sous la forme HH:mm-HH:mm / HH:mm-HH:mm")
    private String saturday;
}

package bt.org.dsp.dessungskillingprogram.master.dspcenter;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
public class DSPCentreDTO {
    private Integer id;
    private String dspCentre;
}

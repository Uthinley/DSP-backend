package bt.org.dsp.dessungskillingprogram.master.dspcenter;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "dspCentre_master")
public class DspCentre extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NotNull
    private String dspCentre;
//    @OneToOne(mappedBy = "dspCentre")
//    private Trainers trainers;
}

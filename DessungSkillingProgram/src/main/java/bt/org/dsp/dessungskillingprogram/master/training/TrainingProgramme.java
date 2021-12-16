package bt.org.dsp.dessungskillingprogram.master.training;

import bt.org.dsp.dessungskillingprogram.master.Department;
import bt.org.dsp.dessungskillingprogram.master.branch.Branch;
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
@Table(name = "trainingProgramme_master")
public class TrainingProgramme {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NotNull
    private String trainingProgramme;
    @ManyToOne
    @JoinColumn(name = "branchId", referencedColumnName = "id")
    private Branch branch;

//    @OneToOne(mappedBy = "trainingProgramme")
//    private Trainers trainers;
}

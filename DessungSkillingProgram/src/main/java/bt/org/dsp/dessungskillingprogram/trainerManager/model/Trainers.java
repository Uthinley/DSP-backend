package bt.org.dsp.dessungskillingprogram.trainerManager.model;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import bt.org.dsp.dessungskillingprogram.master.Department;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.DspCentre;
import bt.org.dsp.dessungskillingprogram.master.training.TrainingProgramme;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "trainer_dtls")
public class Trainers extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String trainerId;
    private String trainerName;
    private String sex;
    private Date dateOfJoining;
    private String designation;
//    private String department;
//    private String branch;
    private String trainerAffiliation;

//    private String dspCentre;
//    private String trainingProgramme;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "dept_id", referencedColumnName = "id")
//    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id", referencedColumnName = "id")
    private TrainingProgramme trainingProgramme;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dspCentre_id", referencedColumnName = "id")
    private DspCentre dspCentre;

//    @OneToMany(mappedBy = "trainers")
//    @JsonManagedReference(value = "trainers-course")
//    private List<Course> courses;

}

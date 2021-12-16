package bt.org.dsp.dessungskillingprogram.courseManager.model;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.studentManager.model.Student;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "course_dtls")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String courseId;
    private String courseName;
    private String courseStatus;
    private String industrailSector;
    private String courseLevel;
    private Integer course_duration;
    private String startDate;
    private String endDate;
    private Integer cohortSize;
    private Integer noOfApplicants;
    private Integer noOfStdCertified;
    private String batchNo;
    private String trainingLocation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
//    @JsonBackReference(value = "trainers-course")
    private Trainers trainers;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();


}

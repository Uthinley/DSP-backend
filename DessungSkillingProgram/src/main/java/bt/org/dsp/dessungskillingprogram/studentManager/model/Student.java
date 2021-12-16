package bt.org.dsp.dessungskillingprogram.studentManager.model;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
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
@Table(name = "student_dtls")
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer studentId;

    private String cid;
    private String dessungId;
    private String name;
    private Integer mobileNo;
    private String dob;
    private String sex;
    private String email;
    private String bloodGroup;
    private String maritalStatus;
    private String avatar;
    private Integer trainingCentreId;
    private Integer batchNo;
    private String trainingYear;
//    private Integer tracerId;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "students_courses",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "studentId",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Course> courses = new HashSet<>();


}

package bt.org.dsp.dessungskillingprogram.master.course;


import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.master.CourseLevelMaster.CourseLevelMaster;
import bt.org.dsp.dessungskillingprogram.master.Department;
import bt.org.dsp.dessungskillingprogram.master.branch.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "course_master")
public class CourseMaster extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String courseId;
    private String courseName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "course_level_id", referencedColumnName = "id")
//    @JsonBackReference(value = "trainers-course")s
    private CourseLevelMaster courseLevelMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
//    @JsonBackReference(value = "trainers-course")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
//    @JsonBackReference(value = "trainers-course")
    private Branch branch;

}

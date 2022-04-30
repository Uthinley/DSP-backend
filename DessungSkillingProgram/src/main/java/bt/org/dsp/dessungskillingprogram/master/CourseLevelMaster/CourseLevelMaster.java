package bt.org.dsp.dessungskillingprogram.master.CourseLevelMaster;


import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.master.course.CourseMaster;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "course_level_master")
public class CourseLevelMaster extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String courseLvlName;

}

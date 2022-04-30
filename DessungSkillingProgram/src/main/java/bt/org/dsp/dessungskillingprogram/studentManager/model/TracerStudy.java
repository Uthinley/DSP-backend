package bt.org.dsp.dessungskillingprogram.studentManager.model;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tracer_dtls")
public class TracerStudy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer tracerId;
    private String cid;
    private String employmentStatus;
    private String organizationType;
    private String organizationName;
    private Integer salary;
    private String placeOfWork;
    private String workDuration;
    private String remainingWorkDuration;
    private String jobSatisfaction;
    private String futureDspPlan;
    private String dspCurrentWorkRelation;
    private Integer studentId;

}

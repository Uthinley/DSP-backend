package bt.org.dsp.dessungskillingprogram.studentManager.dto;

import lombok.Data;

@Data
public class TracerStudyDTO {
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

package bt.org.dsp.dessungskillingprogram.courseManager.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CourseDTO {
    private Integer id;

    private Integer courseId;
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
    private List<Integer> trainerId;
    private Integer trainingLocationId;
    private Integer industrailSectorId;
}

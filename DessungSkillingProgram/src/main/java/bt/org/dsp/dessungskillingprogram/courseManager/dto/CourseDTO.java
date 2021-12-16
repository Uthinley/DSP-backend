package bt.org.dsp.dessungskillingprogram.courseManager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CourseDTO {
    private Integer id;

    private String courseId;
    private String courseName;
    private String courseStatus;
    private String industrailSector;
    private String courseLevel;
    private Integer course_duration;
    private Date startDate;
    private Date endDate;
    private Integer cohortSize;
    private Integer noOfApplicants;
    private Integer noOfStdCertified;
    private String batchNo;
    private String trainingLocation;
}

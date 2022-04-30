package bt.org.dsp.dessungskillingprogram.studentManager.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class StudentDeatilDTO {
    private Integer studentId;
    private BigInteger cid;
    private String did;
    private String dessungId;
    private String name;
    private Integer mobileNo;
    private String dob;
    private String gender;
    private String sex;
    private String email;
    private String bloodGroup;
    private String maritalStatus;
    private String avatar;
    private Integer trainingCentreId;
    private Integer batchNo;
    private String trainingYear;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
}

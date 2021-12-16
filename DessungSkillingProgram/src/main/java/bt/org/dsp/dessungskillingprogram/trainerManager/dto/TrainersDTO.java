package bt.org.dsp.dessungskillingprogram.trainerManager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TrainersDTO {
    private Integer id;
    private String trainerId;
    private String trainerName;
    private String sex;
    private Date dateOfJoining;
    private String designation;
    private String department;
    private String branchId;
    private Integer dspCentreId;
    private Integer trainingProgramme;
    private String trainerAffiliation;
}

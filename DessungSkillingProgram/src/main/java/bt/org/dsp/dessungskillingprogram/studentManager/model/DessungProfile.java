package bt.org.dsp.dessungskillingprogram.studentManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "dessung_profile")
public class DessungProfile{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private BigInteger cid;
    private String did;
    private String name;
    private Integer mobileNo;
    private String dob;
    private String gender;
    private String email;
    private String bloodGroup;
    private String maritalStatus;
    private String avatar;
    private Integer trainingCentreId;
    private Integer batchNo;
    private String trainingYear;
}

package bt.org.dsp.dessungskillingprogram.studentManager.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class StudentDTO {
    private Integer courseSelected;
    private List<StudentDeatilDTO> studentList;

}

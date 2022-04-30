package bt.org.dsp.dessungskillingprogram.common;

import bt.org.dsp.dessungskillingprogram.studentManager.dto.GenericDTO;
import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;

import java.util.List;

public interface CommonRepository {
    UserDTO findByUsername(String username);
    GenericDTO getReport();
    GenericDTO getTotalbyCourseStatus();
    List<GenericDTO> getTotalCourseBySector();

}

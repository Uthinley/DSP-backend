package bt.org.dsp.dessungskillingprogram.common;

import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;

public interface CommonRepository {
    UserDTO findByUsername(String username);
}

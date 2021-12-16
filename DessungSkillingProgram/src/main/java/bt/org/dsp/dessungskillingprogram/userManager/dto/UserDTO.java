package bt.org.dsp.dessungskillingprogram.userManager.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String oldPassword;
    private Integer groupId;
    private String cid;


}

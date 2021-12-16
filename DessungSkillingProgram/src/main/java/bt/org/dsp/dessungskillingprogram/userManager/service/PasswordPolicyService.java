package bt.org.dsp.dessungskillingprogram.userManager.service;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;
import bt.org.dsp.dessungskillingprogram.userManager.model.PasswordPolicy;
import bt.org.dsp.dessungskillingprogram.userManager.model.Users;
import bt.org.dsp.dessungskillingprogram.userManager.repository.IPasswordPolicyRepository;
import bt.org.dsp.dessungskillingprogram.userManager.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
public class PasswordPolicyService extends BaseService {
    private IPasswordPolicyRepository passwordPolicyRepository;
    private IUserRepository userRepository;
    private AuthService authService;
    private PasswordEncoder passwordEncoder;
    private Environment environment;

    public ResponseEntity<UserDTO> changePassword(UserDTO userDTO){
        UserDTO userList = userRepository.findByUsername(userDTO.getUsername());
        Optional <PasswordPolicy> passwordPolicy = passwordPolicyRepository.findById( 1 );
        Boolean passwordLength = false;
        Boolean passwordValidity = false;
        Boolean passSpecial = false;
        Boolean passEnforce = false;
        if(passwordPolicy.isPresent()){
            PasswordPolicy _foundPolicy = passwordPolicy.get();
            if (_foundPolicy.getPassLength()!=null && _foundPolicy.getPassLength()==true) passwordLength = true;
            if (_foundPolicy.getPassValidity()!=null && _foundPolicy.getPassValidity()==true) passwordValidity = true;
            if (_foundPolicy.getPassSpecial()!=null && _foundPolicy.getPassSpecial()==true) passSpecial = true;
            if (_foundPolicy.getPassEnforce()!=null && _foundPolicy.getPassEnforce()==true) passEnforce = true;
        }

        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, 30);
        Date passwordExDate = new Date(current.getTimeInMillis());

        if(!Objects.isNull(userRepository.findByUsername(userDTO.getUsername()))){
//            Users _userFound = userList.get();
            Users users = new Users();
            String originalPassword = userList.getPassword();
            String oldPassword = userList.getOldPassword();

            // checking the old password is correct
            if(!passwordEncoder.matches(oldPassword, originalPassword))
                return new ResponseEntity("You entered wrong old password", HttpStatus.NOT_ACCEPTABLE);

            // checking new password entered is not the old password.
            if(passwordEncoder.matches(userDTO.getPassword(), originalPassword))
                return new ResponseEntity("Your old password cannot be the new password.", HttpStatus.NOT_ACCEPTABLE);
            if(!userDTO.getPassword().equals(null) || passwordLength == false)
                users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            if(passwordLength == true){
                if(userDTO.getPassword().length() >= 8)
                    users.setPassword( passwordEncoder.encode(userDTO.getPassword()) );
                else
                    return new ResponseEntity("Password must be at least 8 characters", HttpStatus.NOT_ACCEPTABLE);
            }

            // checking for special characters in password
            if(passSpecial == true){
                Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                Matcher matcher = pattern.matcher(userDTO.getPassword());
                boolean isStringContainsSpecialCharacter = matcher.find();
                if(isStringContainsSpecialCharacter)
                    users.setPassword( passwordEncoder.encode(userDTO.getPassword()) );
                else
                    return new ResponseEntity("Password must contain at least a special character", HttpStatus.NOT_ACCEPTABLE);
            }

            // setting password validity of 30 days
            if(passwordValidity == true)  users.setPasswordExpiryDate(passwordExDate);
            else users.setPasswordExpiryDate( null );
            if( users.getIsFirstLogin()!=null || users.getIsFirstLogin()==null ) users.setIsFirstLogin( false );
            users.setModifiedBy(authService.getCurrentUser().getUsername());
            users.setModifiedDate(new Date());
            return new ResponseEntity(userRepository.save(users), OK);
        }
        return new ResponseEntity("Failed", HttpStatus.NOT_FOUND);
    }
}

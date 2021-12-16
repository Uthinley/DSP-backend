package bt.org.dsp.dessungskillingprogram.userManager.service;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.userManager.model.UserGroup;
import bt.org.dsp.dessungskillingprogram.userManager.repository.IUserGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserGroupService extends BaseService {
    private IUserGroupRepository userGroupRepository;

    public ResponseMessage save(UserGroup userGroup, String currentUser) {
        if (StringUtils.isEmpty(userGroup)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter user group information to save.");
            return responseMessage;
        }
        try {
            userGroup.setCreatedBy(currentUser);
            userGroup.setCreatedDate(new Date());
            userGroupRepository.save(userGroup);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Group information couldn't save.");
            return responseMessage;
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Users group saved successfully.");
        return responseMessage;
    }

    public ResponseMessage deleteById(Integer id) {
        try {
            userGroupRepository.deleteById(id);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Users group couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Users group deleted successfully.");
        return responseMessage;
    }

    public List<UserGroup> findAll() {
        return (List<UserGroup>) userGroupRepository.findAll();
    }
}

package bt.org.dsp.dessungskillingprogram.userManager.controller;

import bt.org.dsp.dessungskillingprogram.base.BaseController;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.userManager.model.UserGroup;
import bt.org.dsp.dessungskillingprogram.userManager.service.AuthService;
import bt.org.dsp.dessungskillingprogram.userManager.service.UserGroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/userGroup")
@CrossOrigin
public class UserGroupController extends BaseController {
    private UserGroupService userGroupService;
    private AuthService authService;

    @PostMapping(value = "/save")
    public ResponseMessage save(@RequestBody UserGroup userGroup) {
        String currentUser= authService.getCurrentUser().getUsername();
        return userGroupService.save(userGroup, currentUser);
    }

    @GetMapping(value = "/findAll")
    public List<UserGroup> findAll() {
        return userGroupService.findAll();
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseMessage deleteById(@PathVariable("id") Integer id) {
        return userGroupService.deleteById(id);
    }
}

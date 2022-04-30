package bt.org.dsp.dessungskillingprogram.studentManager.controller;

import bt.org.dsp.dessungskillingprogram.base.BaseController;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.GenericDTO;
import bt.org.dsp.dessungskillingprogram.studentManager.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/home")
public class HomeController extends BaseController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/getReport")
    public GenericDTO getReport(){
        return studentService.getReport();
    }
}

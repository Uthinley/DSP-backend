package bt.org.dsp.dessungskillingprogram.courseManager.controller;

import bt.org.dsp.dessungskillingprogram.base.BaseController;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import bt.org.dsp.dessungskillingprogram.courseManager.service.CourseService;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(value = "/course")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/list")
    public List<Course> getCourseList(){
        return courseService.getCourseList();
    }
}

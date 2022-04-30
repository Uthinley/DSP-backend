package bt.org.dsp.dessungskillingprogram.courseManager.controller;

import bt.org.dsp.dessungskillingprogram.base.BaseController;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import bt.org.dsp.dessungskillingprogram.courseManager.service.CourseService;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.studentManager.model.TracerStudy;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/save")
    public ResponseMessage save(@RequestPart("course") String course    )throws JsonProcessingException {
        return courseService.save(course);
    }

    /**
     * Delete student by student id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseMessage deleteById(@PathVariable("id") Integer id) {
        return courseService.deleteByCourseById(id);
    }
    /**-
     * get tracer by cid
     * * @param cid
     * @return
     */
//    @GetMapping(value = "/findCourseById/{id}")
//    public List<TracerStudy> findCourseById(@PathVariable Integer id) {
//        return courseService.findTracerByStudentId(id);
//    }
}

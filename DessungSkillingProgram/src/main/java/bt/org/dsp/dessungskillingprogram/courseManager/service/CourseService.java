package bt.org.dsp.dessungskillingprogram.courseManager.service;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import bt.org.dsp.dessungskillingprogram.courseManager.repository.ICourseRepository;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService extends BaseService {
    @Autowired
    private ICourseRepository iCourseRepository;

    public List<Course> getCourseList() {
        return iCourseRepository.findAll();
    }
}

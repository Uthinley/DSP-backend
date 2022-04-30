package bt.org.dsp.dessungskillingprogram.courseManager.service;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.courseManager.dto.CourseDTO;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import bt.org.dsp.dessungskillingprogram.courseManager.repository.ICourseRepository;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.master.course.ICourseMasterRepository;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.IDSPCentre;
import bt.org.dsp.dessungskillingprogram.master.repository.IDepartmentRepository;
import bt.org.dsp.dessungskillingprogram.trainerManager.dto.TrainersDTO;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import bt.org.dsp.dessungskillingprogram.trainerManager.repository.ITrainerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CourseService extends BaseService {
    @Autowired
    private ICourseRepository iCourseRepository;
    @Autowired
    private IDSPCentre idspCentre;
    @Autowired
    private ITrainerRepository iTrainerRepository;
    @Autowired
    private IDepartmentRepository iDepartmentRepository;
    @Autowired
    private ICourseMasterRepository iCourseMasterRepository;

    public List<Course> getCourseList() {
        return iCourseRepository.findAll();
    }
    @Transactional
    public ResponseMessage save(String course) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CourseDTO courseDTO = new ObjectMapper().readValue(course, CourseDTO.class);
        Course courses = new Course();
        if (StringUtils.isEmpty(course)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setResponseText("Nothing to save.");
            return responseMessage;
        }
        try{


                if (courseDTO.getId() != null) {
                    courses.setId(courseDTO.getId());
                }
                courses.setCourseName(courseDTO.getCourseName());
                courses.setCourse_duration(courseDTO.getCourse_duration());
                //courses.setCourseLevel(courseDTO.getCourseLevel());
                courses.setCohortSize(courseDTO.getCohortSize());
                courses.setBatchNo(courseDTO.getBatchNo());
                courses.setEndDate(courseDTO.getEndDate());
                courses.setStartDate(courseDTO.getStartDate());
                courses.setCourseStatus(courseDTO.getCourseStatus());
                // courses.setIndustrailSector(courseDTO.getIndustrailSector());
                // courses.setCourseId(courseDTO.getCourseId());
                // courses.setDspCentre(idspCentre.findAllById(courseDTO.getTrainingLocationId()));
                courses.setCourseMaster(iCourseMasterRepository.findAllById(courseDTO.getCourseId()));
                //courses.setTrainers(iTrainerRepository.findAllById(courseDTO.getTrainerId()));
                courses.setDspCentre(idspCentre.findAllById(courseDTO.getTrainingLocationId()));
                // courses.setDepartment(iDepartmentRepository.findAllById(courseDTO.getIndustrailSectorId()));
                // courses.setTrainingLocation(courseDTO.getTrainingLocation());
                courses.setCreatedBy(authentication.getName());
                courses.setCreatedDate(new Date());
                iCourseRepository.save(courses);
                if(courseDTO.getTrainerId() != null) {
                    //Trainer ID is in list and interate over it
                    for (int i = 0; i < courseDTO.getTrainerId().size(); i++) {
//                        Trainers trainers = iTrainerRepository.findAllById(courseDTO.getTrainerId().get(i));
//                        courses.addTrainer(trainers);
                        if(!isTrainerExists(courseDTO.getTrainerId().get(i), courses.getId()))
                            iCourseRepository.saveRelation(courseDTO.getTrainerId().get(i), courses.getId());
                    }
                }

//                    courses.getTrainers().add(iTrainerRepository.findAllById(courseDTO.getTrainerId().get(i)));
//                iCourseRepository.save(courses);

//                    if(courses.getId() != null){
//                        Course courseFound = iCourseRepository.findCourseById(courses.getId());
//                        if(Objects.isNull(courseFound)){
//                            //courses.setCourseName(courseDTO.getCourseName());
//                            courses.setCourse_duration(courseDTO.getCourse_duration());
//                            //courses.setCourseLevel(courseDTO.getCourseLevel());
//                            courses.setCohortSize(courseDTO.getCohortSize());
//                            courses.setBatchNo(courseDTO.getBatchNo());
//                            courses.setEndDate(courseDTO.getEndDate());
//                            courses.setStartDate(courseDTO.getStartDate());
//                            courses.setCourseStatus(courseDTO.getCourseStatus());
//                            // courses.setIndustrailSector(courseDTO.getIndustrailSector());
//                            // courses.setCourseId(courseDTO.getCourseId());
//                            // courses.setDspCentre(idspCentre.findAllById(courseDTO.getTrainingLocationId()));
//                            courses.setCourseMaster(iCourseMasterRepository.findAllById(courseDTO.getCourseId()));
//                            //courses.setTrainers(iTrainerRepository.findAllById(courseDTO.getTrainerId()));
//                            courses.setDspCentre(idspCentre.findAllById(courseDTO.getTrainingLocationId()));
//                            // courses.setDepartment(iDepartmentRepository.findAllById(courseDTO.getIndustrailSectorId()));
//                            // courses.setTrainingLocation(courseDTO.getTrainingLocation());
//                            courses.setCreatedBy(authentication.getName());
//                            courses.setCreatedDate(new Date());
//                            courses.getTrainers().add(iTrainerRepository.findAllById(courseDTO.getTrainerId().get(i)));
////                            courses.addTrainer(iTrainerRepository.findAllById(courseDTO.getTrainerId().get(i)));
//                            iCourseRepository.save(courses);
//                        }else{
//                            courses.addTrainer(iTrainerRepository.findAllById(courseDTO.getTrainerId().get(i)));
//                            iCourseRepository.save(courses);
//                        }
//                    }else{
//                        courses.addTrainer(iTrainerRepository.findAllById(courseDTO.getTrainerId().get(i)));
//                        iCourseRepository.save(courses);
//                    }


            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Saved successfully.");
            return responseMessage;
        }catch (Exception ex){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setResponseText("Could not save." + ex);
            return responseMessage;
        }
    }
    @Transactional
    public ResponseMessage deleteByCourseById(Integer id) {
        try {
            iCourseRepository.deleteCourseById(id);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("course couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Course deleted successfully.");
        return responseMessage;
    }

    public boolean isTrainerExists(Integer trainerID, Integer trainingId){
//        if(!Objects.isNull(iCourseRepository.findTrainerExists(trainerID, trainingId)))
        if(iCourseRepository.findTrainerExists(trainerID, trainingId) > 0 )
            return true;
        else
            return false;
    }
}

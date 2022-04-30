package bt.org.dsp.dessungskillingprogram.studentManager.service;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.common.CommonService;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import bt.org.dsp.dessungskillingprogram.courseManager.repository.ICourseRepository;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.master.DeparmentDTO;
import bt.org.dsp.dessungskillingprogram.master.Department;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.*;
import bt.org.dsp.dessungskillingprogram.studentManager.model.DessungProfile;
import bt.org.dsp.dessungskillingprogram.studentManager.model.Student;
import bt.org.dsp.dessungskillingprogram.studentManager.model.TracerStudy;
import bt.org.dsp.dessungskillingprogram.studentManager.repository.IDessungProfileRepository;
import bt.org.dsp.dessungskillingprogram.studentManager.repository.IStudentRepository;
import bt.org.dsp.dessungskillingprogram.studentManager.repository.ITracerStudyRepository;
import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;
import bt.org.dsp.dessungskillingprogram.userManager.repository.IUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService extends BaseService {
    @Autowired
    private IStudentRepository iStudentRepository;
    @Autowired
    private ITracerStudyRepository iTracerStudyRepository;
    @Autowired
    private IDessungProfileRepository iDessungProfileRepository;
    @Autowired
    private ICourseRepository iCourseRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private CommonService commonService;

    public Student findStudentBycid(BigInteger cid) {
        return iStudentRepository.findStudentBycid(cid);
    }
    public DessungProfile findDessungBycid(BigInteger cid) {
        return iDessungProfileRepository.findDessungBycid(cid);
    }

    public List<TracerStudy> findTracerByStudentId(Integer id) {
        return iTracerStudyRepository.findTracerByStudentId(id);
    }

    public List<Student> findStudentByCourseId(Integer id) {
        return iStudentRepository.findStudentByCourseId(id);
    }

    public List<Student> findStudent(String stdInfo) {
        return iStudentRepository.findStudent(stdInfo);
    }

    public ResponseMessage saveTracer(String tracerDetails, String cid, Integer studentId) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TracerStudyDTO tracerStudyDTO = new ObjectMapper().readValue(tracerDetails, TracerStudyDTO.class);
        TracerStudy tracerStudy = new TracerStudy();
        if (StringUtils.isEmpty(tracerDetails)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setResponseText("Nothing to save.");
            return responseMessage;
        }
        try{
            tracerStudy.setCid(cid);
            tracerStudy.setStudentId(studentId);
            tracerStudy.setEmploymentStatus(tracerStudyDTO.getEmploymentStatus());
            tracerStudy.setJobSatisfaction(tracerStudyDTO.getJobSatisfaction());
            tracerStudy.setFutureDspPlan(tracerStudyDTO.getFutureDspPlan());
            tracerStudy.setDspCurrentWorkRelation(tracerStudyDTO.getDspCurrentWorkRelation());
            tracerStudy.setOrganizationName(tracerStudyDTO.getOrganizationName());
            tracerStudy.setOrganizationType(tracerStudyDTO.getOrganizationType());
            tracerStudy.setRemainingWorkDuration(tracerStudyDTO.getRemainingWorkDuration());
            tracerStudy.setWorkDuration(tracerStudyDTO.getWorkDuration());
            tracerStudy.setPlaceOfWork(tracerStudyDTO.getPlaceOfWork());
            tracerStudy.setSalary(tracerStudyDTO.getSalary());
            tracerStudy.setCreatedBy(authentication.getName());
            tracerStudy.setCreatedDate(new Date());
            iTracerStudyRepository.save(tracerStudy);
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Saved successfully.");
            return responseMessage;
        }catch (Exception ex){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setResponseText("Could not save." + ex);
            return responseMessage;
        }
    }

    public ResponseMessage saveStudent(String dessungProfile, Integer trainingID) throws JsonProcessingException{
        StudentDTO studentDTO = new ObjectMapper().readValue(dessungProfile, StudentDTO.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<StudentDeatilDTO> studentList = studentDTO.getStudentList();
        if (CollectionUtils.isEmpty(studentList)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter all the required information or nothing to save.");
            return responseMessage;
        }
        try{
            for(StudentDeatilDTO dto : studentList){
                    Student studentFound = iStudentRepository.findStudentBycid(dto.getCid());
                if(Objects.isNull(studentFound)){
                    Student student = new Student();
//                    student.setStudentId(dto.getStudentId());
                    student.setCid(dto.getCid());
                    student.setAvatar(dto.getAvatar());
                    student.setBatchNo(dto.getBatchNo());
                    student.setBloodGroup(dto.getBloodGroup());
                    student.setSex(dto.getGender());
                    student.setDessungId(dto.getDid());
                    student.setEmail(dto.getEmail());
                    student.setMobileNo(dto.getMobileNo());
                    student.setDob(dto.getDob());
                    student.setMaritalStatus(dto.getMaritalStatus());
                    student.setName(dto.getName());
                    student.setTrainingYear(dto.getTrainingYear());
                    student.setTrainingCentreId(dto.getTrainingCentreId());
                    student.setCreatedDate(new Date());
                    student.setCreatedBy(authentication.getName());
                    Course course = iCourseRepository.findCourseById(studentDTO.getCourseSelected());
                    student.addCourse(course);
                    student.setTracer('0');
                    iStudentRepository.save(student);
                }else{
                    Course course = iCourseRepository.findCourseById(studentDTO.getCourseSelected());
                    studentFound.addCourse(course);
                    iStudentRepository.save(studentFound);
                }
//                Student student = new Student();

            }
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Saved successfully.");
            return responseMessage;

        }catch (Exception ex){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Could not save." + ex);
            return responseMessage;
        }
    }

    public ResponseMessage deleteByStudentId(Integer id) {
        try {
            iStudentRepository.deleteById(id);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Student couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Student deleted successfully.");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage deleteStdById(Integer id, Integer trainingId) {
        try {
            iStudentRepository.deleteStdById(id, trainingId);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Student couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Student deleted successfully.");
        return responseMessage;
    }

    public ResponseMessage update(String data) throws JsonProcessingException {
        Student student = new ObjectMapper().readValue(data, Student.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        List<StudentDeatilDTO> studentList = studentDTO.getStudentList();
        try {
                student.setModifiedDate(new Date());
                student.setModifiedBy(authentication.getName());
                iStudentRepository.save(student);
//                Student student = new Student();
//                student.setCid(studentDTO.getCid());
//                student.setAvatar(studentDTO.getAvatar());
//                student.setBatchNo(studentDTO.getBatchNo());
//                student.setBloodGroup(studentDTO.getBloodGroup());
//                student.setSex(studentDTO.getGender());
//                student.setDessungId(studentDTO.getDid());
//                student.setEmail(studentDTO.getEmail());
//                student.setMobileNo(studentDTO.getMobileNo());
//                student.setDob(studentDTO.getDob());
//                student.setMaritalStatus(studentDTO.getMaritalStatus());
//                student.setName(studentDTO.getName());
//                student.setTrainingYear(studentDTO.getTrainingYear());
//                student.setTrainingCentreId(studentDTO.getTrainingCentreId());
//                student.setModifiedDate(new Date());
//                student.setModifiedBy(authentication.getName());
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Could not save." + ex);
            return responseMessage;
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Updated successfully.");
        return responseMessage;
    }

    public UserDTO findUserDetailByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    public GenericDTO getReport() {
        return iStudentRepository.getReport();
    }

    public ResponseMessage isStudentExist(BigInteger cid, Integer selectedCourse) {
        Student student = iStudentRepository.findStudentBycid(cid);
        if(!Objects.isNull(student)){
            List<Course> cList = student.getCourses();
            for(Course course : cList){
                if(course.getId().equals(selectedCourse)){
                    responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                    responseMessage.setText("This student is already in this course. You cannot enrol again");
                    break;
                }
                else{
                    responseMessage.setStatus(SUCCESSFUL_STATUS);
                }
            }
        }else{
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Student is new");
        }
        return responseMessage;
//        if (!Objects.isNull(student)) {
//
////            return new ResponseEntity("Users already exist.", HttpStatus.OK);
//        } else {
////            return new ResponseEntity("Users does not exist.", HttpStatus.FOUND);
//        }
    }
//    public List<TracerStudy> getTracerList(Integer id) {
//        return ITracerStudyRepository.findTracerBySid();
//    }

    @Transactional
    public ResponseMessage uploadFile(MultipartFile file, Integer trainingId, String currentUser) throws IOException {
        if (Objects.isNull(file)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose a file.");
            return responseMessage;
        }
        List<StudentExcelDTO> studentExcelDTOS = commonService.readExcel(file,StudentExcelDTO.class);

        if (CollectionUtils.isEmpty(studentExcelDTOS)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose a file.");
            return responseMessage;
        }

        for (StudentExcelDTO dto : studentExcelDTOS) {
            Student student = new Student();
            if(isStudentExists(new BigInteger(dto.getCid()))){
                Student studentDeatilDTO = iStudentRepository.findStudentBycid(new BigInteger(dto.getCid()));
                if(isStudentinCourse(studentDeatilDTO.getStudentId(), trainingId)){
                    System.out.println("This student is already enrolled in the course" +studentDeatilDTO.getStudentId());
                }else{
                    studentDeatilDTO.addCourse(iCourseRepository.findCourseById(trainingId));
                    iStudentRepository.save(studentDeatilDTO);
                }
            }else{
                student.setCid(new BigInteger(dto.getCid()));
                student.setDessungId(dto.getDessungId());
                student.setName(dto.getName());
                student.setMobileNo(dto.getMobileNo());
                student.setDob(dto.getDob());
                student.setSex(dto.getSex());
                student.setEmail(dto.getEmail());
                student.setBloodGroup(dto.getBoodGroup());
                student.setMaritalStatus(dto.getMaritalStatus());
                student.setBatchNo(dto.getBatchNo());
                student.setTrainingYear(dto.getTrainingYear());
                student.setCreatedBy(currentUser);
                student.setCreatedDate(new Date());
                student.addCourse(iCourseRepository.findCourseById(trainingId));
                iStudentRepository.save(student);
            }

//            iDepartmentRepository.save(department);
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Uploaded successfully.");
        return responseMessage;

    }

    public boolean isStudentExists(BigInteger cid){
        if (!Objects.isNull(iStudentRepository.findStudentBycid(cid))) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isStudentinCourse(Integer studentId, Integer trainingId){
        if(!Objects.isNull(iStudentRepository.findStudentInCourse(studentId, trainingId)))
            return true;
        else
            return false;
    }

}

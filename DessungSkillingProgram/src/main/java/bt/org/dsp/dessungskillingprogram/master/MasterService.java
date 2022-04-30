package bt.org.dsp.dessungskillingprogram.master;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.common.CommonService;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.master.CourseLevelMaster.CourseLevelMaster;
import bt.org.dsp.dessungskillingprogram.master.CourseLevelMaster.ICourseLevelMasterRepository;
import bt.org.dsp.dessungskillingprogram.master.branch.Branch;
import bt.org.dsp.dessungskillingprogram.master.branch.BranchDTO;
import bt.org.dsp.dessungskillingprogram.master.branch.IBranchRepository;
import bt.org.dsp.dessungskillingprogram.master.course.CourseMaster;
import bt.org.dsp.dessungskillingprogram.master.course.CourseMasterDTO;
import bt.org.dsp.dessungskillingprogram.master.course.ICourseMasterRepository;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.DspCentre;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.IDSPCentre;
import bt.org.dsp.dessungskillingprogram.master.repository.IDepartmentRepository;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.GenericDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class MasterService extends BaseService {
    @Autowired
    private IDepartmentRepository iDepartmentRepository;
    @Autowired
    private IBranchRepository iBranchRepository;
    @Autowired
    private IDSPCentre idspCentre;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ICourseLevelMasterRepository iCourseLevelMasterRepository;
    @Autowired
    private ICourseMasterRepository iCourseMasterRepository;
//    @Autowired
//    private ITrainingProgrammeRepository iTrainingProgrammeRepository;


    public List<Department> findAll() {
        return (List<Department>) iDepartmentRepository.findAll();
    }

    public List<Branch> findAllBranches() {
        return (List<Branch>) iBranchRepository.findAll();
    }
    public List<Branch> findBranchById(Integer id) {
        return iBranchRepository.findBranchById(id);
    }

//    public List<TrainingProgramme> findTrainingById(Integer id) {
//        return iTrainingProgrammeRepository.findTrainingProgrammeById(id);
//    }

    public List<DspCentre> getDspCentre() {
        return (List<DspCentre>) idspCentre.findAll();
    }

//    public List<TrainingProgramme> getTrainingProgramme() {
//        return (List<TrainingProgramme>) iTrainingProgrammeRepository.findAll();
//    }

    public ResponseMessage saveDepartment(Department department, String currentUser) {
        if (StringUtils.isEmpty(department)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter the information to save.");
            return responseMessage;
        }
        try {
            department.setCreatedBy(currentUser);
            department.setCreatedDate(new Date());
            iDepartmentRepository.save(department);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Information couldn't save.");
            return responseMessage;
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Department saved successfully.");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage uploadFile(MultipartFile file, String currentUser) throws IOException {
        if (Objects.isNull(file)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose at least one file.");
            return responseMessage;
        }
        List<DeparmentDTO> deparmentDTOList = commonService.readExcel(file,DeparmentDTO.class);

        if (CollectionUtils.isEmpty(deparmentDTOList)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose at least one file.");
            return responseMessage;
        }

        for (DeparmentDTO dto : deparmentDTOList) {
            Department department = new Department();
            department.setCreatedBy(currentUser);
            department.setCreatedDate(new Date());
            department.setDepartmentName(dto.getDepartmentName());
            iDepartmentRepository.save(department);
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Uploaded successfully.");
        return responseMessage;

    }

    @Transactional
    public ResponseMessage deleteDeptById(Integer id) {
        try {
            iDepartmentRepository.deleteById(id);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Department couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Department deleted successfully.");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage deleteBranchById(Integer id) {
        try {
            iBranchRepository.deleteById(id);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Branch couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Branch deleted successfully.");
        return responseMessage;
    }

    public ResponseMessage saveBranch(String branch, String currentUser) throws JsonProcessingException {
        BranchDTO branchDTO = new ObjectMapper().readValue(branch, BranchDTO.class);
        if (StringUtils.isEmpty(branch)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter the information to save.");
            return responseMessage;
        }
        try {
            Branch branchEntity = new Branch();
            branchEntity.setId(branchDTO.getId());
            branchEntity.setCreatedBy(currentUser);
            branchEntity.setCreatedDate(new Date());
            branchEntity.setBranchName(branchDTO.getBranchName());
            branchEntity.setDepartment(iDepartmentRepository.findAllById(branchDTO.getDepartmentId()));
//            branch.setDepartment(iDepartmentRepository.findAllById(branch.));
            iBranchRepository.save(branchEntity);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Information couldn't save.");
            return responseMessage;
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Department saved successfully.");
        return responseMessage;
    }

    public List<CourseLevelMaster> getCourseLevel() {
        return iCourseLevelMasterRepository.findAll();
    }

    public ResponseMessage saveCourse(String course, String currentUser) throws JsonProcessingException {
        CourseMasterDTO courseMasterDTO = new ObjectMapper().readValue(course, CourseMasterDTO.class);
        if (StringUtils.isEmpty(course)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter the information to save.");
            return responseMessage;
        }
        try{
            CourseMaster course1 = new CourseMaster();
            course1.setCourseId(courseMasterDTO.getCourseId());
            course1.setCourseName(courseMasterDTO.getCourseName());
            course1.setId(courseMasterDTO.getId());
            course1.setCreatedBy(currentUser);
            course1.setCreatedDate(new Date());
            course1.setDepartment(iDepartmentRepository.findAllById(courseMasterDTO.getSectorId()));
//            course1.setCourseLevelMaster((CourseLevelMaster) iCourseLevelMasterRepository.findAllById(Collections.singleton(courseMasterDTO.getClevel())));
            course1.setCourseLevelMaster(iCourseLevelMasterRepository.findAllById(courseMasterDTO.getClevel()));
            course1.setBranch(iBranchRepository.findAllById(courseMasterDTO.getBranchId()));
            iCourseMasterRepository.save(course1);
        }catch (Exception ex){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Information couldn't save.");
            return responseMessage;
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("course saved successfully.");
        return responseMessage;
    }

    public List<CourseMaster> getCourses() {
        return iCourseMasterRepository.findAll();
    }

    @Transactional
    public ResponseMessage deleteCourseById(Integer id) {
        try {
            iCourseMasterRepository.deleteById(id);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Course couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Course deleted successfully.");
        return responseMessage;
    }

    public Optional<CourseMaster> findCourseById(Integer id) {
        return iCourseMasterRepository.findById(id);
    }
    public List<CourseMaster> getRecommendedCourseByDeptId(Integer deptId){
        return iCourseMasterRepository.findRecommendedCourseByDeptId(deptId);
    }

    @Transactional
    public List<GenericDTO> getTotalCourseBySector() {
        return iCourseMasterRepository.getTotalCourseBySector();
    }

    public GenericDTO getTotalbyCourseStatus() {
        return iCourseMasterRepository.getTotalbyCourseStatus();
    }
}

package bt.org.dsp.dessungskillingprogram.master;

import bt.org.dsp.dessungskillingprogram.base.BaseController;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.master.CourseLevelMaster.CourseLevelMaster;
import bt.org.dsp.dessungskillingprogram.master.branch.Branch;
import bt.org.dsp.dessungskillingprogram.master.course.CourseMaster;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.DspCentre;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.GenericDTO;
import bt.org.dsp.dessungskillingprogram.userManager.model.UserGroup;
import bt.org.dsp.dessungskillingprogram.userManager.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(value = "/master")
public class MasterController extends BaseController {
    @Autowired
    private MasterService masterService;
    @Autowired
    private AuthService authService;
    /**
     * get all the departments
     * @return departments
     */
    @GetMapping(value = "/getDepartments")
    public List<Department> getDepartments() {
        return masterService.findAll();
    }

    @GetMapping(value = "/getCourses")
    public List<CourseMaster> getCourses(){
        return masterService.getCourses();
    }
    /**
     * get all the branch
     * @return branch
     */
    @GetMapping(value = "/getBranches")
    public List<Branch> getBranches() {
        return masterService.findAllBranches();
    }

    @GetMapping(value = "/getSelectedBranches/{id}")
    public List<Branch> findBranchById(@PathVariable Integer id){
        return masterService.findBranchById(id);
    }

    @GetMapping(value = "/getSelectedCourse/{id}")
    public Optional<CourseMaster> findCourseById(@PathVariable Integer id){
        return masterService.findCourseById(id);
    }

    @GetMapping(value = "/getRecommendedCourseByDeptId/{deptId}")
    public List<CourseMaster> getRecommendedCourseByDeptId(@PathVariable Integer deptId){
        return masterService.getRecommendedCourseByDeptId(deptId);
    }

    @GetMapping(value = "/getTotalCourseBySector")
    public List<GenericDTO> getTotalCourseBySector(){
        return masterService.getTotalCourseBySector();
    }

    @GetMapping(value="/getTotalbyCourseStatus")
    public GenericDTO getTotalbyCourseStatus(){
        return masterService.getTotalbyCourseStatus();
    }
//    @GetMapping(value = "/getSelectedTraining/{id}")
//    public List<TrainingProgramme> findTrainingById(@PathVariable Integer id){
//        return masterService.findTrainingById(id);
//    }


    /**
     * get all the centre
     * @return centre
     */
    @GetMapping(value = "/getDspCentre")
    public List<DspCentre> getDspCentre() {
        return masterService.getDspCentre();
    }

    /**
     * get all the centre
     * @return centre
     */
    @GetMapping(value = "/getCourseLevel")
    public List<CourseLevelMaster> getCourseLevel() {
        return masterService.getCourseLevel();
    }

    /**
     * get all the TrainingProgramme
     * @return TrainingProgramme
     */
//    @GetMapping(value = "/getTrainingProgramme")
//    public List<TrainingProgramme> getTrainingProgramme() {
//        return masterService.getTrainingProgramme();
//    }

    @PostMapping(value = "/saveDepartment")
    public ResponseMessage saveDepartment(@RequestBody Department department) {
        String currentUser=authService.getCurrentUser().getUsername();
        return masterService.saveDepartment(department, currentUser);
    }
    @PostMapping(value = "/saveBranch")
    public ResponseMessage saveBranch(@RequestPart("branch") String branch) throws JsonProcessingException {
        String currentUser=authService.getCurrentUser().getUsername();
        return masterService.saveBranch(branch, currentUser);
    }

    @PostMapping(value = "/saveCourse")
    public ResponseMessage saveCourse (@RequestPart ("courseMaster") String course) throws JsonProcessingException {
        String currentUser = authService.getCurrentUser().getUsername();
        return masterService.saveCourse(course, currentUser);

    }
    /**
     * upload file of department
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseMessage uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        String currentUser=authService.getCurrentUser().getUsername();
        return masterService.uploadFile(file, currentUser);
    }


    /**
     * Delete student by student id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteDeptById/{id}")
    public ResponseMessage deleteDeptById(@PathVariable("id") Integer id) {
        return masterService.deleteDeptById(id);
    }

    /**
     * Delete student by student id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteBranchById/{id}")
    public ResponseMessage deleteBranchById(@PathVariable("id") Integer id) {
        return masterService.deleteBranchById(id);
    }

    @DeleteMapping(value = "/deleteCourseById/{id}")
    public ResponseMessage deleteCourseById(@PathVariable("id") Integer id) {
        return masterService.deleteCourseById(id);
    }

}

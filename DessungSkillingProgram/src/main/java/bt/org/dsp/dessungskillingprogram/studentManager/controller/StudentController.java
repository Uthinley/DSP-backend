package bt.org.dsp.dessungskillingprogram.studentManager.controller;

import bt.org.dsp.dessungskillingprogram.base.BaseController;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.studentManager.model.DessungProfile;
import bt.org.dsp.dessungskillingprogram.studentManager.model.Student;
import bt.org.dsp.dessungskillingprogram.studentManager.model.TracerStudy;
import bt.org.dsp.dessungskillingprogram.studentManager.service.StudentService;
import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;
import bt.org.dsp.dessungskillingprogram.userManager.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/student")
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AuthService authService;
    /**
     * get student by cid
     * * @param cid
     * @return
     */
    @GetMapping(value = "/findStudentBycid/{cid}")
    public Student findStudentBycid(@PathVariable BigInteger cid) {
        return studentService.findStudentBycid(cid);
    }

    @GetMapping(value = "/findDessungBycid/{cid}")
    public DessungProfile findDessungBycid(@PathVariable BigInteger cid) {
        return studentService.findDessungBycid(cid);
    }

    @GetMapping(value = "/findUserDetailByUsername/{username}")
    public UserDTO findUserDetailByUsername(@PathVariable String username) {
        return studentService.findUserDetailByUsername(username);
    }

    /**-
     * get tracer by cid
     * * @param cid
     * @return
     */
    @GetMapping(value = "/findTracerByid/{id}")
    public List<TracerStudy> findTracerByid(@PathVariable Integer id) {
        return studentService.findTracerByStudentId(id);
    }

    @PostMapping(value = "/saveTracer")
    public ResponseMessage saveTracer(@RequestPart("tracerDetails") String tracerDetails, @RequestParam("studentId") Integer studentId, @RequestParam("cid") String cid)throws JsonProcessingException {
        return studentService.saveTracer(tracerDetails, cid, studentId);
    }

    @PostMapping(value = "/saveStudent")
    public ResponseMessage saveStudent (@RequestParam("dessungProfile") String dessungProfile, @RequestParam("trainingID") Integer trainingID) throws JsonProcessingException{
        return studentService.saveStudent(dessungProfile, trainingID);
//        return null;
    }

    /**
     * Check whether the student exists or not by cid
     * @param cid
     * @return
     */
    @RequestMapping(value = "/isStudentExist/{cid}/{selectedCourse}")
    @ResponseBody
    public ResponseMessage isStudentExist(@PathVariable BigInteger cid, @PathVariable Integer selectedCourse) {
        return studentService.isStudentExist(cid, selectedCourse);
    }


    @GetMapping(value = "/findStudentById/{id}")
    public List<Student> findStudentByCourseId(@PathVariable Integer id){
        return studentService.findStudentByCourseId(id);
    }

    @GetMapping(value = "/findStudent/{studentInfo}")
    public List<Student> findStudent(@PathVariable String studentInfo){
        return studentService.findStudent(studentInfo);
    }

    /**
     * Delete student by student id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseMessage deleteById(@PathVariable("id") Integer id) {
        return studentService.deleteByStudentId(id);
    }

    @DeleteMapping(value = "/deleteStdById/{id}/{trainingId}")
    public ResponseMessage deleteStdById(@PathVariable Integer id, @PathVariable Integer trainingId) {
        return studentService.deleteStdById(id, trainingId);
    }


    @PostMapping(value = "/update")
    public ResponseMessage update(@RequestParam("student") String student) throws JsonProcessingException {
        return studentService.update(student);
    }

    /**
     * upload student file
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadStudentFile", method = RequestMethod.POST)
    public ResponseMessage uploadStudentFile(@RequestParam("file") MultipartFile file, @RequestParam("trainingId") Integer trainingId) throws Exception {
        String currentUser=authService.getCurrentUser().getUsername();
        return studentService.uploadFile(file,trainingId, currentUser);
//        return null;
    }

//    @GetMapping(value = "/tracerlist/{id}")
//    public List<TracerStudy> getTracerList(@PathVariable Integer id){
//        return studentService.getTracerList(id);
//    }

}

package bt.org.dsp.dessungskillingprogram.master;

import bt.org.dsp.dessungskillingprogram.base.BaseController;
import bt.org.dsp.dessungskillingprogram.master.branch.Branch;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.DspCentre;
import bt.org.dsp.dessungskillingprogram.master.training.TrainingProgramme;
import bt.org.dsp.dessungskillingprogram.userManager.model.UserGroup;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(value = "/master")
public class MasterController extends BaseController {
    @Autowired
    private MasterService masterService;
    /**
     * get all the departments
     * @return departments
     */
    @GetMapping(value = "/getDepartments")
    public List<Department> getDepartments() {
        return masterService.findAll();
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

    @GetMapping(value = "/getSelectedTraining/{id}")
    public List<TrainingProgramme> findTrainingById(@PathVariable Integer id){
        return masterService.findTrainingById(id);
    }


    /**
     * get all the centre
     * @return centre
     */
    @GetMapping(value = "/getDspCentre")
    public List<DspCentre> getDspCentre() {
        return masterService.getDspCentre();
    }

    /**
     * get all the TrainingProgramme
     * @return TrainingProgramme
     */
    @GetMapping(value = "/getTrainingProgramme")
    public List<TrainingProgramme> getTrainingProgramme() {
        return masterService.getTrainingProgramme();
    }

}

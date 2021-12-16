package bt.org.dsp.dessungskillingprogram.master;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.master.branch.Branch;
import bt.org.dsp.dessungskillingprogram.master.branch.IBranchRepository;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.DspCentre;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.IDSPCentre;
import bt.org.dsp.dessungskillingprogram.master.repository.IDepartmentRepository;
import bt.org.dsp.dessungskillingprogram.master.training.ITrainingProgrammeRepository;
import bt.org.dsp.dessungskillingprogram.master.training.TrainingProgramme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasterService extends BaseService {
    @Autowired
    private IDepartmentRepository iDepartmentRepository;
    @Autowired
    private IBranchRepository iBranchRepository;
    @Autowired
    private IDSPCentre idspCentre;
    @Autowired
    private ITrainingProgrammeRepository iTrainingProgrammeRepository;

    public List<Department> findAll() {
        return (List<Department>) iDepartmentRepository.findAll();
    }

    public List<Branch> findAllBranches() {
        return (List<Branch>) iBranchRepository.findAll();
    }
    public List<Branch> findBranchById(Integer id) {
        return iBranchRepository.findBranchById(id);
    }

    public List<TrainingProgramme> findTrainingById(Integer id) {
        return iTrainingProgrammeRepository.findTrainingProgrammeById(id);
    }

    public List<DspCentre> getDspCentre() {
        return (List<DspCentre>) idspCentre.findAll();
    }

    public List<TrainingProgramme> getTrainingProgramme() {
        return (List<TrainingProgramme>) iTrainingProgrammeRepository.findAll();
    }
}

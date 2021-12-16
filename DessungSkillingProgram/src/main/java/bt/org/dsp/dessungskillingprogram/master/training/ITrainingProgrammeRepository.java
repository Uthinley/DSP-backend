package bt.org.dsp.dessungskillingprogram.master.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrainingProgrammeRepository extends JpaRepository<TrainingProgramme, Integer> {
    TrainingProgramme findAllById(Integer id);
    List<TrainingProgramme> findTrainingProgrammeById(Integer id);
}

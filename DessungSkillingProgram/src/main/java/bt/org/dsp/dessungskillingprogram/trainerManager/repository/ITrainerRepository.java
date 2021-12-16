package bt.org.dsp.dessungskillingprogram.trainerManager.repository;

import bt.org.dsp.dessungskillingprogram.common.CommonRepository;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrainerRepository extends JpaRepository<Trainers, Integer>, CommonRepository {
    List<Trainers> findAll();
}

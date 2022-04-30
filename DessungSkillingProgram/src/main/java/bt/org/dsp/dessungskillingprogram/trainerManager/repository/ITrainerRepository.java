package bt.org.dsp.dessungskillingprogram.trainerManager.repository;

import bt.org.dsp.dessungskillingprogram.common.CommonRepository;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrainerRepository extends JpaRepository<Trainers, Integer>, CommonRepository {
    List<Trainers> findAll();
    Trainers findAllById(Integer id);

    @Modifying
    @Query(value = "DELETE FROM trainer_dtls WHERE id = ?", nativeQuery = true)
    int deleteTrainerById(Integer id);
}

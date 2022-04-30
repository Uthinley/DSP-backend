package bt.org.dsp.dessungskillingprogram.master.CourseLevelMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseLevelMasterRepository extends JpaRepository<CourseLevelMaster, Integer> {
    CourseLevelMaster findAllById(Integer id);
}

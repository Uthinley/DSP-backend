package bt.org.dsp.dessungskillingprogram.studentManager.repository;

import bt.org.dsp.dessungskillingprogram.studentManager.model.Student;
import bt.org.dsp.dessungskillingprogram.studentManager.model.TracerStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITracerStudyRepository extends JpaRepository<TracerStudy, Integer> {
    @Query(value = "SELECT * FROM tracer_dtls WHERE student_id = ?",
            nativeQuery = true)
    List<TracerStudy> findTracerByStudentId(Integer id);


}

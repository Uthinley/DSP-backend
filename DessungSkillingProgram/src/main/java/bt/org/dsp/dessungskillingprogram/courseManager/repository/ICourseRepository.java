package bt.org.dsp.dessungskillingprogram.courseManager.repository;

import bt.org.dsp.dessungskillingprogram.common.CommonRepository;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer>, CommonRepository {
    List<Course> findAll();

    @Query(value = "SELECT * FROM course_dtls WHERE id = ?",
            nativeQuery = true)
    Course findCourseById(Integer id);

    @Modifying
    @Query(value = "DELETE FROM course_dtls WHERE id = ?", nativeQuery = true)
    int deleteCourseById(Integer id);

    @Modifying
    @Query(value = "INSERT INTO trainers_courses VALUES(?,?)", nativeQuery = true)
    void saveRelation(Integer trainerId, Integer courseId);

    @Query(value = "SELECT COUNT(*) FROM trainers_courses WHERE trainer_id = ? AND training_program_id =?", nativeQuery = true)
    int findTrainerExists(Integer trainerId, Integer trainingId);


}

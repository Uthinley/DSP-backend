package bt.org.dsp.dessungskillingprogram.master.course;

import bt.org.dsp.dessungskillingprogram.common.CommonRepository;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.GenericDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseMasterRepository extends JpaRepository<CourseMaster, Integer>, CommonRepository {
    CourseMaster findAllById(Integer id);

    @Query(value = "SELECT * FROM course_master WHERE department_id= ?", nativeQuery = true)
    List<CourseMaster> findRecommendedCourseByDeptId(Integer deptId);


}

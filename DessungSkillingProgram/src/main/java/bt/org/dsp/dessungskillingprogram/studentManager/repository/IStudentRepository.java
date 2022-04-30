package bt.org.dsp.dessungskillingprogram.studentManager.repository;

import bt.org.dsp.dessungskillingprogram.common.CommonRepository;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.GenericDTO;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.StudentDTO;
import bt.org.dsp.dessungskillingprogram.studentManager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer>, CommonRepository {

    @Query(value = "SELECT * FROM student_dtls WHERE cid = ?",
            nativeQuery = true)
    Student findStudentBycid(BigInteger cid);

    @Query(value = "SELECT * FROM students_courses WHERE student_id=? AND course_id=?",nativeQuery = true)
    Student findStudentInCourse(Integer studentId, Integer trainingId);

    @Query(value = "SELECT * FROM students_courses a INNER JOIN student_dtls b ON b.student_id = a.student_id INNER JOIN course_dtls c ON a.course_id = c.id WHERE a.course_id = ?",
            nativeQuery = true)
    List<Student> findStudentByCourseId(Integer id);

    @Query(value = "SELECT * FROM student_dtls WHERE cid LIKE %:stdInfo% OR name LIKE %:stdInfo% OR dessung_id LIKE %:stdInfo% ",nativeQuery = true)
    List<Student> findStudent(String stdInfo);

    @Modifying
    @Query(value = "DELETE FROM `students_courses` WHERE student_id=? AND course_id=?",nativeQuery = true)
    void deleteStdById(Integer id, Integer trainingId);


}

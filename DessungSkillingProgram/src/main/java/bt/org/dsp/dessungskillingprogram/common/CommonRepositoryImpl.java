package bt.org.dsp.dessungskillingprogram.common;

import bt.org.dsp.dessungskillingprogram.base.BaseDao;
import bt.org.dsp.dessungskillingprogram.studentManager.dto.GenericDTO;
import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CommonRepositoryImpl extends BaseDao implements CommonRepository{
    @Transactional
    public UserDTO findByUsername(String username) {
        String sqlQuery = "SELECT A.username username, A.password, A.group_id AS groupId, A.cid FROM sa_users A WHERE A.username=:username";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, UserDTO.class).setParameter("username", username);
        return (UserDTO) hQuery.uniqueResult();
    }

    @Transactional
    public GenericDTO getReport() {
        String sqlQuery = "SELECT (SELECT COUNT(*) FROM trainer_dtls) AS obj1,(SELECT COUNT(*) FROM course_dtls) AS obj2,(SELECT COUNT(*) FROM student_dtls) AS obj3,(SELECT COUNT(*) FROM sa_users) AS obj4, (SELECT CONCAT(ROUND(((SELECT COUNT(*) FROM student_dtls WHERE tracer != 1)/(SELECT COUNT(*) FROM student_dtls)*100),2))) AS obj5";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, GenericDTO.class);
        return (GenericDTO) hQuery.uniqueResult();
    }

    @Transactional
    public List<GenericDTO> getTotalCourseBySector(){
        String sqlQuery = "SELECT COUNT(*) AS obj1, c.department_name as obj2 FROM course_dtls a INNER JOIN course_master b ON a.course_master_id = b.id INNER JOIN department_master c ON b.department_id = c.id GROUP BY b.department_id ORDER BY 1 DESC ";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, GenericDTO.class);
        return (List<GenericDTO>)  hQuery.list();
    }

    @Transactional
    public GenericDTO getTotalbyCourseStatus(){
        String sqlQuery = "SELECT (SELECT COUNT(*) FROM course_dtls WHERE course_status = 'Completed' ) AS obj1,(SELECT COUNT(*) FROM course_dtls WHERE course_status = 'Future') AS obj2,(SELECT COUNT(*) FROM course_dtls WHERE course_status = 'On-going') AS obj3";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, GenericDTO.class);
        return (GenericDTO)  hQuery.uniqueResult();
    }

}

package bt.org.dsp.dessungskillingprogram.common;

import bt.org.dsp.dessungskillingprogram.base.BaseDao;
import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

public class CommonRepositoryImpl extends BaseDao implements CommonRepository{
    @Transactional
    public UserDTO findByUsername(String username) {
        String sqlQuery = "SELECT A.username username, A.password, A.group_id AS groupId FROM sa_users A WHERE A.username=:username";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, UserDTO.class).setParameter("username", username);
        return (UserDTO) hQuery.uniqueResult();
    }
}

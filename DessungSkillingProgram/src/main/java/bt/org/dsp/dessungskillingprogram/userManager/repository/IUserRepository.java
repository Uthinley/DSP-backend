package bt.org.dsp.dessungskillingprogram.userManager.repository;

import bt.org.dsp.dessungskillingprogram.common.CommonRepository;
import bt.org.dsp.dessungskillingprogram.userManager.dto.UserDTO;
import bt.org.dsp.dessungskillingprogram.userManager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer>, CommonRepository {

        Optional<Users> findByCid(String username);
        List<Users> findAll();

        @Query(value = "SELECT * FROM sa_users WHERE  username= ?",nativeQuery = true)
        Optional<Users> findByUser(String username);
//    UserDTO findByUsername(String username);

}

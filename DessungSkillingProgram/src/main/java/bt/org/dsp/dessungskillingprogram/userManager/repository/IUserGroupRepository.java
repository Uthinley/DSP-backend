package bt.org.dsp.dessungskillingprogram.userManager.repository;

import bt.org.dsp.dessungskillingprogram.userManager.model.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserGroupRepository extends CrudRepository<UserGroup, Integer> {
    UserGroup findAllById(Integer id);
}

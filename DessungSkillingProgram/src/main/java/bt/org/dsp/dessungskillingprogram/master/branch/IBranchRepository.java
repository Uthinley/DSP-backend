package bt.org.dsp.dessungskillingprogram.master.branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBranchRepository extends JpaRepository<Branch, Integer> {
    List<Branch> findBranchById(Integer id);
}

package bt.org.dsp.dessungskillingprogram.master.branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBranchRepository extends JpaRepository<Branch, Integer> {
    @Query(value = "SELECT * FROM branch_master WHERE department_id = ?", nativeQuery = true)
    List<Branch> findBranchById(Integer id);

    Branch findAllById(Integer id);
}

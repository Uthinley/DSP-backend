package bt.org.dsp.dessungskillingprogram.master.repository;

import bt.org.dsp.dessungskillingprogram.master.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    Department findAllById(Integer id);
}

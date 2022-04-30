package bt.org.dsp.dessungskillingprogram.studentManager.repository;

import bt.org.dsp.dessungskillingprogram.studentManager.model.DessungProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IDessungProfileRepository extends JpaRepository<DessungProfile, Integer> {

    @Query(value = "SELECT * FROM dessung_profile WHERE cid = ?",
            nativeQuery = true)
    DessungProfile findDessungBycid(BigInteger cid);
}

package bt.org.dsp.dessungskillingprogram.master.dspcenter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDSPCentre extends JpaRepository<DspCentre, Integer> {
    DspCentre findAllById(Integer id);
}

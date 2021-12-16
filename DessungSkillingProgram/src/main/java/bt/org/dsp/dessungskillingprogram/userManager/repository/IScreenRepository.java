package bt.org.dsp.dessungskillingprogram.userManager.repository;

import bt.org.dsp.dessungskillingprogram.userManager.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IScreenRepository extends JpaRepository<Screen, Integer> {
    List<Screen> findAll();
    Screen findAllById(Integer id);
}

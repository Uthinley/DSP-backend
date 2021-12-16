package bt.org.dsp.dessungskillingprogram.userManager.repository;

import bt.org.dsp.dessungskillingprogram.userManager.model.PasswordPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPasswordPolicyRepository extends JpaRepository<PasswordPolicy, Integer> {
}

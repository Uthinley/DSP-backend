package bt.org.dsp.dessungskillingprogram.userManager.service;

import bt.org.dsp.dessungskillingprogram.userManager.model.RefreshToken;
import bt.org.dsp.dessungskillingprogram.userManager.repository.IRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import bt.org.dsp.dessungskillingprogram.userManager.repository.IRefreshTokenRepository;

@Service
public class RefreshTokenService {

    @Autowired
    private IRefreshTokenRepository IRefreshTokenRepository;

    @Autowired
    public RefreshTokenService(IRefreshTokenRepository IRefreshTokenRepository) {
        this.IRefreshTokenRepository = IRefreshTokenRepository;
    }

    @Transactional
    public String generateRefreshToken() {
        String token = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setCreatedBy("currentUser");
        refreshToken.setCreatedDate(new Date());

//        IRefreshTokenRepository.save(refreshToken);
        return token;
    }

    void validateRefreshToken(String token) {
        IRefreshTokenRepository.findByToken(token);
       /* refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new OnlineRequisitionException("Invalid refresh Token"));*/
    }

    public void deleteRefreshToken(String token) {
        IRefreshTokenRepository.deleteByToken(token);
    }
}

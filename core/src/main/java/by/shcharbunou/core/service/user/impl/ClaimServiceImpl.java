package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.service.user.ClaimService;
import by.shcharbunou.dal.entity.user.Claim;
import by.shcharbunou.dal.repository.user.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("claimService")
@Transactional(transactionManager = "transactionManager")
public class ClaimServiceImpl implements ClaimService {
    private final ClaimRepository claimRepository;

    @Autowired
    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }


    @Override
    public Claim saveClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public void deleteClaim(Claim claim) {
        claimRepository.delete(claim);
    }

    @Override
    public List<Claim> findAllClaims() {
        return claimRepository.findAll();
    }
}

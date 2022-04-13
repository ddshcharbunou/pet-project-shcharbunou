package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.exception.ClaimDuplicateException;
import by.shcharbunou.core.exception.message.ClaimMessage;
import by.shcharbunou.core.service.user.ClaimService;
import by.shcharbunou.dal.entity.user.Claim;
import by.shcharbunou.dal.repository.user.ClaimRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service("claimService")
@Transactional(transactionManager = "transactionManager")
public class ClaimServiceImpl implements ClaimService {
    private final ClaimRepository claimRepository;

    @Autowired
    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
        log.debug("ClaimService initialized");
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

    @Override
    public Claim findClaimByGroupID(UUID id) {
        return claimRepository.findByGroupID(id);
    }

    @Override
    public Claim findClaimByUserID(UUID id) {
        return claimRepository.findByUserID(id);
    }

    @Override
    public void deleteAllClaims() {
        claimRepository.deleteAll();
    }

    @Override
    public Claim createClaim(UUID userID, UUID groupID) throws ClaimDuplicateException {
        Claim claim = new Claim();
        boolean isDuplicated = checkClaimDuplicate(userID);
        if (isDuplicated) {
            throw new ClaimDuplicateException(ClaimMessage.CLAIM_DUPLICATE.getMessage());
        }
        claim.setUserID(userID);
        claim.setGroupID(groupID);
        return claim;
    }

    private boolean checkClaimDuplicate(UUID userID) {
        Claim testClaim = claimRepository.findByUserID(userID);
        return Objects.nonNull(testClaim);
    }
}

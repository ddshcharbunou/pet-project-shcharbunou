package by.shcharbunou.core.service.user;

import by.shcharbunou.core.exception.ClaimDuplicateException;
import by.shcharbunou.dal.entity.user.Claim;

import java.util.List;
import java.util.UUID;

/**
 * Service for Claim entity.
 */
public interface ClaimService {
    /**
     * Save claim in db.
     * @param claim claim
     * @return claim {@link Claim}
     */
    Claim saveClaim(Claim claim);

    /**
     * Delete claim.
     * @param claim claim
     */
    void deleteClaim(Claim claim);

    /**
     * Find all claims in db.
     * @return list of claims {@link Claim}
     */
    List<Claim> findAllClaims();

    /**
     * Find claim by group id.
     * @param id group id
     * @return claim {@link Claim}
     */
    Claim findClaimByGroupID(UUID id);

    /**
     * Find claim by user id.
     * @param id user id
     * @return claim {@link Claim}
     */
    Claim findClaimByUserID(UUID id);

    /**
     * Delete all claims.
     */
    void deleteAllClaims();

    /**
     * Create claim.
     * @param userID user id
     * @param groupID group id
     * @return claim {@link Claim}
     * @throws ClaimDuplicateException claim duplicated
     */
    Claim createClaim(UUID userID, UUID groupID) throws ClaimDuplicateException;
}

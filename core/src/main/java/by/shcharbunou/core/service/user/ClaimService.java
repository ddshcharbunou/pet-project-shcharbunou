package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.entity.user.Claim;

import java.util.List;

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
}

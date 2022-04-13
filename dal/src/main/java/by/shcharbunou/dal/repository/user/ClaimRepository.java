package by.shcharbunou.dal.repository.user;


import by.shcharbunou.dal.entity.user.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Claim entity.
 */
@Repository("claimRepository")
public interface ClaimRepository extends JpaRepository<Claim, UUID> {
    Claim findByGroupID(UUID id);

    Claim findByUserID(UUID id);
}

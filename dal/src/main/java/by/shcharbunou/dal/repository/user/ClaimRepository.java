package by.shcharbunou.dal.repository.user;


import by.shcharbunou.dal.entity.user.Claim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Claim entity.
 */
@Repository("claimRepository")
public interface ClaimRepository extends JpaRepository<Claim, UUID> {
    List<Claim> findByGroupID(UUID id);

    Claim findByUserID(UUID id);

    Page<Claim> findByGroupID(UUID id, Pageable pageable);
}

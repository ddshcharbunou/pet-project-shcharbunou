package by.shcharbunou.dal.repository.user.office.material;

import by.shcharbunou.dal.entity.user.office.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Material entity.
 */
@Repository("materialRepository")
public interface MaterialRepository extends JpaRepository<Material, UUID> {
}

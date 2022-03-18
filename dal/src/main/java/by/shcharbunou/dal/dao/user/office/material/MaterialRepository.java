package by.shcharbunou.dal.dao.user.office.material;

import by.shcharbunou.dal.entity.user.office.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaterialRepository extends JpaRepository<Material, UUID> {
}

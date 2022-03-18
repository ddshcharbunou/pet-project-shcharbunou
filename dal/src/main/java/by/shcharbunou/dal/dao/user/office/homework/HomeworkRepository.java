package by.shcharbunou.dal.dao.user.office.homework;

import by.shcharbunou.dal.entity.user.office.homework.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomeworkRepository extends JpaRepository<Homework, UUID> {
}

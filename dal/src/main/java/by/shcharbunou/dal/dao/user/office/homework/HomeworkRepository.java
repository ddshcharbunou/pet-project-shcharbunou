package by.shcharbunou.dal.dao.user.office.homework;

import by.shcharbunou.dal.entity.user.office.homework.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("homeworkRepository")
public interface HomeworkRepository extends JpaRepository<Homework, UUID> {
}

package dev.twominutes.notetaking.repository;

import dev.twominutes.notetaking.models.Goal;
import dev.twominutes.notetaking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);
    List<Goal> findByUserAndType(User user, String type);
}

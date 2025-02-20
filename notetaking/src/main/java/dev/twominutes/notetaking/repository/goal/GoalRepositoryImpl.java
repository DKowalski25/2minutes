package dev.twominutes.notetaking.repository.goal;

import dev.twominutes.notetaking.dto.goal.GoalDto;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GoalRepositoryImpl implements GoalRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static class GoalRowMapper implements RowMapper<GoalDto> {
        @Override
        public GoalDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return GoalDto.builder()
                    .id(rs.getLong("id"))
                    .description(rs.getString("description"))
                    .deadline(rs.getDate("deadline").toLocalDate())
                    .type(rs.getString("type"))
                    .userId(rs.getLong("user_id"))
                    .build();
        }
    }

    @Override
    public GoalDto save(GoalDto goal) {
        String sql = "INSERT INTO goals (description, deadline, type, user_id) " +
                "VALUES (:description, :deadline, :type, :userId) RETURNING id";

        Long generatedId = jdbcTemplate.queryForObject(sql, Map.of(
                "description", goal.getDescription(),
                "deadline", java.sql.Date.valueOf(goal.getDeadline()),
                "type", goal.getType(),
                "userId", goal.getUserId()
        ), Long.class);

        goal.setId(generatedId);
        return goal;
    }

    @Override
    public Optional<GoalDto> findById(Long id) {
        String sql = "SELECT * FROM goals WHERE id = :id";
        List<GoalDto> results = jdbcTemplate.query(sql, Map.of("id", id), new GoalRowMapper());
        return results.stream().findFirst();
    }

    @Override
    public List<GoalDto> findByUserId(Long userId) {
        String sql = "SELECT * FROM goals WHERE user_id = :userId";
        return jdbcTemplate.query(sql, Map.of("userId", userId), new GoalRowMapper());
    }

    @Override
    public List<GoalDto> findByUserAndType(Long userId, String type) {
        String sql = "SELECT * FROM goals WHERE user_id = :userId AND type = :type";
        return jdbcTemplate.query(sql, Map.of("userId", userId, "type", type), new GoalRowMapper());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM goals WHERE id = :id";
        jdbcTemplate.update(sql, Map.of("id", id));
    }
}
package dev.twominutes.notetaking.repository.note;

import dev.twominutes.notetaking.models.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoteRepositoryImpl implements NoteRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static class NoteRowMapper implements RowMapper<Note> {
        @Override
        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Note.builder()
                    .id(rs.getLong("id"))
                    .userId(rs.getLong("user_id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                    .updateAt(rs.getTimestamp("update_at").toLocalDateTime())
                    .build();
        }
    }

    @Override
    public Note save(Note note) {
        String sql = "INSERT INTO notes (user_id, title, content, created_at, updated_at)" +
                " VALUES (:userId, :title, :content, :createdAt, :updatedAt) RETURNING id";
        Map<String, Object> params = Map.of(
                "userId", note.getUserId(),
                "title", note.getTitle(),
                "content", note.getContent(),
                "createAt", note.getCreateAt(),
                "updateAt", note.getUpdateAt()
        );
        Long generatedId = jdbcTemplate.queryForObject(sql, params, Long.class);
        note.setId(generatedId);
        return note;
    }

    @Override
    public Optional<Note> findById(Long id) {
        String sql = "SELECT * FROM notes WHERE id = :id";
        List<Note> results = jdbcTemplate.query(sql, Map.of("id", id), new NoteRowMapper());
        return results.stream().findFirst();
    }

    @Override
    public List<Note> findByUserId(Long userId) {
        String sql = "SELECT * FROM notes WHERE user_id = :userId";
        return jdbcTemplate.query(sql, Map.of("userId", userId), new NoteRowMapper());
    }

    @Override
    public void update(Note note) {
        String sql = "UPDATE notes SET title = :title, content = :content, updated_at = :updateAt WHERE id = :id";

        Map<String, Object> params = Map.of(
                "title", note.getTitle(),
                "content", note.getContent(),
                "updateAt", note.getUpdateAt(),
                "id", note.getId()
        );

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM notes WHERE id = :id";
        jdbcTemplate.update(sql, Map.of("id", id));

    }
}

package org.stopcode1.backendstudenti.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.stopcode1.backendstudenti.exception.DatabaseException;
import org.stopcode1.backendstudenti.model.Student;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static String SELECT_ALL = """
        SELECT idStudent, firstName, lastName, matricola, age, university
        FROM studenti
        """;

    private final static String SELECT_BY_ID = """
            SELECT idStudent, firstName, lastName, matricola, age, university
            FROM studenti
            WHERE idStudent = ?
            """;

    private final static String INSERT = """
            INSERT INTO studenti (firstName, lastName, matricola, age, university)
            VALUES (?, ?, ?, ?, ?)
            """;


    private final static String DELETE_BY_ID = """
            DELETE
            FROM studenti
            WHERE idStudent = ?
            """;

    private final static String UPDATE_BY_ID = """
            UPDATE studenti
            SET firstName = ?,
                lastName = ?,
                matricola = ?,
                age = ?,
                university = ?
            WHERE idStudent = ?
            """;

    private final RowMapper<Student> ROW_MAPPER_STUDENT =
            (rs, rowNum) -> {
                return new Student(
                        rs.getLong("idStudent"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("matricola"),
                        rs.getInt("age"),
                        rs.getString("university")
                );
    };

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Student> findAll() {

        return jdbcTemplate.query(SELECT_ALL, ROW_MAPPER_STUDENT);
    }

    public Optional<Student> findById(long id) {
    try {
        Student student =  jdbcTemplate.queryForObject(SELECT_BY_ID, ROW_MAPPER_STUDENT, id);
        return Optional.of(student);
    } catch (EmptyResultDataAccessException exception) {
        return Optional.empty();
    }

    }

    public long save(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int affectedRows = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            INSERT,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, student.getFirstName());
                    ps.setString(2, student.getLastName());
                    ps.setString(3, student.getMatricola());
                    ps.setInt(4, student.getAge());
                    ps.setString(5, student.getUniversity());
                    return ps;
                }, keyHolder
        );

        if(affectedRows != 1) {
           throw new DatabaseException("Studente non inserito nel DB");
        }

        Number generatedKey = keyHolder.getKey();

        if(generatedKey == null) {
            throw new DatabaseException("Impossibile recuperare l'identificativo dello studente");
        }
        return generatedKey.longValue();


    }


    public int deleteById(long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }

    public int update(Student student) {
        return jdbcTemplate.update(UPDATE_BY_ID,
                student.getFirstName(),
                student.getLastName(),
                student.getMatricola(),
                student.getAge(),
                student.getUniversity(),
                student.getId()
        );
    }

}

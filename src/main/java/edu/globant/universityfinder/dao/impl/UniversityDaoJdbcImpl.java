package edu.globant.universityfinder.dao.impl;

import edu.globant.universityfinder.dao.UniversityDao;
import edu.globant.universityfinder.model.Career;
import edu.globant.universityfinder.model.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Created by federico.calarco on 8/26/2016.
 */


public class UniversityDaoJdbcImpl implements UniversityDao {

    private static final Logger logger = LogManager.getLogger(UniversityDaoJdbcImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public University save(University university) {

        validateNotNull(university);

        String query = "INSERT INTO universities (name,location,address,webpage,phone,email) VALUES (:name, :location, :address, :webpage, :phone, :email)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", university.getName())
                .addValue("location", university.getLocation())
                .addValue("address", university.getAddress())
                .addValue("webpage", university.getWebpage())
                .addValue("phone", university.getPhone())
                .addValue("email", university.getEmail());

        int affectedRows = namedParameterJdbcTemplate.update(query, parameterSource, keyHolder);


        //TODO : If affectedRows == 0 -> Creating university failed, no rows affected (throw sqlException)

        if (affectedRows != 0) {

            logger.debug("affectedRows != 0");

            Long universityId = null;

            if (keyHolder != null) {
                universityId = keyHolder.getKey().longValue();
            }
            logger.debug("universityId: " + universityId);
            return this.getById(universityId);
        } else {
            //TODO: if affectedRows == 0 -> throw database_error exception
            return null;
        }

    }

    public Career saveCareer(Career career) {

        String sql = "INSERT INTO careers (nameCareer) VALUES (:nameCareer)";

        SqlParameterSource namedParameters = new MapSqlParameterSource("nameCareer", career.getName());

        KeyHolder holder = new GeneratedKeyHolder();

        int affectedRows = namedParameterJdbcTemplate.update(sql, namedParameters, holder);

        System.out.println(holder.getKey().longValue());


        return null;

    }

    @Override
    public University getById(Long universityId) {

        String sql = "SELECT * FROM universities WHERE id = :universityId";

        SqlParameterSource namedParameters = new MapSqlParameterSource("universityId", Long.valueOf(universityId));

        University university = (University) namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new UniversityRowMapper());

        //TODO: Is this the best way?

        university.setCareers((ArrayList) retrieveUniversityCareers(universityId));

        logger.debug("university returned: " + university.getId());

        return university;
    }


    Collection<Career> retrieveUniversityCareers(Long universityId) {

        String sql = "SELECT careers.id, careers.nameCareer FROM careers INNER JOIN universities_careers ON careers.id = universities_careers.id_careers INNER JOIN universities ON universities_careers.id_universities = universities.id where universities.id = :universityId";

        SqlParameterSource namedParameters = new MapSqlParameterSource("universityId", Long.valueOf(universityId));

        List<Career> careers = namedParameterJdbcTemplate.query(sql, namedParameters, new CareerRowMapper());

        return careers;
    }

    @Override
    public List<University> getAll() {

        String sql = "SELECT * FROM universities";

        List<University> universities = namedParameterJdbcTemplate.query(sql, new UniversityRowMapper());

        // retrieve careers of universities
        if (universities != null) {

            for (University u : universities) {
                u.setCareers((ArrayList) retrieveUniversityCareers(u.getId()));
            }
        }

        return universities;
    }

    @Override
    public void delete(Long universityId) {

        String query = "DELETE FROM universities WHERE id = :universityId";

        SqlParameterSource namedParameters = new MapSqlParameterSource("universityId", Long.valueOf(universityId));

        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public University update(University university) {
        return null;
    }


    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private void validateNotNull(University university) {
        if (university == null) {

            //TODO: throw new UniversityNotFoundException

            throw new NullPointerException();
        }

    }


    private class UniversityRowMapper implements RowMapper<University> {

        @Override
        public University mapRow(ResultSet resultSet, int i) throws SQLException {

            University university = new University();

            university.setId(resultSet.getLong("id"));
            university.setName(resultSet.getString("name"));
            university.setLocation(resultSet.getString("location"));
            university.setAddress(resultSet.getString("address"));
            university.setWebpage(resultSet.getString("webpage"));
            university.setPhone(resultSet.getString("phone"));
            university.setEmail(resultSet.getString("email"));


            return university;
        }

    }


    private class CareerRowMapper implements RowMapper<Career> {


        @Override
        public Career mapRow(ResultSet resultSet, int i) throws SQLException {

            Career career = new Career();

            career.setId(resultSet.getLong("id"));
            career.setName(resultSet.getString("nameCareer"));

            return career;
        }
    }


}







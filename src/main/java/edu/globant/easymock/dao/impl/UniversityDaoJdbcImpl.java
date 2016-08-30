package edu.globant.easymock.dao.impl;

import edu.globant.easymock.dao.UniversityDao;
import edu.globant.easymock.model.Career;
import edu.globant.easymock.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by federico.calarco on 8/26/2016.
 */


public class UniversityDaoJdbcImpl implements UniversityDao {

    private static final String fetchAllUniversityiesQuery = "SELECT * FROM universities";


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public University save(University university) {

        String query = "INSERT INTO universities (name,location,address,webpage,phone,email) VALUES (:name, :location, :address, :webpage, :phone, :email)";


        Map namedParameters = new HashMap();

        namedParameters.put("name", university.getName());
        namedParameters.put("location", university.getLocation());
        namedParameters.put("address", university.getAddress());
        namedParameters.put("webpage", university.getWebpage());
        namedParameters.put("phone", university.getPhone());
        namedParameters.put("email", university.getEmail());


        namedParameterJdbcTemplate.update(query, namedParameters);

        return university;
    }

    @Override
    public University getById(Long universityId) {

        String query = "SELECT * FROM universities WHERE id = :universityId";

        String query2 = "SELECT u.name, c.name FROM universities as u INNER JOIN careers as c ON u.id = c.id INNER JOIN universities_careers as uc ON u.id = c.id WHERE uc.id_universities = :universityId";

        SqlParameterSource namedParameters = new MapSqlParameterSource("universityId", Long.valueOf(universityId));

        University university = (University) namedParameterJdbcTemplate.queryForObject(query2, namedParameters, new UniversityRowMapper());

        return university;
    }

    @Override
    public List<University> getAll() {

        List<University> universities = namedParameterJdbcTemplate.query(fetchAllUniversityiesQuery, new UniversityRowMapper());

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

            Career career = new Career();

            career.setName(resultSet.getString("nameCareer"));

            return university;
        }

    }


}







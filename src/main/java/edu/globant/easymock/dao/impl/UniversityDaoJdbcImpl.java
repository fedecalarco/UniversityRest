package edu.globant.easymock.dao.impl;

import edu.globant.easymock.dao.UniversityDao;
import edu.globant.easymock.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by federico.calarco on 8/26/2016.
 */


public class UniversityDaoJdbcImpl implements UniversityDao {

    private static final String fetchAllUniversityiesQuery = "SELECT * FROM universities";


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public University save(University university) {
        return null;
    }

    @Override
    public University getById(Long universityId) {
        return null;
    }

    @Override
    public Collection<University> getAll() {

        List<University> universities = (List) namedParameterJdbcTemplate.query(fetchAllUniversityiesQuery, new UniversityRowMapper() );


        return universities;
    }

    @Override
    public void delete(Long universityId) {

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

            return university;
        }

    }


}







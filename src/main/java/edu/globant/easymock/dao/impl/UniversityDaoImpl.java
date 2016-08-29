package edu.globant.easymock.dao.impl;

import edu.globant.easymock.dao.UniversityDao;
import edu.globant.easymock.model.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;

/**
 * Created by federico.calarco on 8/18/2016.
 */
public class UniversityDaoImpl implements UniversityDao {

    private static final Logger logger = LogManager.getLogger(UniversityDaoImpl.class);

    DataSource dataSource;

    final String saveSQL = "INSERT INTO UNIVERSITIES " + "(ID, NAME) VALUES (?, ?)";
    final String getByIdSQL = "SELECT * FROM UNIVERSITIES WHERE ID = ?";
    final String getAllSQL = "SELECT * FROM UNIVERSITIES";

    //TODO: add careerDao...
    final String CONSULT_EXIST_CAREER = "SELECT id FROM careers c WHERE c.name = ?";

    final String SAVE_QUERY= "INSERT INTO universities (name,location,address,webpage,phone,email) VALUES(?,?,?,?,?,?)";

    final String ADD_RELATIONSHIP = "INSERT INTO universities_careers (id_university, id_career)";

    final String SAVE_CAREER = "INSERT INTO careers (name) VALUES (?)";

    @Override
    public University save(University university) {

        Connection connection = null;

        try {

            connection = dataSource.getConnection();


            PreparedStatement ps = connection.prepareStatement(saveSQL);

            ps.setLong(1, university.getId());
            ps.setString(2, university.getName());

            ps.executeUpdate();

            ps.close();

            return university;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public University getById(Long universityId) {
        Connection connection = null;


        try {

            connection = dataSource.getConnection();


            PreparedStatement ps = connection.prepareStatement(getByIdSQL);

            ps.setLong(1, universityId);


            University university = null;

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                university = new University();

                university.setId(rs.getLong("ID"));
                university.setName(rs.getString("NAME"));
            }
            rs.close();
            ps.close();

            return university;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public Collection<University> getAll() {

        List<University> universityList = null;

        Connection connection = null;


        try {

            connection = dataSource.getConnection();


            PreparedStatement ps = connection.prepareStatement(getAllSQL);


            universityList = new ArrayList<>();
            University university;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {



                university = new University();

                university.setId(rs.getLong("ID"));
                university.setName(rs.getString("NAME"));

                universityList.add(university);


            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }

        return universityList;
    }

    @Override
    public void delete(Long universityId) {

    }

    @Override
    public University update(University university) {
        return null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

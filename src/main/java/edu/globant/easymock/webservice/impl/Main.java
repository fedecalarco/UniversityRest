package edu.globant.easymock.webservice.impl;

import edu.globant.easymock.model.Career;
import edu.globant.easymock.model.University;
import edu.globant.easymock.service.UniversityService;
import edu.globant.easymock.service.impl.UniversityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico.calarco on 8/23/2016.
 */
public class Main {


    public static void main(String[] args) {

        Career career = new Career(2l,"Architecture");

        List<Career> careerList = new ArrayList<>();

        University university = new University(2L,"UNLP","LA PLATA","55 nro 648","unlp.edu.ar", "4451531", "unlp@edu.ar",careerList);


        ApplicationContext context = new ClassPathXmlApplicationContext("UniversityFinderContext.xml");

        UniversityService universityService =(UniversityServiceImpl) context.getBean("universityService");


        universityService.save(university);

    //    System.out.println(university.getName());


        University unn = universityService.getById(2L);

        if(unn == null){
            System.out.println("Hello");
        }
        System.out.println(unn.getName() + " -  " + unn.getId());


    }


    static void persist(String string) {

        try {

            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/test", "test", "");
            Statement stmt = con.createStatement();

            stmt.executeUpdate("CREATE TABLE table1 ( user varchar(50) )");
            stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Claudio' )");
            stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Bernasconi' )");

            ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
            while (rs.next()) {
                String name = rs.getString("user");
                System.out.println(name);
            }
            stmt.close();
            con.close();


        } catch (Exception ex) {
            System.out.println( ex.getMessage() );
        }

    }
}

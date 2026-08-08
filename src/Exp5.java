package com.test.stm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exp5 {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:h2:./StudentDB",
                    "sa",
                    ""
            );

            Statement stmt = con.createStatement();

            stmt.execute("CREATE TABLE IF NOT EXISTS STUDENT (ID INT PRIMARY KEY, NAME VARCHAR(50), EMAIL VARCHAR(100))");
            stmt.execute("DELETE FROM STUDENT");

            stmt.executeUpdate(
                    "INSERT INTO STUDENT VALUES "
                    + "(1, 'Ajay', 'ajay@gmail.com')"
            );

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM STUDENT WHERE ID = 1"
            );

            if (rs.next()) {

                System.out.println("Record Exists");
                System.out.println("ID    : " + rs.getInt("ID"));
                System.out.println("Name  : " + rs.getString("NAME"));
                System.out.println("Email : " + rs.getString("EMAIL"));
                System.out.println("TEST PASSED");

            } else {

                System.out.println("Record Not Found");
                System.out.println("TEST FAILED");
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 02 - JobSkills
 * Student(s) Name(s):Alejandro Rojas
 */

import java.io.IOException;
import java.util.Properties;
import java.io.*;
import java.sql.*;
import java.util.Scanner;


public class JobSkills {

    public static String DATASET = "data/job_skills.csv";
    public static String data;


    public static void main(String[] args) throws IOException, SQLException {


        // TODO: load database properties
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        String server = prop.getProperty("server");
        String database = prop.getProperty("database");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String connectURL = "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&user=" + user + "&password=" + password;

        // TODO: connect to the database

        Connection conn = DriverManager.getConnection(connectURL);
        System.out.println("Connection to MySQL database " + database + " was successful!");


        // TODO: complete the data load
        System.out.println("Creating statement...");
        File file = new File(DATASET);
        PreparedStatement insertSKILL;
        PreparedStatement insertPOSITIONS;
        PreparedStatement insertPositionSkill;
        try (Scanner sc = new Scanner(file)) {
            insertSKILL = conn.prepareStatement("INSERT ignore INTO SKILL (id , SkillName) values (?, ?)");
            insertPOSITIONS = conn.prepareStatement("INSERT ignore INTO POSITIONS (id, PositionName) values (?, ?)");
            insertPositionSkill = conn.prepareStatement("INSERT ignore INTO PositionSkill (SkillID,PositionID) values (?, ?)");
            conn.setAutoCommit(false);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] position = line.split(",");
                String[] Id = position[0].split(":");
                int positionId = Integer.parseInt(Id[0]);
                String SkillName = Id[1];
                String[] skillsArray = position[1].split(";");
                insertSKILL.setInt(1, positionId);
                insertSKILL.setString(2, SkillName);
                insertSKILL.addBatch();
                for (int i = 0, skillsArrayLength = skillsArray.length; i < skillsArrayLength; i++) {
                    String skillAndId = skillsArray[i];
                    String[] PositionName = skillAndId.split(":");
                    int skillId = Integer.parseInt(PositionName[0]);
                    String skillName = PositionName[1];
                    insertPOSITIONS.setInt(1, skillId);
                    insertPOSITIONS.setString(2, skillName);
                    insertPositionSkill.setInt(1, positionId);
                    insertPositionSkill.setInt(2, skillId);
                    insertPOSITIONS.addBatch();
                    insertPositionSkill.addBatch();
                }
            }
        }
        insertSKILL.executeBatch();
        insertPOSITIONS.executeBatch();
        insertPositionSkill.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);
        System.out.println("done!");
        conn.close();

    }
}

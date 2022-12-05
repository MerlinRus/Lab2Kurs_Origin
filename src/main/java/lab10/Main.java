package lab10;

import java.sql.*;

public class Main {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_students",
                    "postgres", "AlevTinaGreat1");

            statement = connection.createStatement();

            System.out.println("Query 1 :");
            QueryOne(statement);

            System.out.println("\nQuery 2 :");
            QueryTwo(statement);

            System.out.println("\nQuery 3 :");
            QueryThree(statement);

            System.out.println("\nQuery 4 :");
            QueryFour(statement);

            System.out.println("\nQuery 5 :");
            QueryFive(statement);
        }
        catch (SQLException e) {
            System.out.println("Connection to java_students database failed..");
            throw new RuntimeException(e);
        }
    }


    public static void QueryOne(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("SELECT stud.name as studName, subj.name as subjName, p.rating " +
                "FROM students AS stud JOIN progress AS p ON stud.id = p.id_student JOIN subjects AS subj " +
                "ON p.id_subject = subj.id WHERE p.rating >= 3 AND p.id_subject = 2;"
        );

        while (resultSet.next()) {
            String studName = resultSet.getString("studName");
            String subjName = resultSet.getString("subjName");
            int rating = resultSet.getInt("rating");

            System.out.printf("%s %s %d\n", studName, subjName, rating);
        }
    }

    public static void QueryTwo(Statement statement) throws SQLException{
        resultSet = statement.executeQuery("SELECT subjects.name, AVG(progress.rating) as avg FROM progress " +
                "JOIN subjects ON progress.id_subject = subjects.id WHERE id_subject = 2 GROUP BY subjects.name;"
        );

        while (resultSet.next()) {
            String subjName = resultSet.getString("name");
            float avgRating = resultSet.getFloat("avg");

            System.out.printf("%s %f\n", subjName, avgRating);
        }
    }


    public static void QueryThree(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("SELECT students.name, AVG(progress.rating) as avg FROM progress JOIN subjects " +
                "ON progress.id_subject = subjects.id JOIN students ON students.id = progress.id_student WHERE " +
                "id_student = 1 GROUP BY students.name;"
        );

        while (resultSet.next()) {
            String studName = resultSet.getString("name");
            float avgRating = resultSet.getFloat("avg");

            System.out.printf("%s %f\n", studName, avgRating);
        }
    }


    public static void QueryFour(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("SELECT subj.name FROM subjects AS subj JOIN progress " +
                "ON subj.id = progress.id_subject WHERE (SELECT AVG(rating)\n" +
                "FROM progress JOIN subjects ON subj.id = progress.id_subject) >= 3 GROUP BY subj.name;"
        );

        while (resultSet.next()) {
            String subjName = resultSet.getString("name");

            System.out.printf("%s\n", subjName);
        }
    }

    public static void QueryFive(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("SELECT stud.name as studName, subj.name as subjName, p.rating " +
                "FROM students AS stud JOIN progress AS p ON stud.id = p.id_student JOIN subjects AS subj " +
                "ON p.id_subject = subj.id WHERE p.rating > 3 limit 3 offset 2;");

        while (resultSet.next()) {
            String studName = resultSet.getString("studName");
            String subjName = resultSet.getString("subjName");
            int rating = resultSet.getInt("rating");

            System.out.printf("%s %s %d\n", studName, subjName, rating);
        }
    }
}


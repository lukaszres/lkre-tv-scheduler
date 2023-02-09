package pl.lker.tv.scheduler.database;

import pl.lker.tv.scheduler.model.Seance;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private Connection connection;
    private Statement statement;

    public RepositoryImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:scheduler.db");
            statement = connection.createStatement();
            createTables();
        } catch (SQLException e) {
            System.err.println("Cannot open connection");
            e.printStackTrace();
        }
    }

    public boolean createTables() {
        String createSeances = "CREATE TABLE IF NOT EXISTS seances (id INTEGER PRIMARY KEY AUTOINCREMENT, title varchar(255), date DATE, genre varchar(255), episode varchar(255), channel" +
                " varchar(255))";
        try {
            statement.execute(createSeances);
        } catch (SQLException e) {
            System.err.println("Table 'seances' creation error");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean insertSeance(Seance seance) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into seances values (NULL, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, seance.getTitle());
            prepStmt.setDate(2, new java.sql.Date(seance.getTime().getTime()));
            prepStmt.setString(3, seance.getGenre());
            prepStmt.setString(4, seance.getEpisode());
            prepStmt.setString(5, seance.getChannel());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu czytelnika");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean insertSeances(List<Seance> seance) {
        return false;
    }

    @Override
    public List<Seance> selectSeances() {
        List<Seance> seances = new LinkedList<Seance>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM seances");
            int id;
            String title, genre, episode, channel;
            Date date;
            while (result.next()) {
                id = result.getInt("id");
                title = result.getString("title");
                date = result.getDate("date");
                genre = result.getString("genre");
                episode = result.getString("episode");
                channel = result.getString("channel");
                seances.add(new Seance(title, date, genre, episode, channel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return seances;
    }

}

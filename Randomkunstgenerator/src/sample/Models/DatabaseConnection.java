package sample.Models;

/**
 * Created by Gebruiker on 1-3-2017.
 */

import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.scene.paint.Color;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class DatabaseConnection {
    //String dbUrl = "jdbc:mysql://localhost:3306/Company?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "root";

    private Connection connection;
    private Properties properties;

    //Create properties

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", pass);
        }
        return properties;
    }

    //connect database

    public static Connection connect() {
        try {
//                    Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Javatest", "koen");
            //"jdbc:oracle://studmysql01.fhict.local/dbi339814?autoReconnect=true&useSSL=false&useUnicode=true"
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //for testing purposes
    public String getName() {
        String sql = "Select * from Picture";
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            //preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                return name;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            disconnect();
        }
        return null;
    }

    public ArrayList<String> Getnames() {
        ArrayList<String> list = new ArrayList<>();

        String sql = "Select * from Picture";
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            //preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                list.add(name);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            disconnect();
        }
        return null;
    }

    //insert picture
    public void insertPicture(String Name) {

        int ID = Gethighest("Picture") + 1;

        String sql = "Insert into Picture"
                + "(ID, NAME)" +
                "values(?, ?)";
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, Name);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void insertDrawable(Drawable draw) {

        int IDpic = Gethighest("Picture");
        int ID = Gethighest("Drawable") + 1;

        String sql = "Insert into drawable"
                + "(ID, CorX, CorY, Linethinkness, pictureID)" +
                "values(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            preparedStatement.setInt(2, draw.GetCorX());
            preparedStatement.setInt(3, draw.GetCorY());
            preparedStatement.setInt(4, draw.GetLinethinkness());
            preparedStatement.setInt(5, IDpic);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void insertCircle(Circle cir) {
        int IDdraw = Gethighest("Drawable");
        int ID = Gethighest("Circle") + 1;

        String sql = "Insert into circle"
                + "(ID,heigth, width, drawableID)" +
                "values(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            preparedStatement.setInt(2, cir.GetHeight());
            preparedStatement.setInt(3, cir.GetWidth());
            preparedStatement.setInt(4, IDdraw);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void insertRectangle(Rectangle rec) {
        int IDdraw = Gethighest("Drawable");
        int ID = Gethighest("Rectangle") + 1;

        String sql = "Insert into rectangle"
                + "(ID,heigth, width, drawableID)" +
                "values(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            preparedStatement.setInt(2, rec.GetHeight());
            preparedStatement.setInt(3, rec.GetWidth());
            preparedStatement.setInt(4, IDdraw);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void insertLine(Line line) {
        int IDdraw = Gethighest("Drawable");
        int ID = Gethighest("Line") + 1;

        String sql = "Insert into line"
                + "(ID,X2, Y2, drawableID)" +
                "values(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            preparedStatement.setInt(2, line.GetX2());
            preparedStatement.setInt(3, line.GetY2());
            preparedStatement.setInt(4, IDdraw);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public ArrayList<Drawable> getdrawables(String name) {
        ArrayList<Drawable> list = new ArrayList<>();

        //hier moet het laatste gebeuren

        //circles
        try {
            String sql = "select * from Drawable d join Circle c on d.ID = c.DRAWABLEID join picture p on d.PICTUREID = p.ID Where p.NAME = ?";
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int CorX = resultSet.getInt("CORX");
                int CorY = resultSet.getInt("CORY");
                int linethinkness = resultSet.getInt("Linethinkness");
                int width = resultSet.getInt("Width");
                int heigth = resultSet.getInt("Heigth");

                Circle cir = new Circle(CorX, CorY, Color.BLUE, Color.BLUE, linethinkness, width, heigth);
                list.add(cir);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            disconnect();
        }

        //rectangles
        try {
            String sql = "select * from Drawable d join Rectangle c on d.ID = c.DRAWABLEID join picture p on d.PICTUREID = p.ID Where p.NAME = ?";
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int CorX = resultSet.getInt("CORX");
                int CorY = resultSet.getInt("CORY");
                int linethinkness = resultSet.getInt("Linethinkness");
                int width = resultSet.getInt("Width");
                int heigth = resultSet.getInt("Heigth");

                Rectangle rec = new Rectangle(CorX, CorY, Color.RED, Color.RED, linethinkness, width, heigth);
                list.add(rec);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            disconnect();
        }

        //Line
        try {
            String sql = "select * from Drawable d join Line c on d.ID = c.DRAWABLEID join picture p on d.PICTUREID = p.ID Where p.NAME = ?";
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int CorX = resultSet.getInt("CORX");
                int CorY = resultSet.getInt("CORY");
                int linethinkness = resultSet.getInt("Linethinkness");
                int X2 = resultSet.getInt("X2");
                int Y2 = resultSet.getInt("Y2");

                Line line = new Line(CorX, CorY, Color.BLACK, Color.BLACK, linethinkness, X2, Y2);
                list.add(line);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            disconnect();
        }

        return list;
    }

    //get highest review because i am too lazy to use PLSQL
    public int Gethighest(String Tablename) {
        String sql = "Select max(ID) AS max from " + Tablename;
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            //preparedStatement.setString(1,Tablename);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("max");
                return ID;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        } finally {
            disconnect();
        }
        return 0;
    }

}

package de.blacklinerdev.monitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Monitor {

    private final Properties props;
    //private Properties queries;
    private List<Exam> exams;

    public Monitor() throws IOException, ClassNotFoundException {
        InputStream is;
        props = new Properties();
        try {
            is = new FileInputStream("/home/leroy/Projekte/Monitor/src/main/java/de/blacklinerdev/monitor/config.properties");
            props.load(is);
            is = new FileInputStream("/home/leroy/Projekte/Monitor/src/main/java/de/blacklinerdev/monitor/exams");
            ObjectInputStream ois = new ObjectInputStream(is);
            this.exams = (List<Exam>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            
        }
    }

    public static void main(String[] args) {
        Monitor monitor;
        try {
            monitor = new Monitor();
            Map<String, Boolean> result = monitor.run();
            monitor.print(result);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    private Map<String, Boolean> run() throws ClassNotFoundException, SQLException {
        Map<String, Boolean> results = new HashMap();
        String url = props.getProperty("url");
        String dbuser = props.getProperty("dbuser");
        String dbpasswd = props.getProperty("dbpasswd");
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, dbuser, dbpasswd);
        Statement stmt = con.createStatement();
        for (Exam e : exams) {
            ResultSet rs = stmt.executeQuery(e.getQuery());
            while (rs.next()) {
                LocalTime time = rs.getTime("letztes Update").toLocalTime();
                results.put(e.getServiceName(), time.isAfter(LocalTime.now().minusMinutes(e.getIntervall())));
            }
        }
        return results;
    }

    private void print(Map<String, Boolean> result) {
        
        for (Entry<String, Boolean> entry : result.entrySet()) {
            if(entry.getValue()){
                System.out.println(entry.getKey() + ": running");
            }else{
                System.out.println(entry.getKey() + ": not running");
            }
        }
        int services = exams.size();
        int up = getRunningAmount(result);
        System.out.println(up + " of " + services + " are running");
        if (up != services) {
            System.out.println(services - up + " service(s) are down!");
        }
    }

    private int getRunningAmount(Map<String, Boolean> map) {
        int running = 0;
        for (Boolean b : map.values()) {
            if (b) {
                running++;
            }
        }
        return running;
    }
}

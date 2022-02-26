package de.blacklinerdev.monitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Monitor {

    private final Properties props;
    //private Properties queries;
    private final List<String> queries;
    
    public Monitor() throws IOException{
        InputStream is;
        props = new Properties();
        queries = new ArrayList();
        //load props
        is = new FileInputStream("/home/leroy/Projekte/Monitor/src/main/java/de/blacklinerdev/monitor/config.properties");
        props.load(is);
        
        //load queries
        //is = getClass().getResourceAsStream("queries");
        //queries.load(is);
        
        //load string queries
        is = new FileInputStream("/home/leroy/Projekte/Monitor/src/main/java/de/blacklinerdev/monitor/queries");
        Scanner sc = new Scanner(is);
        while(sc.hasNextLine()){
            queries.add(sc.nextLine());
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException{
        Monitor monitor = new Monitor();
        List<Boolean> result = monitor.run();
        monitor.print(result);
        
    }
    
    private List<Boolean> run() throws ClassNotFoundException, SQLException{
        List<Boolean> results = new ArrayList();
        String url = props.getProperty("url");
        String dbuser = props.getProperty("dbuser");
        String dbpasswd = props.getProperty("dbpasswd");
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, dbuser, dbpasswd);
        Statement stmt = con.createStatement();
        for(String query : queries){
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                LocalTime time = rs.getTime("lastUpdate").toLocalTime();
                results.add(time.isAfter(LocalTime.now().minusHours(1)));
            }
        }
        return results;
    }
    
    private void print(List<Boolean> result) {
        for(Boolean b : result){
            System.out.println(b);
        }
        int services = queries.size();
        int up = getRunningAmount(result);
        System.out.println(up + " of " + services + " are running");
        if(up != services){
            System.out.println(services - up + " service(s) are down!");
        }
    }
    
    private int getRunningAmount(List<Boolean> list){
        int running = 0;
        for(Boolean b : list){
            if(b) running++;
        }
        return running;
    }
}

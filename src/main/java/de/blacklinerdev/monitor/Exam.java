package de.blacklinerdev.monitor;

import java.io.Serializable;

public class Exam implements Serializable{

    private String serviceName;
    private String query;
    private int intervall;
    private String resultName;

    public Exam() {
    }

    public Exam(String name, String query, int intervall) {
        this.serviceName = name;
        this.query = query;
        this.intervall = intervall;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getIntervall() {
        return intervall;
    }

    public void setIntervall(int intervall) {
        this.intervall = intervall;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    @Override
    public String toString() {
        return "Exam{" + "serviceName=" + serviceName + ", query=" + query + ", intervall=" + intervall + '}';
    }
    
}

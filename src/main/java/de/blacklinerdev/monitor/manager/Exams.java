package de.blacklinerdev.monitor.manager;

import java.util.List;
import java.util.Observable;
import de.blacklinerdev.monitor.Exam;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exams extends Observable{
   
    private List<Exam> list;
    private File file;
    private static final String FILESTR = "/home/leroy/Projekte/Monitor/src/main/java/de/blacklinerdev/monitor/exams";
    
    public Exams() throws IOException{
        this.list = new ArrayList();
        try{
            this.file = new File(FILESTR);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            this.list = (List<Exam>) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            file.createNewFile();
        }
        this.setChanged();
        this.notifyObservers();
    }

    public List<Exam> getList() {
        return list;
    }
    public Exam getExam(int index){
        return list.get(index);
    }

    public void add(Exam exam){
        this.list.add(exam);
        setChanged();
        notifyObservers();
        save();
    }
    
    public void edit(Exam oldExam, Exam newExam){
        remove(oldExam);
        add(newExam);
        setChanged();
        notifyObservers();
        save();
    }
    public boolean remove(Exam exam){
        boolean success = this.list.remove(exam);
        setChanged();
        notifyObservers();
        save();
        return success;
    }

    private void save() {
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(this.list);
        } catch (IOException ex) {
            Logger.getLogger(Exams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

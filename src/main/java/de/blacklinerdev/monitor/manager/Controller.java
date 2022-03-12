package de.blacklinerdev.monitor.manager;

import de.blacklinerdev.monitor.Exam;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener, Observer {

    private final Exams exams;
    private final ExamManager mgr;

    @SuppressWarnings("LeakingThisInConstructor")
    public Controller() throws IOException {
        this.mgr = new ExamManager(this);
        this.exams = new Exams();
        this.exams.addObserver(this);
        this.update();
    }

    public static void main(String[] args) throws IOException {
        Controller c = new Controller();
        c.mgr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch (command) {

            case "exit":
                System.exit(0);
                break;
            case "remove":
                remove();
                break;
            case "edit":
                edit();
                break;
            case "add":
                add();
                break;
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        update();
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if (lse.getValueIsAdjusting()) {
            return;
        }
        int index = this.mgr.getListSelection();
        if (index == -1){
            return;
        }
        Exam exam = this.exams.getExam(index);
        this.mgr.setExam(exam);
    }
    private void remove(){
        int index = mgr.getListSelection();
        if(index == -1){
            return;
        }
        Exam exam = exams.getExam(index);
        exams.remove(exam);
    }
    private void add(){
        exams.add(mgr.getExam());
    }
    private void edit(){
        remove();
        add();
    }
    
    private void update() {
        this.mgr.setList(this.exams.getList());
    }
}

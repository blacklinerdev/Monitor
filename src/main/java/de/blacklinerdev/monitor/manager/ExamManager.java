package de.blacklinerdev.monitor.manager;

import de.blacklinerdev.monitor.Exam;
import java.util.List;
import javax.swing.DefaultListModel;

public class ExamManager extends javax.swing.JFrame {
    
    public ExamManager( Controller c) {
        initComponents();
        this.jlist.addListSelectionListener(c);
        DefaultListModel dlm = new DefaultListModel();
        this.jlist.setModel(dlm);
        this.addbt.addActionListener(c);
        this.addbt.setActionCommand("add");
        this.delbt.addActionListener(c);
        this.delbt.setActionCommand("remove");
        this.editbt.addActionListener(c);
        this.editbt.setActionCommand("edit");
        this.closebt.addActionListener(c);
        this.closebt.setActionCommand("exit");
    }
    public final void setList(List<Exam> list){
        jlist.removeAll();
        DefaultListModel dlm = new DefaultListModel();
        for(Exam e : list){
            dlm.addElement(e.getServiceName());
        }
        jlist.setModel(dlm);
    }
    public int getListSelection(){
        return this.jlist.getSelectedIndex();
    }
    public void setExam(Exam exam){
        this.headline.setText(exam.getServiceName());
        this.nametb.setText(exam.getServiceName());
        this.querytb.setText(exam.getQuery());
        this.intervalsp.setValue(exam.getIntervall());
    }
    public Exam getExam(){
        return new Exam(nametb.getText(), querytb.getText(), (int)intervalsp.getValue());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlist = new javax.swing.JList<>();
        headline = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nametb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        querytb = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        intervalsp = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        delbt = new javax.swing.JButton();
        editbt = new javax.swing.JButton();
        addbt = new javax.swing.JButton();
        closebt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exam Manager");
        setResizable(false);
        setSize(new java.awt.Dimension(500, 400));

        jScrollPane1.setViewportView(jlist);

        headline.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        headline.setText("jLabel1");

        jLabel1.setText("Name");

        jLabel2.setText("Query");

        querytb.setColumns(20);
        querytb.setRows(5);
        jScrollPane2.setViewportView(querytb);

        jLabel3.setText("Interval");

        jLabel4.setText("Max. minutrs since last entry");

        delbt.setText("Remove");

        editbt.setText("Edit");

        addbt.setText("Add");

        closebt.setText("Close");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nametb, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(closebt)
                                        .addGap(103, 103, 103))
                                    .addComponent(intervalsp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(delbt)
                                        .addGap(18, 18, 18)
                                        .addComponent(editbt)
                                        .addGap(18, 18, 18)
                                        .addComponent(addbt)))
                                .addGap(6, 6, 6))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(headline)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(headline)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nametb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(intervalsp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(closebt))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(delbt)
                                    .addComponent(editbt)
                                    .addComponent(addbt)))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbt;
    private javax.swing.JButton closebt;
    private javax.swing.JButton delbt;
    private javax.swing.JButton editbt;
    private javax.swing.JLabel headline;
    private javax.swing.JSpinner intervalsp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlist;
    private javax.swing.JTextField nametb;
    private javax.swing.JTextArea querytb;
    // End of variables declaration//GEN-END:variables

}

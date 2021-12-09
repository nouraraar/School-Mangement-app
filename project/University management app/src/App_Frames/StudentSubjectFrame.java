/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App_Frames;

import App_Exceptions.InvalidPwdException;
import App_Exceptions.InvalidUsernameException;
import Dialogs.Message_Dialog;
import Models.*;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author amir,chedi,nour
 */
public class StudentSubjectFrame extends javax.swing.JFrame {

  
    
    /**
     * Creates new form LoginFrame
     */
    public StudentSubjectFrame(String server_ip,int PORT,User user,int fieldId,int level) {
        this.fieldId=fieldId;
        this.level=level;
        this.user = user;
        this.server_ip = server_ip;
        this.PORT = PORT;
        initComponents();
        initVisibility();
        initValues();
        setVisible(true);
        setLocationRelativeTo(null);
       
    }

    private void initValues()
    {
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                
                 try {

                            //establishing the connection
                            Socket socket = new Socket(server_ip,PORT);
                            //opening out/in streams with the server 
                            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                            //sending the user to server
                            out.writeObject("+get_student_subjects");//undecating to server that it's a show all studnets request
                            out.writeObject(new Student(null,null,null,null,null,null,level,null,-1, fieldId, null, false));
                            out.writeObject(user);     //providing the student to the server
                           //recieve the response
                            String response = (String)in.readObject();
                            if(response.equals("ok-"))
                            {
                          
                          
                                  Collection<Subject> result = (ArrayList<Subject>)in.readObject();
                                  addResultTable(result);
 
                            }else if(response.equals("err-"))
                            {
                                new Message_Dialog("An unknown error has occuried while getting data, please check the servers log files");
                                dispose();
                            }
                            else if(response.equals("SQL_err-"))
                            {
                                new Message_Dialog("DATABASE ERROR : Database server is unable to fetch this querry , please check the servers .");
                            }
                            else if(response.equals("IO_err-"))
                            {
                                  new Message_Dialog("Server ERROR : Something went wrong with the streams , please make sure that you are connected to the server , that the server is online and try again .");
                            }
                            else if(response.equals("CNF_err-"))
                            {
                                new Message_Dialog("Server ERROR : server dependencies are missing , please make sure that the servers files are safe and run the application in JRE 8u241 .");
                            }
                            else
                            {
                                 new Message_Dialog("Commenication link failure : Recivied DATA is corrupted , please try again ");
                            }
                        }catch(IOException ex)
                        {
                               new Message_Dialog("Server ERROR : Something went wrong with the streams , please make sure that you are connected to the server , that the server is online and try again .");
                        } catch (ClassNotFoundException ex) {
                           new Message_Dialog("Something went wrong please contact the developers");
                        


                    }
                
            }
        }).start();
        
    }
    
      private void addResultTable(Collection<Subject> result)
    {
        resultTable.removeAll();
        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
        
        while(model.getRowCount()>0)
            model.removeRow(0);
        
                                Object row[] = new Object[3];
                                for(Subject subject : result)
                                {
                                    row[0] = subject.getDescription();
                                    row[1] = subject.getTeacherCin();
                                    row[2] = (int)subject.getVolume();

                                    model.addRow(row);
                                }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        export_icon = new javax.swing.JLabel();
        exportLabel = new javax.swing.JLabel();
        exportLabel1 = new javax.swing.JLabel();
        export_icon1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        rootPanel.setBackground(new java.awt.Color(33, 35, 35));
        rootPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 51, 255), null));
        rootPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                rootPanelMouseDragged(evt);
            }
        });
        rootPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rootPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rootPanelMousePressed(evt);
            }
        });

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App_Frames/icons/icons8-delete-30.png"))); // NOI18N
        closeButton.setContentAreaFilled(false);
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.setIconTextGap(0);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        resultTable.setBackground(new java.awt.Color(64, 70, 74));
        resultTable.setForeground(new java.awt.Color(255, 255, 255));
        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject description", "teacher cin", "volume"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(resultTable);

        export_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App_Frames/icons/icons8-microsoft-excel-40.png"))); // NOI18N
        export_icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        export_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                export_iconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                export_iconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                export_iconMouseExited(evt);
            }
        });

        exportLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        exportLabel.setForeground(new java.awt.Color(144, 153, 164));
        exportLabel.setText("export to Excel");

        exportLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        exportLabel1.setForeground(new java.awt.Color(144, 153, 164));
        exportLabel1.setText("export to Google sheet");

        export_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App_Frames/icons/icons8-microsoft-excel-40.png"))); // NOI18N
        export_icon1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        export_icon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                export_icon1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(export_icon1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rootPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(export_icon)
                    .addGap(0, 0, 0)
                    .addComponent(exportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(export_icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(exportLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rootPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(export_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(rootPanelLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(exportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rootPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rootPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rootPanelMousePressed
        // TODO add your handling code here:
        mouseClickPoint = evt.getPoint(); // update the position
    }//GEN-LAST:event_rootPanelMousePressed

    private void rootPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rootPanelMouseDragged
        // TODO add your handling code here:
        Point newPoint = evt.getLocationOnScreen();
        newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y); // Moves the point by given values from its location
        setLocation(newPoint); // set the new location
    }//GEN-LAST:event_rootPanelMouseDragged

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void export_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_export_iconMouseClicked
        // TODO add your handling code here:

        exportTableToExcel();

    }//GEN-LAST:event_export_iconMouseClicked

    private void export_iconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_export_iconMouseEntered
        // TODO add your handling code here:
        exportLabel.setVisible(true);
    }//GEN-LAST:event_export_iconMouseEntered

    private void export_iconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_export_iconMouseExited
        // TODO add your handling code here:
        exportLabel.setVisible(false);
    }//GEN-LAST:event_export_iconMouseExited

    private void export_icon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_export_icon1MouseClicked
        // TODO add your handling code here:

        exportTableToExcel();

    }//GEN-LAST:event_export_icon1MouseClicked

     private void exportTableToExcel() 
    {
     
        try{
              //Set default location to C:\Users\Authentic\Desktop or your preferred location
            JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\Authentic\\Desktop");
            //Dialog box title
            excelFileChooser.setDialogTitle("Save As ..");
            //Filter only xls, xlsx, xlsm files
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel files", "xls", "xlsx", "xlsm");
            //Setting extension for selected file names
            excelFileChooser.setFileFilter(fnef);
            int chooser = excelFileChooser.showSaveDialog(null);
            //Check if save button has been clicked
            if (chooser == JFileChooser.APPROVE_OPTION) {
        TableModel model = resultTable.getModel();
        FileWriter excel = new FileWriter(excelFileChooser.getSelectedFile()+".xlsx");
        //creating excel header
        for(int i = 0; i < model.getColumnCount(); i++){
            excel.write(model.getColumnName(i) + "\t");
        }

        excel.write("\n");

        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                excel.write(model.getValueAt(i,j).toString()+"\t");
            }
            excel.write("\n");
        }

        excel.close();
        new Message_Dialog("Data exported successfully");
            }

    }catch(IOException ex){ new Message_Dialog(ex.getMessage()); }
         
  
    }
    
    private void resultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseClicked
        // TODO add your handling code here:
        resultTable.setRowSelectionAllowed(true);
    }//GEN-LAST:event_resultTableMouseClicked

    private void rootPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rootPanelMouseClicked
        // TODO add your handling code here:
         resultTable.setRowSelectionAllowed(false);
    }//GEN-LAST:event_rootPanelMouseClicked

    
 
    
     private void initVisibility() {
     
               }
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel exportLabel;
    private javax.swing.JLabel exportLabel1;
    private javax.swing.JLabel export_icon;
    private javax.swing.JLabel export_icon1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resultTable;
    private javax.swing.JPanel rootPanel;
    // End of variables declaration//GEN-END:variables

    private Point mouseClickPoint ;
    private Socket socket;
    private static String server_ip;
    private static User user;
    private static  int PORT ;
    private static int fieldId;
    private static int level;
}

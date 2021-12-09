/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App_Frames;

import App_Exceptions.*;
import Dialogs.Message_Dialog;
import Models.*;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author amir,chedi,nour
 */
public class HomePanel extends javax.swing.JPanel {

    /**
     * Creates new form StudentPanel
     */
    public HomePanel(String server_ip,int PORT,User user) {
        this.user = user;
        this.server_ip = server_ip;
        this.PORT = PORT;
        
        initComponents();
        setInvisibilty() ;  
        initValues();
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
                            out.writeObject("+get_count");//undecating to server that it's a show all studnets request
                            out.writeObject(user);     //providing the student to the server
                           //recieve the response
                            String response = (String)in.readObject();
                            if(response.equals("ok-"))
                            {
                                 //receving the response
                                  Home home = (Home)in.readObject();
                                  studentCount.setText(String.valueOf(home.getStudentCount()));
                                  teacherCount.setText(String.valueOf(home.getTeacherCount()));
                                  fieldCount.setText(String.valueOf(home.getFieldCount()));
                                  subjectCount.setText(String.valueOf(home.getSubjectCount()));
                                  
 
                            }else if(response.equals("err-"))
                            {
                                new Message_Dialog("An unknown error has occuried while getting data, please check the servers log files");
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
    
     private void setInvisibilty()
     {
        
      
        
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        studentAreaLabel = new javax.swing.JLabel();
        teacherCount = new javax.swing.JLabel();
        studentCount = new javax.swing.JLabel();
        fieldCount = new javax.swing.JLabel();
        subjectCount = new javax.swing.JLabel();
        subjectLabel = new javax.swing.JLabel();
        studentLabel = new javax.swing.JLabel();
        teacherLabel = new javax.swing.JLabel();
        fieldLabel = new javax.swing.JLabel();
        aboutUsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(34, 40, 44));
        setMaximumSize(new java.awt.Dimension(884, 626));
        setPreferredSize(new java.awt.Dimension(884, 626));
        setLayout(null);

        studentAreaLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        studentAreaLabel.setForeground(new java.awt.Color(144, 153, 164));
        studentAreaLabel.setText("Home");
        add(studentAreaLabel);
        studentAreaLabel.setBounds(20, 0, 160, 31);

        teacherCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        teacherCount.setForeground(new java.awt.Color(224, 233, 244));
        teacherCount.setText("0");
        add(teacherCount);
        teacherCount.setBounds(800, 100, 10, 22);

        studentCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        studentCount.setForeground(new java.awt.Color(224, 233, 244));
        studentCount.setText("0");
        add(studentCount);
        studentCount.setBounds(340, 100, 10, 30);

        fieldCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldCount.setForeground(new java.awt.Color(224, 233, 244));
        fieldCount.setText("0");
        add(fieldCount);
        fieldCount.setBounds(340, 220, 10, 30);

        subjectCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        subjectCount.setForeground(new java.awt.Color(224, 233, 244));
        subjectCount.setText("0");
        add(subjectCount);
        subjectCount.setBounds(800, 220, 10, 22);

        subjectLabel.setBackground(new java.awt.Color(114, 8, 169));
        subjectLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        subjectLabel.setForeground(new java.awt.Color(204, 213, 224));
        subjectLabel.setText("  Subjects :");
        subjectLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        subjectLabel.setOpaque(true);
        add(subjectLabel);
        subjectLabel.setBounds(500, 200, 350, 70);

        studentLabel.setBackground(new java.awt.Color(114, 8, 169));
        studentLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        studentLabel.setForeground(new java.awt.Color(204, 213, 224));
        studentLabel.setText("  Students :");
        studentLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        studentLabel.setOpaque(true);
        add(studentLabel);
        studentLabel.setBounds(40, 80, 350, 70);

        teacherLabel.setBackground(new java.awt.Color(114, 8, 169));
        teacherLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        teacherLabel.setForeground(new java.awt.Color(204, 213, 224));
        teacherLabel.setText("  Teachers :");
        teacherLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        teacherLabel.setOpaque(true);
        add(teacherLabel);
        teacherLabel.setBounds(500, 80, 350, 70);

        fieldLabel.setBackground(new java.awt.Color(114, 8, 169));
        fieldLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        fieldLabel.setForeground(new java.awt.Color(204, 213, 224));
        fieldLabel.setText("  Fields :");
        fieldLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        fieldLabel.setOpaque(true);
        add(fieldLabel);
        fieldLabel.setBounds(40, 200, 350, 70);

        aboutUsLabel.setBackground(new java.awt.Color(245, 245, 245));
        aboutUsLabel.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        aboutUsLabel.setForeground(new java.awt.Color(144, 153, 164));
        aboutUsLabel.setText("About us :");
        add(aboutUsLabel);
        aboutUsLabel.setBounds(20, 340, 90, 19);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(64, 70, 74));
        jTextArea1.setColumns(50);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("We are three students at the National Institute of Applied Science and Technology studying\nnetworks and telecommunications . \nWe developed this project  based on the concept of client-server applications ,\nIt aims to manipulate some basic ressources inside of an institute .\nProviding an access to three different areas :\n * Student area\n * Teacher area\n * Subject area\n\nWhere each area designed to fullfil management needs for any institute administration\nfacility , based on the ressources mentioned previously .\n\nThis project was created in demand for educational purposes.\nThis project is open source ,\n\nFor more details , please contact the developers :\n\nHammami Chedi : chadihammami@insat.u-carthage.tn ,\nAraar Nour El Houda : nourelhouda.araar@insat.u-carthage.tn ,\nKhemissi Amir : amir.khemissi@insat.u-carthage.tn ,\n\nTunisia , 2020");
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 380, 850, 240);
    }// </editor-fold>//GEN-END:initComponents

// verification Functions
    
 
    
   
    
 
   
    
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutUsLabel;
    private javax.swing.JLabel fieldCount;
    private javax.swing.JLabel fieldLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel studentAreaLabel;
    private javax.swing.JLabel studentCount;
    private javax.swing.JLabel studentLabel;
    private javax.swing.JLabel subjectCount;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JLabel teacherCount;
    private javax.swing.JLabel teacherLabel;
    // End of variables declaration//GEN-END:variables
   

    private static User user;
    private static int PORT;
    private static String server_ip;
}

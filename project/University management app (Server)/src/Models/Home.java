/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.io.Serializable;

/**
 *
 * @author amir,chedi,nour
 */
public class Home implements Serializable{
    
    private int studentCount;
    private int teacherCount;
    private int fieldCount;
    private int subjectCount;

    public Home(int studentCount, int teacherCount, int fieldCount, int subjectCount) {
        this.studentCount = studentCount;
        this.teacherCount = teacherCount;
        this.fieldCount = fieldCount;
        this.subjectCount = subjectCount;
    }

    @Override
    public String toString() {
        return "Home{" + "studentCount=" + studentCount + ", teacherCount=" + teacherCount + ", fieldCount=" + fieldCount + ", subjectCount=" + subjectCount + '}';
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(int teacherCount) {
        this.teacherCount = teacherCount;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(int subjectCount) {
        this.subjectCount = subjectCount;
    }
    
    
}

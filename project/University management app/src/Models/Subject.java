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
public class Subject implements Serializable{
    private String teacherCin;
    private String description;
    private int volume;
    private int level;
    private int fieldId;

    public Subject(String teacherCin, String description, int volume, int level, int fieldId) {
        this.teacherCin = teacherCin;
        this.description = description;
        this.volume = volume;
        this.level = level;
        this.fieldId = fieldId;
    }

    public String getTeacherCin() {
        return teacherCin;
    }

    public void setTeacherCin(String teacherCin) {
        this.teacherCin = teacherCin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public String toString() {
        return "Subject{" + "teacherCin=" + teacherCin + ", description=" + description + ", volume=" + volume + ", level=" + level + ", fieldId=" + fieldId + '}';
    }

  

 
   
    
}

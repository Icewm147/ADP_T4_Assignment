
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author M.Masood Lamera
 */
public class WorkerSubject implements Serializable{
    private String subjectID;
    private String subjectName;
    private String courseID;

    public WorkerSubject() {
    }

    public WorkerSubject(String subjectID, String subjectName, String courseID) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.courseID = courseID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "WorkerSubject{" + "subjectID=" + subjectID + ", subjectName=" + subjectName + ", courseID=" + courseID + '}';
    }
    
}

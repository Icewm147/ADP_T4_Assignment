
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author M.Masood Lamera
 */
public class WorkerSubject implements Serializable {

    private String subjectID1, subjectName1;
    private String subjectID2, subjectName2;
    private String subjectID3, subjectName3;
    private String subjectID4, subjectName4;
    private String courseID;

    public WorkerSubject() {
    }

    public WorkerSubject(String subjectID1, String subjectName1, String subjectID2, String subjectName2, String subjectID3, String subjectName3, String subjectID4, String subjectName4, String courseID) {
        this.subjectID1 = subjectID1;
        this.subjectName1 = subjectName1;
        this.subjectID2 = subjectID2;
        this.subjectName2 = subjectName2;
        this.subjectID3 = subjectID3;
        this.subjectName3 = subjectName3;
        this.subjectID4 = subjectID4;
        this.subjectName4 = subjectName4;
        this.courseID = courseID;
    }

    public WorkerSubject(String subjectID1, String subjectName1, String courseID) {
        this.subjectID1 = subjectID1;
        this.subjectName1 = subjectName1;
        this.courseID = courseID;
    }

    public String getSubjectID1() {
        return subjectID1;
    }

    public void setSubjectID1(String subjectID1) {
        this.subjectID1 = subjectID1;
    }

    public String getSubjectName1() {
        return subjectName1;
    }

    public void setSubjectName1(String subjectName1) {
        this.subjectName1 = subjectName1;
    }

    public String getSubjectID2() {
        return subjectID2;
    }

    public void setSubjectID2(String subjectID2) {
        this.subjectID2 = subjectID2;
    }

    public String getSubjectName2() {
        return subjectName2;
    }

    public void setSubjectName2(String subjectName2) {
        this.subjectName2 = subjectName2;
    }

    public String getSubjectID3() {
        return subjectID3;
    }

    public void setSubjectID3(String subjectID3) {
        this.subjectID3 = subjectID3;
    }

    public String getSubjectName3() {
        return subjectName3;
    }

    public void setSubjectName3(String subjectName3) {
        this.subjectName3 = subjectName3;
    }

    public String getSubjectID4() {
        return subjectID4;
    }

    public void setSubjectID4(String subjectID4) {
        this.subjectID4 = subjectID4;
    }

    public String getSubjectName4() {
        return subjectName4;
    }

    public void setSubjectName4(String subjectName4) {
        this.subjectName4 = subjectName4;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

//    @Override
//    public String toString() {
//        return "WorkerSubject{" + "subjectID1=" + subjectID1 + ", subjectName1=" + subjectName1 + ", courseID=" + courseID + '}';
//    }
    @Override
    public String toString() {
        return "WorkerSubject{" + "subjectID1=" + subjectID1 + ", subjectName1=" + subjectName1 + ", subjectID2=" + subjectID2 + ", subjectName2=" + subjectName2 + ", subjectID3=" + subjectID3 + ", subjectName3=" + subjectName3 + ", subjectID4=" + subjectID4 + ", subjectName4=" + subjectName4 + ", courseID=" + courseID + '}';
    }
   
 public String toString2() {
        return  subjectID1 + ","+ subjectName1 + ","+ courseID  ;
    }
}

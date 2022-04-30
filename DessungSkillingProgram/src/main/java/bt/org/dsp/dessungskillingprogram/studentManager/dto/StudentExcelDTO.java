package bt.org.dsp.dessungskillingprogram.studentManager.dto;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;

import java.math.BigInteger;

public class StudentExcelDTO {

    @ExcelRow
    private Integer student_id;

    @ExcelCell(1)
    private String cid;

    @ExcelCell(2)
    private String dessungId;

    @ExcelCell(3)
    private String name;

    @ExcelCell(4)
    private Integer mobileNo;

    @ExcelCell(5)
    private String dob;

    @ExcelCell(6)
    private String sex;

    @ExcelCell(7)
    private String email;

    @ExcelCell(8)
    private String boodGroup;

    @ExcelCell(9)
    private String maritalStatus;

    @ExcelCell(10)
    private Integer batchNo;

    @ExcelCell(11)
    private String trainingYear;

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDessungId() {
        return dessungId;
    }

    public void setDessungId(String dessungId) {
        this.dessungId = dessungId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Integer mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBoodGroup() {
        return boodGroup;
    }

    public void setBoodGroup(String boodGroup) {
        this.boodGroup = boodGroup;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    public String getTrainingYear() {
        return trainingYear;
    }

    public void setTrainingYear(String trainingYear) {
        this.trainingYear = trainingYear;
    }
}

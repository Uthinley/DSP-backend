package bt.org.dsp.dessungskillingprogram.studentManager.model;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.courseManager.model.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
@Table(name = "student_dtls")
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer studentId;

    private BigInteger cid;
    private String dessungId;
    private String name;
    private Integer mobileNo;
    private String dob;
    private String sex;
    private String email;
    private String bloodGroup;
    private String maritalStatus;
    private String avatar;
    private Integer trainingCentreId;
    private Integer batchNo;
    private String trainingYear;
    private char tracer;
//    private Integer tracerId;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JsonBackReference(value = "student-courses")
//    @JsonManagedReference
    @JoinTable(name = "students_courses",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "studentId",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public BigInteger getCid() {
        return cid;
    }

    public void setCid(BigInteger cid) {
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getTrainingCentreId() {
        return trainingCentreId;
    }

    public void setTrainingCentreId(Integer trainingCentreId) {
        this.trainingCentreId = trainingCentreId;
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

    public char getTracer() {
        return tracer;
    }

    public void setTracer(char tracer) {
        this.tracer = tracer;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

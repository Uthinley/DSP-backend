package bt.org.dsp.dessungskillingprogram.courseManager.model;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import bt.org.dsp.dessungskillingprogram.master.Department;
import bt.org.dsp.dessungskillingprogram.master.course.CourseMaster;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.DspCentre;
import bt.org.dsp.dessungskillingprogram.studentManager.model.Student;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Data
@Table(name = "course_dtls")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String courseId;
    private String courseName;
    private String courseStatus;
//    private String industrailSector;
//    private String courseLevel;
    private Integer course_duration;
    private String startDate;
    private String endDate;
    private Integer cohortSize;
    private Integer noOfApplicants;
    private Integer noOfStdCertified;
    private String batchNo;
    private String trainingLocation;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
////    @JsonBackReference(value = "trainers-course")
//    private Trainers trainers;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_master_id", referencedColumnName = "id")
    private CourseMaster courseMaster;
//    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "department_id", referencedColumnName = "id")
//    private Department department;
//
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dsp_centre_id", referencedColumnName = "id")
    private DspCentre dspCentre;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Student> students = new ArrayList<>();

//    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonBackReference
//    @JsonBackReference(value = "student-courses")
//    @JsonManagedReference
//    @JsonIgnore
    private Set<Trainers> trainers = new HashSet<>();

    public void addTrainer(Trainers trainer){
        this.trainers.add(trainer);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Integer getCourse_duration() {
        return course_duration;
    }

    public void setCourse_duration(Integer course_duration) {
        this.course_duration = course_duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCohortSize() {
        return cohortSize;
    }

    public void setCohortSize(Integer cohortSize) {
        this.cohortSize = cohortSize;
    }

    public Integer getNoOfApplicants() {
        return noOfApplicants;
    }

    public void setNoOfApplicants(Integer noOfApplicants) {
        this.noOfApplicants = noOfApplicants;
    }

    public Integer getNoOfStdCertified() {
        return noOfStdCertified;
    }

    public void setNoOfStdCertified(Integer noOfStdCertified) {
        this.noOfStdCertified = noOfStdCertified;
    }

    public String getTrainingLocation() {
        return trainingLocation;
    }

    public void setTrainingLocation(String trainingLocation) {
        this.trainingLocation = trainingLocation;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public CourseMaster getCourseMaster() {
        return courseMaster;
    }

    public void setCourseMaster(CourseMaster courseMaster) {
        this.courseMaster = courseMaster;
    }

    public DspCentre getDspCentre() {
        return dspCentre;
    }

    public void setDspCentre(DspCentre dspCentre) {
        this.dspCentre = dspCentre;
    }

    public Set<Trainers> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainers> trainers) {
        this.trainers = trainers;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}

package pl.edu.oswiecim.pwsz.inf.hrs.model;

import javax.persistence.*;

@Entity
@Table(name = "Salaries", schema = "HRS_SCH")
public class Salary {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "HRS_SCH.SALARIES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;

    @Column(name = "Users_Id")
    private Integer userId;

    @Column(name = "Employment_arrangement")
    private String employmentArrangement;

    @Column(name = "Base_salary")
    private Double baseSalary;

    @Column(name = "Seniority")
    private Integer seniority;

    @Column(name = "Salary_supplement")
    private Double salarySupplement;

    @Column(name = "Employment_status")
    private String employmentStatus;

    public Integer getSalaryId() {
        return id;
    }

    public void setSalaryId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmploymentArrangement() {
        return employmentArrangement;
    }

    public void setEmploymentArrangement(String employmentArrangement) {
        this.employmentArrangement = employmentArrangement;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public Double getSalarySupplement() {
        return salarySupplement;
    }

    public void setSalarySupplement(Double salarySupplement) {
        this.salarySupplement = salarySupplement;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}

//Id, User_Id, Employment_arrangement, Base_salary, Seniority, Salary_supplement, Employment_status

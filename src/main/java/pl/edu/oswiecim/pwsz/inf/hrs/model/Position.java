package pl.edu.oswiecim.pwsz.inf.hrs.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Position", schema = "HRS_SCH")
public class Position {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "HRS_SCH.POSITION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;
    @Column(name = "name")
    private  String name;
    @Column(name = "min_salary")
    private double minSalary;
    @Column(name = "max_salary")
    private double maxSalary;
    @Column(name = "max_percent_salary_supplement")
    private double maxPercentSalarySupplement;


    public Set<User> getUsers() {
        return users;
    }


    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany( mappedBy = "positions")
    private Set<User> users = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMaxPercentSalarySupplement() {
        return maxPercentSalarySupplement;
    }

    public void setMaxPercentSalarySupplement(double maxPercentSalarySupplement) {
        this.maxPercentSalarySupplement = maxPercentSalarySupplement;
    }
}

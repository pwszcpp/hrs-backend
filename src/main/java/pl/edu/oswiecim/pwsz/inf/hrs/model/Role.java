package pl.edu.oswiecim.pwsz.inf.hrs.model;

import javax.persistence.*;

@Entity
@Table(name = "Role", schema = "HRS_SCH")
public class Role {

    @Id
    @Column(name = "bit")
    private Integer bit;

    @Column(name = "name")
    private String role;

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

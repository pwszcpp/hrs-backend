package pl.edu.oswiecim.pwsz.inf.hrs.model;

import javax.persistence.*;

@Entity
@Table(name = "contractor")
public class Contractor {


    @Id
    @Column(name = "contractor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contractor_name")
    private String name;

    @Column(name = "tin")
    private String tin;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true, nullable = false)
    //@NotNull
    private Address address;

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

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true, nullable = false)
   public Address getAddress() {
       return address;
    }

     public void setAddress(Address address) {
        this.address = address;
   }
}

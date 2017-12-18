package pl.edu.oswiecim.pwsz.inf.hrs.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "leave", schema = "HRS_SCH")
public class Leave {
    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "HRS_SCH.LEAVE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;

    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Users_Id",  nullable = false)
    private User user;

    @Column(name = "Create_date")
    private Date createDate;
    @Column(name = "Start_date")
    private Date startDate;
    @Column(name = "End_date")
    private Date endDate;

    @Column(name = "Leave_dimension")
    private Integer leaveDimension;
    @Column(name = "Overdue_leave")
    private Integer overdueLeave;
    @Column(name = "Days_used")
    private Integer daysUsed;

    @Column(name = "Agreed")
    private Boolean agreed;
    @Column(name = "Disagree_reason")
    private String disagreeReason;

    public Integer getId() {
        return id;
    }

    public void setLeaveId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Users_Id",  nullable = false)
    public User getUser() {
        return user;
    }
    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Users_Id",  nullable = false)
    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLeaveDimension() {
        return leaveDimension;
    }

    public void setLeaveDimension(Integer leaveDimension) {
        this.leaveDimension = leaveDimension;
    }

    public Integer getOverdueLeave() {
        return overdueLeave;
    }

    public void setOverdueLeave(Integer overdueLeave) {
        this.overdueLeave = overdueLeave;
    }

    public Integer getDaysUsed() {
        return daysUsed;
    }

    public void setDaysUsed(Integer daysUsed) {
        this.daysUsed = daysUsed;
    }

    public Boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }

    public String getDisagreeReason() {
        return disagreeReason;
    }

    public void setDisagreeReason(String disagreeReason) {
        this.disagreeReason = disagreeReason;
    }
}

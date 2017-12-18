package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import javax.annotation.Generated;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "users",
        "create_date",
        "start_date",
        "end_date",
        "leave_dimension",
        "overdue_leave",
        "days_used",
        "agreed",
        "disagree_reason"
})
public class LeaveDto extends ResourceSupport {

    @JsonProperty("id")
    private Integer id;


    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("end_date")
    private Date endDate;
    @JsonProperty("leave_dimension")
    private Integer leaveDimension;
    @JsonProperty("overdue_leave")
    private Integer overdueLeave;
    @JsonProperty("days_used")
    private Integer daysUsed;
    @JsonProperty("agreed")
    private boolean agreed;
    @JsonProperty("disagree_reason")
    private String disagreeReason;

     @JsonProperty("id")
     public Integer getLeaveId() {
      return id;
     }
     @JsonProperty("id")
     public void setId(Integer id) {
      this.id = id;
     }

    @JsonProperty(value = "user")
    @JsonIgnoreProperties({ "password", "role","status","passExpire", "passChangedDate","loginLastSuccess",
            "loginLastFailed","loginAttemptsFailed" })
    private User user;


    @JsonProperty("user")
    @JsonIgnoreProperties({ "password", "role","status","passExpire", "passChangedDate","loginLastSuccess",
            "loginLastFailed","loginAttemptsFailed" })
    public User getUser() { return user; }
    @JsonProperty("user")
    @JsonIgnoreProperties({ "password", "role","status","passExpire", "passChangedDate","loginLastSuccess",
            "loginLastFailed","loginAttemptsFailed" })
    public void setUser(User user) { this.user = user;
    }


     @JsonProperty("start_date")
     public Date getStartDate() {
      return startDate;
     }
     @JsonProperty("start_date")
     public void setStartDate(Date startDate) {
      this.startDate = startDate;
     }
     @JsonProperty("create_date")
     public Date getCreateDate() {
      return createDate;
     }
     @JsonProperty("create_date")
     public void setCreateDate(Date creatDate) {
      this.createDate = creatDate;
     }
     @JsonProperty("end_date")
     public Date getEndDate() {
      return endDate;
     }
     @JsonProperty("end_date")
     public void setEndDate(Date endDate) {
      this.endDate = endDate;
     }
     @JsonProperty("leave_dimension")
     public Integer getLeaveDimension() {
      return leaveDimension;
     }
     @JsonProperty("leave_dimension")
     public void setLeaveDimension(Integer leaveDimension) {
      this.leaveDimension = leaveDimension;
     }
     @JsonProperty("overdue_leave")
     public Integer getOverdueLeave() {
      return overdueLeave;
     }
     @JsonProperty("overdue_leave")
     public void setOverdueLeave(Integer overdueLeave) {
      this.overdueLeave = overdueLeave;
     }
     @JsonProperty("days_used")
     public Integer getDaysUsed() {
      return daysUsed;
     }
     @JsonProperty("days_used")
     public void setDaysUsed(Integer daysUsed) {
      this.daysUsed = daysUsed;
     }
     @JsonProperty("agreed")
     public boolean getAgreed() {
      return agreed;
     }
     @JsonProperty("agreed")
     public void setAgreed(boolean agreed) {
      this.agreed = agreed;
     }
        @JsonProperty("disagree_reason")
     public String getDisagreeReason() {
      return disagreeReason;
     }
        @JsonProperty("disagree_reason")
     public void setDisagreeReason(String disagreeReason) {
      this.disagreeReason = disagreeReason;
     }
}

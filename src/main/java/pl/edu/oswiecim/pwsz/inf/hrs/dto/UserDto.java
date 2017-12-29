package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;

import javax.annotation.Generated;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "username",
        "email",
        "password",
        "role",
        "address",
        "position",
        "taxOffice",
        "passExpire",
        "passChangedDate",
        "loginLastSuccess",
        "loginLastFailed",
        "loginAttemptsFailed",
        "employmentStartDate"
        //       "roles"
})
public class UserDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private String role;

    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    public Integer getUserId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }


    @JsonProperty(value="passExpire",access = JsonProperty.Access.READ_WRITE)
   private Date passExpire = new Date(System.currentTimeMillis()+ (365*24*60*60*1000));



    @JsonProperty("passChangedDate")
    private Date passChangedDate= new Date(System.currentTimeMillis()) ;


    @JsonProperty("loginLastSuccess")
    private Date loginLastSuccess= new Date(System.currentTimeMillis());


    @JsonProperty("loginLastFailed")
    private Date loginLastFailed= new Date(System.currentTimeMillis()) ;


    @JsonProperty("loginAttemptsFailed")
    private Integer loginAttemptsFailed=0;

    @JsonProperty("address")
    private String address ;


    @JsonProperty("employmentStartDate")
    private Date employmentStartDate;


    @JsonProperty("position")
    private String position="empty";


    @JsonProperty("taxOffice")
    private String taxOffice;

    @JsonProperty(value="passExpire",access = JsonProperty.Access.READ_ONLY)
    public Date getPassExpire() {
        return passExpire;
    }
    @JsonProperty(value = "passExpire", access = JsonProperty.Access.READ_ONLY)
    public void setPassExpire(Date passExpire) {
        this.passExpire = passExpire;
    }
    @JsonProperty("passChangedDate")
    public Date getPassChangedDate() {
        return passChangedDate;
    }
    @JsonProperty(value ="passChangedDate",access = JsonProperty.Access.READ_ONLY)
    public void setPassChangedDate(Date passChangedDate) {
        this.passChangedDate = passChangedDate;
    }
    @JsonProperty("loginLastSuccess")
    public Date getLoginLastSuccess() {
        return loginLastSuccess;
    }
    @JsonProperty(value="loginLastSuccess", access = JsonProperty.Access.READ_ONLY)
    public void setLoginLastSuccess(Date loginLastSuccess) {
        this.loginLastSuccess = loginLastSuccess;
    }
    @JsonProperty("loginLastFailed")
    public Date getLoginLastFailed() {
        return loginLastFailed;
    }
    @JsonProperty(value = "loginLastFailed",access = JsonProperty.Access.READ_ONLY)
    public void setLoginLastFailed(Date loginLastFailed) {
        this.loginLastFailed = loginLastFailed;
    }
    @JsonProperty("loginAttemptsFailed")
    public Integer getLoginAttemptsFailed() {
        return loginAttemptsFailed; }
    @JsonProperty(value = "loginAttemptsFailed", access = JsonProperty.Access.READ_ONLY)
    public void setLoginAttemptsFailed(Integer loginAttemptsFailed) {
        this.loginAttemptsFailed = loginAttemptsFailed;
    }
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }
    @JsonProperty(value = "employmentStartDate")
    public Date getEmploymentStartDate() {
        return employmentStartDate;
    }
    @JsonProperty(value = "employmentStartDate")
    public void setEmploymentStartDate(Date employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }
    @JsonProperty("position")
    public String getPosition() {
        return position;
    }
    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }
    @JsonProperty("taxOffice")
    public String getTaxOffice() {
        return taxOffice;
    }
    @JsonProperty("taxOffice")
    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }
}

package examples.User;

import annotations.Import;
import annotations.Numerical;
import annotations.TypescriptClass;

import java.math.BigInteger;
import java.util.Date;

@TypescriptClass
public class User {

    @Numerical
    private BigInteger id;

    private String username;
    private String email;
    private String password;

    private Date createdOn;
    private Date modifiedOn;

    @Import
    private UserDetails userDetails;

    private boolean enabled;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

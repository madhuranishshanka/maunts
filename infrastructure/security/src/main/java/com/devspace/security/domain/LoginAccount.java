package com.devspace.security.domain;

import com.devspace.multitenancy.domain.TenantEntity;

import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class LoginAccount extends TenantEntity {

    private String userName;
    private String password;
    @OneToMany
    private Set<Role> roles = new HashSet<Role>();

    public LoginAccount() {}

    public LoginAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LoginAccount that = (LoginAccount) o;

        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}

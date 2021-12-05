package com.uri.hotdesk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name="employee_id")
    private String employeeId;
    private String email;
    private String adminName;
    @OneToMany(mappedBy = "employee")
    private Set<EmployeeTeam> registrations;
    @JsonIgnore
    private String defaultRole;
    @JsonIgnore
    private Boolean enabled;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Set<EmployeeTeam> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<EmployeeTeam> registrations) {
        this.registrations = registrations;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", email='" + email + '\'' +
                ", adminName='" + adminName + '\'' +
                ", registrations=" + registrations +
                ", defaultRole='" + defaultRole + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

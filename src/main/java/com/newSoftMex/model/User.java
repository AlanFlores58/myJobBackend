package com.newSoftMex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String last_name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_role")
    private UserRole userRole;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="userContract", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="userCupon", cascade = CascadeType.ALL)
    private List<Cupon> cupons;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="userNote", cascade = CascadeType.ALL)
    private List<Note> notes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="userService", cascade = CascadeType.ALL)
    private List<Service> services;

    @NotNull
    private boolean enabled;

    public User(String name, String last_name, String email, String password, UserRole userRole) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        enabled=false;
    }

    public User(String name, String last_name, String email, String password) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public User(String username, String name, String last_name, String email, String password, UserRole userRole, List<Contract> contracts, List<Cupon> cupons, List<Note> notes, List<Service> services, boolean enabled) {
        this.username = username;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.contracts = contracts;
        this.cupons = cupons;
        this.notes = notes;
        this.services = services;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Cupon> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupon> cupons) {
        this.cupons = cupons;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

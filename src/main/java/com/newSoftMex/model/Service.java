package com.newSoftMex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alan.flores on 1/5/17.
 */
@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idUser")
    private User userService;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idServiceType")
    private ServiceType serviceType;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private float price;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="service", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="service", cascade = CascadeType.ALL)
    private List<Note> notes;


    public Service() {
    }

    public Service(User userService, ServiceType serviceType, String name, String description, float price, List<Contract> contracts, List<Note> notes) {
        this.userService = userService;
        this.serviceType = serviceType;
        this.name = name;
        this.description = description;
        this.price = price;
        this.contracts = contracts;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserService() {
        return userService;
    }

    public void setUserService(User user) {
        this.userService = user;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}

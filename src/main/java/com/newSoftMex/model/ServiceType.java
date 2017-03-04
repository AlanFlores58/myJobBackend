package com.newSoftMex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alan.flores on 1/5/17.
 */
@Entity
@Table(name = "serviceType")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    private String image;

    private byte[] photo;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="serviceType", cascade = CascadeType.ALL)
    private List<Service> services;

    public ServiceType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ServiceType() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}

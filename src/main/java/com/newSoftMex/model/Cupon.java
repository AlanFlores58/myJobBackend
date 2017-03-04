package com.newSoftMex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by alan.flores on 1/5/17.
 */
@Entity
@Table(name = "cupon")
public class Cupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double discount;

    @NotNull
    private boolean state;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idUser")
    private User userCupon;

    private Date date;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="cupon", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    public Cupon(double discount, boolean state, User userCupon) {
        this.discount = discount;
        this.state = state;
        this.userCupon = userCupon;
    }

    public Cupon() {
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public User getUserCupon() {
        return userCupon;
    }

    public void setUserCupon(User userCupon) {
        this.userCupon = userCupon;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.newSoftMex.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public Cupon(double discount, boolean state, User userCupon) {
        this.discount = discount;
        this.state = state;
        this.userCupon = userCupon;
    }

    public Cupon() {
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
}

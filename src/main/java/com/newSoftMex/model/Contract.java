package com.newSoftMex.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by alan.flores on 1/5/17.
 */
@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idUser")
    private User userContract;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idService")
    private Service service;

    @ManyToOne
    @JoinColumn(name="idCupon")
    private Cupon cupon;

    @NotNull
    private Float price;

    private Long state;

    @NotNull
    private Date dateStart;

    @NotNull
    private Date dateFinish;


    private boolean confirmAdmin = false;

    private boolean payed = false;

    private boolean cancelled = false;

    private boolean confirmed = false;

    private boolean finish = false;

    public Contract(User userContract, Service service, Float price, Long state, Date dateStart, Date dateFinish) {
        this.userContract = userContract;
        this.service = service;
        this.price = price;
        this.state = state;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Contract() {
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isConfirmAdmin() {
        return confirmAdmin;
    }

    public void setConfirmAdmin(boolean confirmAdmin) {
        this.confirmAdmin = confirmAdmin;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserContract() {
        return userContract;
    }

    public void setUserContract(User userContract) {
        this.userContract = userContract;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }
}

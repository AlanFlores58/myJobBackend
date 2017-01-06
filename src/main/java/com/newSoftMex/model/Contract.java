package com.newSoftMex.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @ManyToOne
    @JoinColumn(name="idUser")
    private User userContract;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idComment")
    private Comment comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idService")
    private Service service;

    @NotNull
    private Float price;

    @NotNull
    private Long state;

    public Contract(User userContract, Comment comment, Service service, Float price, Long state) {
        this.userContract = userContract;
        this.comment = comment;
        this.service = service;
        this.price = price;
        this.state = state;
    }

    public Contract() {
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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
}

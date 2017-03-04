package com.newSoftMex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alan.flores on 1/5/17.
 */
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idUser")
    private User userDoer;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idUserService")
    private User idUserServiceCommented;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUserDoer() {
        return userDoer;
    }

    public void setUserDoer(User userDoer) {
        this.userDoer = userDoer;
    }

    public User getIdUserServiceCommented() {
        return idUserServiceCommented;
    }

    public void setIdUserServiceCommented(User idUserServiceCommented) {
        this.idUserServiceCommented = idUserServiceCommented;
    }
}

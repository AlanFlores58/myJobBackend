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

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="comment", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy="comment", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Comment() {
    }

    public Comment(String comment, List<Contract> contracts, List<Comment> comments) {
        this.comment = comment;
        this.contracts = contracts;
        this.comments = comments;
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

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

package com.newSoftMex.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alan.flores on 1/5/17.
 */

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idComment")
    private Comment comment;


    @NotNull
    @ManyToOne
    @JoinColumn(name="idService")
    private Service service;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idUser")
    private User userNote;

    public Note() {
    }

    public Note(Comment comment, Service service, User userNote) {
        this.comment = comment;
        this.service = service;
        this.userNote = userNote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUserNote() {
        return userNote;
    }

    public void setUserNote(User userNote) {
        this.userNote = userNote;
    }
}

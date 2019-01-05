package com.techplus.connectedinapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techplus.connectedinapi.enums.InvitationStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private InvitationStatus status;

    public Invitation() {
    }

    public Invitation(User sender, User receiver, Date date, InvitationStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        sender.setRoles(new ArrayList<>());
        sender.setPassword("");
        sender.setPosts(new ArrayList<>());
        sender.setContacts(new ArrayList<>());
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        receiver.setRoles(new ArrayList<>());
        receiver.setPassword("");
        receiver.setPosts(new ArrayList<>());
        receiver.setContacts(new ArrayList<>());
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

}

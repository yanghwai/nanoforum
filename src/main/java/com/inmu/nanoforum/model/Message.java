package com.inmu.nanoforum.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_read")
    private boolean read;

    @Column(name = "is_deleted")
    private boolean deleted;

    @Column(name = "sendtime")
    private Timestamp sendTime;

    @NotEmpty
    @Column(name = "content")
    private String content;

    @Column(name = "uid_sender")
    private int senderId;

    @Column(name = "uid_receiver")
    private int receiverId;

    @Transient
    private String senderSsoId;

    @Transient
    private String receiverSsoId;

//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "uid_sender")
//    private AppUser sender;
//
//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "uid_receiver")
//    private AppUser receiver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderSsoId() {
        return senderSsoId;
    }

    public void setSenderSsoId(String senderSsoId) {
        this.senderSsoId = senderSsoId;
    }

    public String getReceiverSsoId() {
        return receiverSsoId;
    }

    public void setReceiverSsoId(String receiverSsoId) {
        this.receiverSsoId = receiverSsoId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", read=" + read +
                ", deleted=" + deleted +
                ", sendTime=" + sendTime +
                ", content='" + content + '\'' +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", senderSsoId='" + senderSsoId + '\'' +
                ", receiverSsoId='" + receiverSsoId + '\'' +
                '}';
    }
}

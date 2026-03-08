package com.example.ticketmanager;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy" +
            "-MM-dd HH:mm");

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Ticket(
    ){}

    public Ticket(String name, String content){

        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        name = name.trim();

        if(name.length() > 2 && name.length() < 50){
            this.name = name;
        }
        else {

            this.name = "com.example.ticketmaster.Ticket #" + id;
        }

        if(content.length() > 25 && content.length() < 150) {
            this.content = content;
        } else {
            throw new IllegalArgumentException(String.format("Content cannot be shorter than %s " +
                    "characters or " +
                    "longer than %s!", 10, 100));
        }
    }

    public long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getContent(){
        return content;
    }

    public User getUser(){return user;}

    public void setName(String name){this.name = name;}

    public void setContent(String content){this.content = content;}

    public void setUser(User user){this.user = user;}

    @Override
    public String toString(){
        String s = createdAt != null ? createdAt.format(DATE_FORMATTER) : "not set";
        return String.format("\n      id: %s\ncreation: %s\n    name: %s \n content: %s\n", id,
                s,
                name,
                content);
    }



}

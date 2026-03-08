package com.example.ticketmanager;

public class CreateTicketRequest {
    private String name;
    private String content;
    private User user;

    public CreateTicketRequest(){}

    public String getName() {return name;}
    public String getContent() {return content;}
    public void setName(String name) {this.name = name;}
    public void setContent(String content) {this.content = content;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}

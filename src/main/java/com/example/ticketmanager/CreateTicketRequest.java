package com.example.ticketmanager;
import jakarta.validation.constraints.*;

public class CreateTicketRequest {
    @NotBlank(message = "Name can not be null or empty!")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    private String name;

    @NotBlank(message = "Content can not be null or empty!")
    @Size(min = 5, max = 1000, message = "Content must be between 5 and 1000 characters")
    private String content;

    @NotNull(message = "User ID is required")
    private Long userId;

    public CreateTicketRequest(){}

    public String getName() {return name;}
    public String getContent() {return content;}
    public void setName(String name) {this.name = name;}
    public void setContent(String content) {this.content = content;}

    public long getUserId() {return userId;}

    public void setUser(long userID) {this.userId = userID;}
}

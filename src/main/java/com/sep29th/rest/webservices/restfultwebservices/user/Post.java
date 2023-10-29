package com.sep29th.rest.webservices.restfultwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10, message = "Description must have more than 10 characters")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY) // Lazy sẽ không lấy thông tin của user, EAGLE sẽ lấy cả thông tin user
    @JsonIgnore
    private User user;

    public Post() {
    }

    public Post(String description) {
        this.description = description;
    }

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

package eshop.model;

import java.time.LocalDateTime;

public class Client {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime creationTimestamp;
    private LocalDateTime deletionTimestamp;

    public Client(String firstName,
                  String lastName,
                  LocalDateTime creationTimestamp) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.creationTimestamp = creationTimestamp;
    }

    public Client(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationTimestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDateTime getDeletionTimestamp() {
        return deletionTimestamp;
    }

    public void setDeletionTimestamp(LocalDateTime deletionTimestamp) {
        this.deletionTimestamp = deletionTimestamp;
    }
}
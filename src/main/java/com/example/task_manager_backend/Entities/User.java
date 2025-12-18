package com.example.task_manager_backend.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

// import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false)
    @NotBlank
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;
    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;
    @Column(columnDefinition = "TEXT")
    private String profilePic;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.LAZY)
    private List<Tasks> tasks = new ArrayList<>();

    // Team member 
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true , fetch = FetchType.LAZY)
    private List<TeamMember> teamMembers = new ArrayList<>();

    // Notification 
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Notifications> userNotifications = new ArrayList<>();

    // Messages 
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messages> userMessages = new ArrayList<>();

    // Assests 
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Assests> userAssests = new ArrayList<>();
    // subtask 
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Sub_Task> userSubTask = new ArrayList<>();
    
}

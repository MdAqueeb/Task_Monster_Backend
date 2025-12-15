package com.example.task_manager_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_manager_backend.Service.TeamMemberService;

@RestController
public class TeamMemberController {
    @Autowired
    private TeamMemberService teamMemberService;
}

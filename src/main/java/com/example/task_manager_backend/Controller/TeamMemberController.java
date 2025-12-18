package com.example.task_manager_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.task_manager_backend.Entities.Sub_Task;
import com.example.task_manager_backend.Entities.TeamMember;
import com.example.task_manager_backend.Service.TeamMemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Team Members", description = "Team Members API's")
@RequestMapping("/teamMembers")
public class TeamMemberController {
    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping("/addMember")
    @Operation(summary = "Add new member in task")
    public ResponseEntity<TeamMember> addMember(@RequestBody TeamMember message) {
        TeamMember tsk = teamMemberService.addteamMember(message);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
        
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove specific member in task")
    public ResponseEntity<TeamMember> deleteMember(@PathVariable Long id) {
        TeamMember tsk = teamMemberService.removeTeamMember(id);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.ACCEPTED);
    }
    //     - get all team-member
    
    @GetMapping("/{id}")
    @Operation(summary = "Fetch all team member of particular task")
    public ResponseEntity<List<TeamMember>> getAllTeamMember(@PathVariable Long id) {
        List<TeamMember> tsk = teamMemberService.getAllTeamMembers(id);
        if(tsk == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }
}

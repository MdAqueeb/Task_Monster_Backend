package com.example.task_manager_backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task_manager_backend.Entities.TeamMember;
import com.example.task_manager_backend.Repository.TeamMemberRepo;

@Service
public class TeamMemberService {
    
    @Autowired
    private TeamMemberRepo teamMemberRepo;

    public TeamMember addteamMember(TeamMember message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addteamMember'");
    }

    public TeamMember removeTeamMember(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTeamMember'");
    }

    public List<TeamMember> getAllTeamMembers(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTeamMembers'");
    }
}

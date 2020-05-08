package com.aluminati.onuw.roles;

import com.aluminati.onuw.Role;
import com.aluminati.onuw.Team;

public class Seer extends Role {
    @Override
    public Team getTeam() {
        return Team.VILLAGER;
    }
}

package com.aluminati.onuw.roles;

import com.aluminati.onuw.Team;

public class Villager extends Role {
    @Override
    public Team getTeam() {
        return Team.VILLAGER;
    }
}

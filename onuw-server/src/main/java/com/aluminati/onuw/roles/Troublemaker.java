package com.aluminati.onuw.roles;

import com.aluminati.onuw.Team;

public class Troublemaker extends Role {
    @Override
    public Team getTeam() {
        return Team.VILLAGER;
    }
}

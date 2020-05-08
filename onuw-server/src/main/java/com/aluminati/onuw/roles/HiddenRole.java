package com.aluminati.onuw.roles;

import com.aluminati.onuw.Team;

public class HiddenRole extends Role {
    @Override
    public Team getTeam() {
        return Team.HIDDEN;
    }
}

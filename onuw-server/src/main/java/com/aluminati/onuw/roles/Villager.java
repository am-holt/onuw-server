package com.aluminati.onuw.roles;

import com.aluminati.onuw.Team;
import com.aluminati.onuw.actions.ActionType;

import java.util.Optional;

public class Villager extends Role {
    @Override
    public Team getTeam() {
        return Team.VILLAGER;
    }

    @Override
    public Optional<ActionType> getAction() {
        return Optional.empty();
    }
}

package com.aluminati.onuw.roles;

import com.aluminati.onuw.Team;
import com.aluminati.onuw.actions.ActionType;
import com.aluminati.onuw.actions.SelectPlayersAction;

import java.util.Optional;

public class Werewolf extends Role {
    @Override
    public Team getTeam() {
        return Team.WEREWOLF;
    }

    @Override
    public Optional<ActionType> getAction() {
        return Optional.of(new SelectPlayersAction());
    }
}

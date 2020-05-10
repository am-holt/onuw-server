package com.aluminati.onuw.roles;

import com.aluminati.onuw.RoleType;
import com.aluminati.onuw.Team;
import com.aluminati.onuw.actions.ActionType;

import java.util.Optional;

public abstract class Role {

    public abstract Team getTeam();
    public abstract Optional<ActionType> getAction();

    public static Role from(RoleType roleType) {
        return roleType.accept(new RoleType.Visitor<Role>() {

            @Override
            public Role visitWerewolf() {
                return new Werewolf();
            }

            @Override
            public Role visitSeer() {
                return new Seer();
            }

            @Override
            public Role visitVillager() {
                return new Villager();
            }

            @Override
            public Role visitTroublemaker() {
                return new Troublemaker();
            }

            @Override
            public Role visitHidden() {
                return new HiddenRole();
            }

            @Override
            public Role visitUnknown(String unknownValue) {
                return null;
            }
        });
    }
}

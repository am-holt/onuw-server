package com.aluminati.onuw.actions;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class SelectPlayersAction implements ActionType {

    private boolean isUsed;
    private Set<String> playerIds;

    public SelectPlayersAction() {
        isUsed = false;
        playerIds = new HashSet();
    }

    public void choosePlayer(String playerId, int threshold, Consumer<Set<String>> onSelect) {
        if (playerIds.contains(playerId)) {
            playerIds.remove(playerId);
        } else {
            playerIds.add(playerId);
        }

        if (playerIds.size() >= threshold) {
            onSelect.accept(playerIds);
            isUsed = true;
        }
    }

    @Override
    public boolean isActionUsed() {
        return isUsed;
    }
}

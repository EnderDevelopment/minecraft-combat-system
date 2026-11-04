package com.enderdevelopment.plugin.combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatManager {
    private final Map<UUID, CombatData> combatDataMap = new HashMap<>();

    public void startCombat(Player player1, Player player2) {
        CombatData data = new CombatData(player1, player2);
        combatDataMap.put(player1.getUniqueId(), data);
        combatDataMap.put(player2.getUniqueId(), data);
    }

    public void endCombat(Player player) {
        CombatData data = combatDataMap.remove(player.getUniqueId());
        if (data != null) {
            UUID opponentId = data.getOpponent(player.getUniqueId());
            if (opponentId != null) {
                combatDataMap.remove(opponentId);
            }
        }
    }

    public boolean isInCombat(Player player) {
        return combatDataMap.containsKey(player.getUniqueId());
    }

    public Player getOpponent(Player player) {
        CombatData data = combatDataMap.get(player.getUniqueId());
        if (data != null) {
            UUID opponentId = data.getOpponent(player.getUniqueId());
            if (opponentId != null) {
                return Bukkit.getPlayer(opponentId);
            }
        }
        return null;
    }

    private static class CombatData {
        private final UUID player1;
        private final UUID player2;

        public CombatData(Player player1, Player player2) {
            this.player1 = player1.getUniqueId();
            this.player2 = player2.getUniqueId();
        }

        public UUID getOpponent(UUID playerId) {
            if (playerId.equals(player1)) {
                return player2;
            } else if (playerId.equals(player2)) {
                return player1;
            }
            return null;
        }
    }
}
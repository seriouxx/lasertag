package untitled.untitled.PlayerData;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String kit;
    private String team;
    private long lastShotTime;

    private int kills;
    private int deaths;


    public PlayerData(UUID uuid, String kit, String team) {
        this.uuid = uuid;
        this.kit = kit;
        this.team = team;
        this.lastShotTime = 0;
        kills = 0;
        deaths = 0;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getKit() {
        return kit;
    }

    public String getTeam() {
        return team;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }


    public int getDeaths() {
        return deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }


}

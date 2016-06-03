package tieorange.edu.hellmanager.main.PunishmentTools;

import tieorange.edu.hellmanager.main.PunishmentTools.PunishmentTool;
import tieorange.edu.hellmanager.main.TortureDepartment;

/**
 * Created by tieorange on 29/04/16.
 */
public class ExplosivePunishmentTool extends PunishmentTool {
    private double maxExplosiveDistance;

    public ExplosivePunishmentTool(String name, int damage, TortureDepartment tortureDepartment, Double maxExplosiveDistance, Integer minTemperature) {
        super(name, damage, tortureDepartment, minTemperature);
        setMaxExplosiveDistance(maxExplosiveDistance);
    }

    public ExplosivePunishmentTool(String name, int damage, TortureDepartment tortureDepartment, Double maxExplosiveDistance, Double maxTemperature) {
        super(name, damage, tortureDepartment, maxTemperature);
        setMaxExplosiveDistance(maxExplosiveDistance);
    }

    public double getMaxExplosiveDistance() {
        return maxExplosiveDistance;
    }

    public void setMaxExplosiveDistance(Double maxExplosiveDistance) {
        if (maxExplosiveDistance != null)
            this.maxExplosiveDistance = maxExplosiveDistance;
        else throw new IllegalArgumentException("maxExplosiveDistance is NULL");
    }
}

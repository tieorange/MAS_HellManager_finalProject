package tieorange.edu.hellmanager.main.PunishmentTools;


import tieorange.edu.hellmanager.main.TortureDepartment;

/**
 * Created by tieorange on 29/04/16.
 */
public class CuttingPunishmentTool extends PunishmentTool {
    private int maxCuttingPower;

    public CuttingPunishmentTool(String name, int damage, TortureDepartment tortureDepartment, Integer maxCuttingPower, Integer minTemperature) {
        super(name, damage, tortureDepartment, minTemperature);
        setMaxCuttingPower(maxCuttingPower);
    }

    public CuttingPunishmentTool(String name, int damage, TortureDepartment tortureDepartment, Integer maxCuttingPower, Double maxTemperature) {
        super(name, damage, tortureDepartment, maxTemperature);
        setMaxCuttingPower(maxCuttingPower);
    }

    public int getMaxCuttingPower() {
        return maxCuttingPower;
    }

    public void setMaxCuttingPower(Integer maxCuttingPower) {
        if (maxCuttingPower != null)
            this.maxCuttingPower = maxCuttingPower;
        else throw new IllegalArgumentException("maxCuttingPower is NULL");
    }
}

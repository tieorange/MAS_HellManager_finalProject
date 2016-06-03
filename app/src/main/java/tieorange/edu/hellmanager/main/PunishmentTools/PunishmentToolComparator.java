package tieorange.edu.hellmanager.main.PunishmentTools;

import java.util.Comparator;

/**
 * Created by tieorange on 05/05/16.
 */
public class PunishmentToolComparator implements Comparator<PunishmentTool> {

    @Override
    public int compare(PunishmentTool o1, PunishmentTool o2) {
        return new Integer(o1.getDamage()).compareTo(o2.getDamage());
    }
}
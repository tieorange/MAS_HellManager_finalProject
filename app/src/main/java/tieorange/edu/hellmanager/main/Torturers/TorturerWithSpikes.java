package tieorange.edu.hellmanager.main.Torturers;


import tieorange.edu.hellmanager.main.TortureDepartment;
import tieorange.edu.hellmanager.main.TorturersTorturingDepartment;

/**
 * Created by tieorange on 26/04/16.
 */
public class TorturerWithSpikes extends Torturer {

    public TorturerWithSpikes(String name, TortureDepartment tortureDepartment) {
        super(name + " with spikes", tortureDepartment);
    }

    public TorturerWithSpikes(String name, TorturersTorturingDepartment torturersTorturingDepartment) {
        super(name + " with spikes", torturersTorturingDepartment);
    }

    @Override
    public void punish() {
        System.out.println("Torturer punished with Spikes");
    }
}

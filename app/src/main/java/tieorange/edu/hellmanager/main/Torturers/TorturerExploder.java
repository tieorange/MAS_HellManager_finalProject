package tieorange.edu.hellmanager.main.Torturers;


import tieorange.edu.hellmanager.main.TortureDepartment;
import tieorange.edu.hellmanager.main.TorturersTorturingDepartment;

/**
 * Created by tieorange on 28/04/16.
 */

// Multi-inheritance
public class TorturerExploder extends TorturerWithSpikes implements IExploder {
    private int explosionPower;

    public TorturerExploder(String name, TortureDepartment tortureDepartment, Integer explosionPower) {
        super(name, tortureDepartment);
        setExplosionPower(explosionPower);
    }

    public TorturerExploder(String name, TorturersTorturingDepartment torturersTorturingDepartment, int explosionPower) {
        super(name, torturersTorturingDepartment);
        setExplosionPower(explosionPower);
    }

    @Override
    public int getExplosionPower() {
        return explosionPower;
    }

    @Override
    public void setExplosionPower(Integer explosionPower) {
        if (explosionPower != null) {
            this.explosionPower = explosionPower;
        } else throw new IllegalArgumentException("explosionPower is NULL");
    }

    @Override
    public void makeExplosion() {
        System.out.println("Explosion... baaabaahhhh-booooom");
    }
}

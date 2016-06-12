package tieorange.edu.hellmanager.main.Torturers;

/**
 * Created by tieorange on 28/04/16.
 */
public interface IExploder {
    public int getExplosionPower();

    public void setExplosionPower(Integer explosionPower);

    /**
     * make an explosion
     */
    public void makeExplosion();
}

package tieorange.edu.hellmanager.main.Sinners;

/**
 * Created by tieorange on 26/04/16.
 */
public interface IMurderer {
    public void setAmountOfVictims(Integer amountOfVictims);

    public int getAmountOfVictims();

    /**
     * Murderer tries to kill
     */
    public void Kill();
}

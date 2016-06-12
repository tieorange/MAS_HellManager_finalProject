package tieorange.edu.hellmanager.main;


import java.util.HashMap;
import java.util.Map;

import tieorange.edu.hellmanager.main.Torturers.Torturer;

/**
 * Created by tieorange on 05/05/16.
 */
public class TorturersTorturingDepartment {
    private String name;
    private Map<String, Torturer> torturersMap = new HashMap<>();

    public TorturersTorturingDepartment(String name) {
        super();

    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is NULL");
        }
        this.name = name;
    }

    public Map<String, Torturer> getTorturersMap() {
        return torturersMap;
    }

    /**
     * adds torturer to collection
     * @param torturer
     */
    public void addTorturer(Torturer torturer) {
        if (torturer == null) {
            throw new IllegalArgumentException("torturer is NULL");
        }
        if (torturersMap.containsKey(torturer.getId())) {
            throw new IllegalArgumentException("torturer is already added to this department");
        } else if (torturer.getTortureDepartment() != null) {
            throw new IllegalArgumentException("torturer is already added to TortureDepartment");
        }
        torturersMap.put(torturer.getId(), torturer);
        if (torturer.getTorturersTorturingDepartment() != this) { // check if already set
            torturer.setTorturersTorturingDepartment(this);
        }
    }

}

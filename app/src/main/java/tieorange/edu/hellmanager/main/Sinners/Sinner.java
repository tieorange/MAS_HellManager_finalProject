package tieorange.edu.hellmanager.main.Sinners;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.util.*;

import tieorange.edu.hellmanager.main.SufferingProcess;

/**
 * Created by tieorange on 21/04/16.
 */
public class Sinner implements ILiar, IMurderer {
    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 14;
    private EnumSet<SinnerType> sinnerTypes;


    private Set<Sin> sinsSet = new HashSet<>(); // Composition
    private List<SufferingProcess> sufferingProcessList = new ArrayList<>(); // with attribute // TODO: List<main.SufferingProcess> for having a many to many collection (not 1 - *)
    @NonNull
    @Size(min = 2, max = 14)
    private String firstName;
    private String lastName;
    private Date birthDate;

    private int amountOfLies;
    private int amountOfVictims;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sinner(String firstName, String lastName, Date birthDate, EnumSet<SinnerType> sinnerTypes, Integer amountOfLies, Integer amountOfVictims) {
        if (sinnerTypes != null) {
            if (!sinnerTypes.isEmpty()) {
                this.sinnerTypes = sinnerTypes;
                if (sinnerTypes.contains(SinnerType.LIAR)) {
                    if (amountOfLies != null) {
                        setAmountOfLies(amountOfLies);
                    } else throw new IllegalArgumentException("amountOfLies is mandatory for LIAR");
                }
                if (sinnerTypes.contains(SinnerType.MURDERER)) {
                    if (amountOfVictims != null) {
                        setAmountOfVictims(amountOfVictims);
                    } else throw new IllegalArgumentException("amountOfVictims is mandatory for MURDERER");
                }
                setFirstName(firstName);
                setLastName(lastName);
                setBirthDate(birthDate);
            } else throw new IllegalArgumentException("sinnerTypes is empty");
        } else throw new IllegalArgumentException("sinnerTypes is NULL");
    }


    //region methods
    public void addSin(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name should not be null");
        } else if (isSinAlreadyExist(name)) {
            throw new IllegalArgumentException("Sin with such a name already added");
        } else {
            sinsSet.add(new Sin(name));
        }

    }

    public boolean isSinAlreadyExist(String name) {
        boolean isAlreadyExist = false;
        for (Sin sin : sinsSet) {
            if (sin.getName().equals(name))
                isAlreadyExist = true;
        }
        return isAlreadyExist;
    }

    public void removeSin(String name) {
        if (name != null) {
            for (Sin sin : sinsSet) {
                if (sin.getName().equals(name))
                    sinsSet.remove(sin);
            }
        } else {
            throw new IllegalArgumentException("Name should not be null");
        }
//        for(Iterator<Sin> sin = sinsSet.iterator() : sin. ){
    }
    //endregion


    //region Getter & setters
    public List<String> getSinsNames() {
        List<String> sinsNames = new ArrayList<>();
        for (Sin sin : sinsSet) {
            sinsNames.add(sin.getName());
        }
        return sinsNames;
    }

    public void addSufferingProcess(SufferingProcess process) {
        if (process == null) {
            throw new IllegalArgumentException("process is NULL");
        }
        if (!process.getSinner().equals(this)) {
            throw new IllegalArgumentException("process belongs to another sinner");
        }
        sufferingProcessList.add(process);

    }

    public void removeSufferingProcess(SufferingProcess process) {
        if (process == null) {
            throw new IllegalArgumentException("process is NULL");
        }
        if (!process.getSinner().equals(process)) {
            throw new IllegalArgumentException("process belongs to another sinner");
        }
        sufferingProcessList.remove(process);
    }

    public List<SufferingProcess> getSufferingProcesses() {
        return this.sufferingProcessList;
    }


    /*public SufferingProcess getSufferingProcessList() {
        return sufferingProcessList;
    }

    public void setSufferingProcessList(SufferingProcess sufferingProcessList) {
        if (sufferingProcessList == null) {
            throw new IllegalArgumentException("main.SufferingProcess is NULL");
        }
        if (sufferingProcessList.getSinner() != this) {
            throw new IllegalArgumentException("main.SufferingProcess sinner is different");
        } else {
            this.sufferingProcessList = sufferingProcessList;
            if (sufferingProcessList.getSinner() != this)
                sufferingProcessList.setSinner(this);
        }
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("firstName is NULL");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("lastName is NULL");
        } else if (lastName.length() < NAME_MIN_LENGTH || lastName.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("lastName is too long or too short");
        } else {
            this.lastName = lastName;
        }
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("birthDate is NULL");
        } else {
            this.birthDate = birthDate;
        }
    }

    public Set<Sin> getSinsSet() {
        return sinsSet;
    }

    public void setSinsSet(Set<Sin> sinsSet) {
        this.sinsSet = sinsSet;
    }
    //endregion

    //region Getters and Setters and Methods from Interfaces
    @Override
    public void setAmountOfLies(Integer amountOfLies) {
        if (amountOfLies >= 0) {
            this.amountOfLies = amountOfLies;
        } else {
            throw new IllegalArgumentException("amountOfLies should be >= 0");
        }
    }

    @Override
    public int getAmountOfLies() {
        return amountOfLies;
    }


    @Override
    public void setAmountOfVictims(Integer amountOfVictims) {
        // TODO: CHECK THE TYPE
        if (amountOfVictims >= 0) {
            this.amountOfVictims = amountOfVictims;
        } else throw new IllegalArgumentException("amountOfVictims should be >= 0");

    }

    @Override
    public int getAmountOfVictims() {
        return amountOfVictims;
    }

    @Override
    public void Kill() {
        if (sinnerTypes.contains(SinnerType.MURDERER)) {
            System.out.println("Sinner killed another Sinner");
        } else throw new RuntimeException("Sinner is not a Murderer");
    }

    @Override
    public void tryToLie() {
        if (sinnerTypes.contains(SinnerType.LIAR)) {
            System.out.println("Sinner lied to another Sinner");
        } else throw new RuntimeException("Sinner is not a liar");
    }
    //endregion

    private class Sin {
        private String name;

        public Sin(String name) {
            setName(name);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (name != null) {
                this.name = name;
            } else {
                throw new IllegalArgumentException("Name can't be null");
            }
        }
    }
}


package tieorange.edu.hellmanager.Entities;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tieorange.edu.hellmanager.main.Sinners.ILiar;
import tieorange.edu.hellmanager.main.Sinners.IMurderer;

/**
 * Created by tieorange on 04/06/16.
 */
public class SinnerEntity extends RealmObject {
    @PrimaryKey
    private String id;

    private boolean isLiar = false;
    private boolean isMurderer = false;

    private RealmList<SufferingProcessEntity> sufferingProcessList;
    private String firstName;
    private String lastName;
    private Date birthDate;

    private Integer amountOfLies;
    private Integer amountOfVictims;

    public SinnerEntity() {
    }

    public SinnerEntity(String id, boolean isLiar, boolean isMurderer, RealmList<SufferingProcessEntity> sufferingProcessList, String firstName, String lastName, Date birthDate, Integer amountOfLies, Integer amountOfVictims) {
        this.id = id;
        this.isLiar = isLiar;
        this.isMurderer = isMurderer;
        this.sufferingProcessList = sufferingProcessList;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.amountOfLies = amountOfLies;
        this.amountOfVictims = amountOfVictims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLiar() {
        return isLiar;
    }

    public void setLiar(boolean liar) {
        isLiar = liar;
    }

    public boolean isMurderer() {
        return isMurderer;
    }

    public void setMurderer(boolean murderer) {
        isMurderer = murderer;
    }

    public RealmList<SufferingProcessEntity> getSufferingProcessList() {
        return sufferingProcessList;
    }

    public void setSufferingProcessList(RealmList<SufferingProcessEntity> sufferingProcessList) {
        this.sufferingProcessList = sufferingProcessList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAmountOfLies() {
        return amountOfLies;
    }

    public void setAmountOfLies(Integer amountOfLies) {
        this.amountOfLies = amountOfLies;
    }

    public Integer getAmountOfVictims() {
        return amountOfVictims;
    }

    public void setAmountOfVictims(Integer amountOfVictims) {
        this.amountOfVictims = amountOfVictims;
    }
}

package tieorange.edu.hellmanager.Entities;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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

    private RealmList<SufferingProcessEntity> sufferingProcessList = new RealmList<>();
    private RealmList<DeadlySin> sinsList = new RealmList<>();
    private String firstName;
    private String lastName;
    private Date birthDate;

    private Integer amountOfLies;
    private Integer amountOfVictims;

    public SinnerEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public SinnerEntity(boolean isLiar, boolean isMurderer, RealmList<SufferingProcessEntity> sufferingProcessList, String firstName, String lastName, Date birthDate, Integer amountOfLies, Integer amountOfVictims) {
        this();
        this.id = UUID.randomUUID().toString();
        this.isLiar = isLiar;
        this.isMurderer = isMurderer;
//        this.sufferingProcessList = sufferingProcessList;
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

    public RealmList<DeadlySin> getSinsList() {
        return sinsList;
    }

    public void setSinsList(RealmList<DeadlySin> sinsList) {
        this.sinsList = sinsList;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String birthDate = dateFormat.format(getBirthDate());

        final String format = MessageFormat.format("\uD83D\uDC79 {0} {1} \nbirth date: {2}",
                getFirstName(), getLastName(), birthDate);
        StringBuilder result = new StringBuilder(format.toString());
        if (isLiar)
            result.append("\nlies: " + getAmountOfLies());
        if (isMurderer)
            result.append("\nvictims: " + getAmountOfVictims());
        if (sinsList.size() > 0)
            result.append("\nsins: " + sinsList.size());
        return result.toString();
    }
}

package tieorange.edu.hellmanager.Entities;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tieorange.edu.hellmanager.main.Sinners.Sinner;
import tieorange.edu.hellmanager.main.TortureDepartment;

/**
 * Created by tieorange on 04/06/16.
 */
public class SufferingProcessEntity extends RealmObject {
    @PrimaryKey
    private String id;

    private Date startDate;
    private Date finishDate;
    private TortureDepartmentEntity tortureDepartment;
    private SinnerEntity sinner;

    public SufferingProcessEntity() {
    }

    public SufferingProcessEntity(String id, Date startDate, Date finishDate, TortureDepartmentEntity tortureDepartment, SinnerEntity sinner) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.tortureDepartment = tortureDepartment;
        this.sinner = sinner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public TortureDepartmentEntity getTortureDepartment() {
        return tortureDepartment;
    }

    public void setTortureDepartment(TortureDepartmentEntity tortureDepartment) {
        this.tortureDepartment = tortureDepartment;
    }

    public SinnerEntity getSinner() {
        return sinner;
    }

    public void setSinner(SinnerEntity sinner) {
        this.sinner = sinner;
    }
}

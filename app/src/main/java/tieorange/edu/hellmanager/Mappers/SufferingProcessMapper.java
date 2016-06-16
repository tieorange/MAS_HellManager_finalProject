package tieorange.edu.hellmanager.Mappers;

import java.util.List;

import io.realm.RealmList;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.SufferingProcessEntity;
import tieorange.edu.hellmanager.main.Sinners.Sinner;
import tieorange.edu.hellmanager.main.SufferingProcess;

/**
 * Created by tieorange on 16/06/16.
 */
public class SufferingProcessMapper {
    static SufferingProcessEntity map(SufferingProcess sufferingProcess) {
        SufferingProcessEntity sufferingProcessEntity = new SufferingProcessEntity();
        sufferingProcessEntity.setStartDate(sufferingProcess.getStartDate());
        sufferingProcessEntity.setFinishDate(sufferingProcess.getFinishDate());
        return null;
    }

    public static RealmList<SufferingProcessEntity> map(List<SufferingProcess> sufferingProcesses) {
        if(sufferingProcesses == null){
            return new RealmList<SufferingProcessEntity>();
        }
        RealmList<SufferingProcessEntity> sufferingProcessEntities = new RealmList<>();

        for (SufferingProcess sufferingProcess : sufferingProcesses) {
            SufferingProcessEntity sufferingProcessEntity = map(sufferingProcess);
        }

        return null;
    }
}

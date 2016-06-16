package tieorange.edu.hellmanager.Mappers;

import android.support.annotation.NonNull;

import io.realm.RealmList;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.SufferingProcessEntity;
import tieorange.edu.hellmanager.main.Sinners.Sinner;
import tieorange.edu.hellmanager.main.Sinners.SinnerType;

/**
 * Created by tieorange on 16/06/16.
 */
public class Mappers {

    public static SinnerEntity mapSinnerToEntity(Sinner sinner) {
        SinnerEntity entity = mapSinnerToEntityWithoutProcess(sinner);

        // Suffering Process
        RealmList<SufferingProcessEntity> sufferingProcessEntities =
                SufferingProcessMapper.map(sinner.getSufferingProcesses());
        entity.setSufferingProcessList(sufferingProcessEntities);




        /*for (SufferingProcess sufferingProcess : sufferingProcesses) {
            SufferingProcessEntity sufferingProcessEntity = SufferingProcessMapper.map(sinner, entity, sufferingProcess);
            TortureDepartmentEntity tortureDepartmentEntity = TortureDepartmentMapper.mapTortureDepartment(sufferingProcess);
            sufferingProcessEntity.setTortureDepartment(tortureDepartmentEntity);
        }*/


        return entity;
    }

    @NonNull
    private static SinnerEntity mapSinnerToEntityWithoutProcess(Sinner sinner) {
        SinnerEntity entity = new SinnerEntity();
        entity.setFirstName(sinner.getFirstName());
        entity.setLastName(sinner.getLastName());
        entity.setBirthDate(sinner.getBirthDate());
        if (sinner.getSinnerTypes().contains(SinnerType.LIAR)) {
            entity.setLiar(true);
            entity.setAmountOfLies(sinner.getAmountOfLies());
        } else if (sinner.getSinnerTypes().contains(SinnerType.MURDERER)) {
            entity.setMurderer(false);
            entity.setAmountOfVictims(sinner.getAmountOfVictims());
        }
        return entity;
    }


}

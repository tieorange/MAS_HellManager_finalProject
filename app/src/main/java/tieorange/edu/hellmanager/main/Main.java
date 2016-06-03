package tieorange.edu.hellmanager.main;


import java.util.*;

import tieorange.edu.hellmanager.main.HellPets.HellPet;
import tieorange.edu.hellmanager.main.HellPets.HellPetColor;
import tieorange.edu.hellmanager.main.HellPets.HellPetType;
import tieorange.edu.hellmanager.main.MyUtils.MyTools;
import tieorange.edu.hellmanager.main.Sinners.Sinner;
import tieorange.edu.hellmanager.main.Sinners.SinnerType;
import tieorange.edu.hellmanager.main.Torturers.Torturer;
import tieorange.edu.hellmanager.main.Torturers.TorturerWithSpikes;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Collection<Torturer> torturers = new ArrayList<>();
//        torturers.add(new TorturerWithKnife())

        // OVERLAPPING
        EnumSet<SinnerType> sinnerTypes = EnumSet.of(SinnerType.LIAR, SinnerType.MURDERER);
        Sinner sinner = new Sinner("bob", "marley", new Date(), sinnerTypes, 3, 3);
        sinner.Kill();
        sinner.tryToLie();


        TortureDepartment tortureDepartment = new TortureDepartment("boiling room");

        // DYNAMIC
        TorturerWithSpikes torturerWithSpikes = new TorturerWithSpikes("Mifelos", tortureDepartment);
        HellPet hellPet = new HellPet("bonia", HellPetColor.GREEN, torturerWithSpikes, HellPetType.FLYING, null, 100);
        hellPet.becomeJumping(1000);

//        testSubset_manual();
        testSubset_auto();

        testSufferingProcess();

        testXor();

        testCustomBusinessConstraint();
    }

    private static void testCustomBusinessConstraint() {
        TortureDepartment tortureDepartment = new TortureDepartment("boiling room");
        EnumSet<SinnerType> sinnerTypes = EnumSet.of(SinnerType.LIAR, SinnerType.MURDERER);
        Sinner sinner = new Sinner("bob", "marley", new Date(), sinnerTypes, 3, 3);
        Sinner sinner2 = new Sinner("bob2", "marley2", new Date(), sinnerTypes, 3, 3);

        SufferingProcess process = new SufferingProcess(MyTools.getStartDateExample(), MyTools.getEndDateExample(), tortureDepartment, sinner);
//        SufferingProcess processFAILED = new SufferingProcess(MyTools.getEndDateExample(), MyTools.getEndDateExample(), tortureDepartment, sinner);
    }

    private static void testXor() {
        TortureDepartment tortureDepartment = new TortureDepartment("boiling room");
        TorturersTorturingDepartment torturersTorturingDepartment = new TorturersTorturingDepartment("hell for torturers");

        TorturerWithSpikes torturerWithSpikesBAD = new TorturerWithSpikes("Mifelos", tortureDepartment);
//        torturerWithSpikesBAD.setTorturersTorturingDepartment(torturersTorturingDepartment);

    }

    private static void testSufferingProcess() {
        TortureDepartment tortureDepartment = new TortureDepartment("boiling room");
        EnumSet<SinnerType> sinnerTypes = EnumSet.of(SinnerType.LIAR, SinnerType.MURDERER);
        Sinner sinner = new Sinner("bob", "marley", new Date(), sinnerTypes, 3, 3);
        Sinner sinner2 = new Sinner("bob2", "marley2", new Date(), sinnerTypes, 3, 3);

        SufferingProcess process = new SufferingProcess(MyTools.getStartDateExample(), MyTools.getEndDateExample(), tortureDepartment, sinner);
        sinner.addSufferingProcess(process);
    }


    public static void testSubset_manual() {
        TortureDepartment tortureDepartment = new TortureDepartment("boiling room");
        TorturerWithSpikes torturerWithSpikes = new TorturerWithSpikes("Mifelos", tortureDepartment);

        try {
            torturerWithSpikes.addLink(Torturer.roleBelongsTo, TortureDepartment.roleConsistOf, tortureDepartment);

            if (torturerWithSpikes.isLink(Torturer.roleBelongsTo, tortureDepartment)) {
                torturerWithSpikes.addLink(Torturer.roleManages, TortureDepartment.roleManagedBy, tortureDepartment);
            } else {
                System.out.println("No super link for the role: " + Torturer.roleBelongsTo);
            }


            torturerWithSpikes.showLinks(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSubset_auto() {
        TortureDepartment department = new TortureDepartment("boiling room");
        TorturerWithSpikes torturer = new TorturerWithSpikes("Mifelos", department);

        try {
            torturer.addLink(Torturer.roleBelongsTo, TortureDepartment.roleConsistOf, department);

            torturer.addLink_subset(Torturer.roleManages, TortureDepartment.roleManagedBy, Torturer.roleBelongsTo, department);

            torturer.showLinks(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

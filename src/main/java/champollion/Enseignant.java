package champollion;
import lombok.Getter;

import java.util.ArrayList;
import java.lang.Math;
/**
 * Un enseignant est caractérisé par les informations suivantes : son nom, son adresse email, et son service prévu,
 * et son emploi du temps.
 */
@Getter
public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
    private ServicePrevu servicePrev;
    private ArrayList<Intervention> emploiDuTemps;

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        // TODO: Implémenter cette méthode
         int nbTotalHeureEnEquivalentTD = 0;
        if (this.servicePrev != null){
            nbTotalHeureEnEquivalentTD += this.servicePrev.getVolumeCM() * 1.5 + this.servicePrev.getVolumeTD() * 1 + this.servicePrev.getVolumeTP() * 0.75;

        }

        return Math.round( nbTotalHeureEnEquivalentTD );
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        // TODO: Implémenter cette méthode
        if (this.servicePrev != null){
            if (this.servicePrev.getListeUE().contains( ue ) ) {
                return ue.getHeuresCM() + ue.getHeuresTD() + ue.getHeuresTP();
            }
            else {
                throw new IllegalStateException( " Cet enseignant n'enseigne pas cette UE.");
            }
        }
        else {
                throw new IllegalStateException( " Cet enseignant n'enseigne pas d'UE pour l'instant.");

            }


    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magistral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        // TODO: Implémenter cette méthode
        if (this.servicePrev == null){
            this.servicePrev = new ServicePrevu();
        }
        this.servicePrev.setVolumeCM ( this.servicePrev.getVolumeCM() + volumeCM );
        this.servicePrev.setVolumeTD ( this.servicePrev.getVolumeTD() + volumeTD );
        this.servicePrev.setVolumeTP ( this.servicePrev.getVolumeTP() + volumeTP );

        ue.setHeuresCM( ue.getHeuresCM() + volumeCM );
        ue.setHeuresTD( ue.getHeuresTD() + volumeTD );
        ue.setHeuresTP( ue.getHeuresTP() + volumeTP );
        this.servicePrev.ajouterUE( ue );
    }

    public void ajouteIntervention(Intervention inter) {
        if (this.emploiDuTemps == null){
            this.emploiDuTemps = new ArrayList<>();
        }
        this.emploiDuTemps.add(inter);
    }

    public boolean enSousService(){
        return heuresPrevues() < 192;
    }


}

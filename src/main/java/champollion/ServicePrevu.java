package champollion;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class ServicePrevu {
	// TODO : implémenter cette classe

    private ArrayList<UE> listeUE;
    private int volumeCM;
    private int volumeTD;
    private int volumeTP;

    // méthode pour ajouter une ue à listeUE
    public void ajouterUE(UE ue) {
        listeUE.add(ue);
    }

    //constructeur
    public ServicePrevu(){
        listeUE = new ArrayList<>();
        volumeCM = 0;
        volumeTD = 0;
        volumeTP = 0;

    }

}

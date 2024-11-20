package champollion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UE {
    private final String intitule;
    private int heuresCM;
    private int heuresTD;
    private int heuresTP;


    public UE(String myIntitule) {
        intitule = myIntitule;
    }




}

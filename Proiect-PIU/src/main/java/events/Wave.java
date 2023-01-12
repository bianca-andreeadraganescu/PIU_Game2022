package events;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class Wave {

    private ArrayList<Integer> enemyList;

    public Wave(ArrayList<Integer> enemyList){
        this.enemyList =enemyList;
    }
}

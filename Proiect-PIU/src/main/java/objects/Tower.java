package objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tower {
    private int x, y, id, towerType;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
    }
}

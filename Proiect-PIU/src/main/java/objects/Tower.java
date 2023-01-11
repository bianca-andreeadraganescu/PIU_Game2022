package objects;

import helpz.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tower {
    private int x, y, id, towerType;
    private float dmg, range, cooldown;
    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        dmg = Constants.Towers.GetStartCoolDown(towerType);
        range = Constants.Towers.GetStartRange(towerType);
        cooldown = Constants.Towers.GetStartDmg(towerType);
    }

}

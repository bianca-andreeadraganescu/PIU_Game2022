package objects;

import helpz.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tower {
    private int x, y, id, towerType, cdTick,dmg;
    private float range, cooldown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        cooldown = Constants.Towers.GetStartCoolDown(towerType);
        range = Constants.Towers.GetStartRange(towerType);
        dmg = Constants.Towers.GetStartDmg(towerType);
    }

    public boolean isCooldownOver() {
        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
    }

    public void update(){
        cdTick++;
    }
}

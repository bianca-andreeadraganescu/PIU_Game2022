public class Enemy implements IEnemy{

    int life =0;
    int speed= 500;

    public Enemy(int life, int speed) {
        this.life = life;
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

package helpz;

public class Constants {
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Enemies{
        public static final int MONSTER = 0;
        public static final int BAT = 1;

        public static float GetSpeed(int enemyType){
            return switch (enemyType) {
                case MONSTER -> 0.5F;
                case BAT -> 0.65F;
                default -> 0;
            };
        }

        public static int GetStartHealth(int enemyType) {
            return switch (enemyType) {
                case MONSTER -> 60;
                case BAT -> 100;
                default -> 0;
            };
        }
    }

    public static class Towers{
        public static final int CANON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;
        public static String GetName(int towerType){
            return switch (towerType) {
                case CANON -> "Cannon";
                case ARCHER -> "Archer";
                case WIZARD -> "Wizard";
                default -> "";
            };
        }

        public static float GetStartDmg(int towerType){
            return switch (towerType) {
                case CANON -> 25;
                case ARCHER -> 15;
                case WIZARD -> 5;
                default ->100;
            };
        }
        public static float GetStartRange(int towerType){
            return switch (towerType) {
                case CANON -> 100;
                case ARCHER -> 100;
                case WIZARD -> 100;
                default ->100;
            };
        }

        public static float GetStartCoolDown(int towerType){
            return switch (towerType) {
                case CANON -> 10;
                case ARCHER -> 10;
                case WIZARD -> 10;
                default ->100;
            };
        }

    }
    public static class Tiles{
        public static final int MARGIN_TILE = 0;
        public static final int MIDLINE_TILE = 1;
        public static final int REDLINE_TILE = 2;


    }
}

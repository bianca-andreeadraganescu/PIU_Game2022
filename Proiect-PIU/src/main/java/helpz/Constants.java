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
            switch (enemyType){
                case MONSTER:
                    return 0.5F;
                case BAT:
                    return 0.65F;
            }
            return 0;
        }
    }

    public static class Towers{
        public static final int CANON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

    }
    public static class Tiles{
        public static final int MARGIN_TILE = 0;
        public static final int MIDLINE_TILE = 1;
        public static final int REDLINE_TILE = 2;


    }
}

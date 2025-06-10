public class Tetromino {
    private int[][] shape;
    private int row;
    private int col;

    public Tetromino(int[][] shape){
        this.shape = shape;
        this.row = 0;
        this.col = 4;
    }

    public int[][]getShape(){
        return shape;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    public void moveRight(){
        col++;
    }
    public void moveLeft(){
        col--;
    }
    public void moveDown(){
        row++;
    }

    //create shapes
    public static int[][] LINE_SHAPE = {
            {1, 1, 1, 1}
    };
    public static int[][] T_SHAPE = {
            {0, 1, 0},
            {1, 1, 1}
    };
    public static int[][] L_SHAPE = {
            {0, 0, 1},
            {1, 1, 1}
    };
    public static int[][] J_SHAPE = {
            {1, 0, 0},
            {1, 1, 1}
    };
    public static int[][] SQUARE_SHAPE = {
            {1, 1},
            {1, 1}
    };
    public static int[][] Z_SHAPE = {
            {1, 1, 0},
            {0, 1, 1}
    };
    public static int[][] S_SHAPE = {
            {0, 1, 1},
            {1, 1, 0}
    };


}

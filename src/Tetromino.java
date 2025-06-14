import java.util.Random;

public class Tetromino {
    private String type;
    private int[][] shape;
    private int row;
    private int col;

    public Tetromino(int[][] shape, String type){
        this.shape = shape;
        this.type = type;
        this.row = 0;
        this.col = 3;
    }

    public String toString(){
        return "Piece type is: " + type;
    }

    public int[][]getShape(){
        return shape;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int newRow){
        this.row = newRow;
    }
    public void setCol(int newCol){
        this.col = newCol;
    }
    public int getCol(){
        return col;
    }


    public void moveLeft(){
        col--;
    }
    public void moveDown(){
        row++;
    }

    public void rotate(){
        int x = shape.length;
        int[][] rotatedShape = new int[x][x];
        //transpose step
        for(int i = 0; i < x; i++){
            for(int j = i + 1; j < x; j++){
                int temp = shape[i][j];
                shape[i][j] = shape[j][i];
                shape[j][i] = temp;
            }
        }
        //flip rows over middle y axis
        for(int i = 0; i < x; i++){
            for(int j = 0; j < x/2; j++){
                int temp = shape[i][j];
                shape[i][j] = shape[i][x - 1 - j];
                shape[i][x - 1 - j] = temp;
            }
        }
    }

    //create shapes
    public static int[][] LINE_SHAPE = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0 ,0}
    };
    public static int[][] T_SHAPE = {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
    };
    public static int[][] L_SHAPE = {
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
    };
    public static int[][] J_SHAPE = {
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
    };

    public static int[][] SQUARE_SHAPE = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 0}
    };

    public static int[][] Z_SHAPE = {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
    };
    public static int[][] S_SHAPE = {
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}
    };

    public static Tetromino randomShape(){
        Random random = new Random();
        int rand = random.nextInt(7);
        if(rand == 0){
            return new Tetromino(LINE_SHAPE, "Line");
        } else if (rand == 1) {
            return new Tetromino(T_SHAPE, "T");
        } else if (rand == 2) {
            return new Tetromino(L_SHAPE, "L");
        } else if (rand == 3) {
            return new Tetromino(J_SHAPE, "J");
        } else if (rand == 4) {
            return new Tetromino(Z_SHAPE, "Z");
        } else if (rand == 5) {
            return new Tetromino(S_SHAPE, "S");
        } else{
            return new Tetromino(SQUARE_SHAPE, "Square");
        }
    }




}

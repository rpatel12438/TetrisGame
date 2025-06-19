import java.util.Random;
import java.awt.*;



public class Tetromino {
    private String type;
    private int[][] shape;
    private int row;
    private int col;
    private Color color;

    /**
     * Constructor for Tetris piece
     * @param shape
     * @param type
     */
    public Tetromino(int[][] shape, String type, Color color){
        this.shape = copyShape(shape);
        this.type = type;
        this.row = 0;
        this.col = 3;
        this.color = color;
    }

    /**
     * Creates a copy of the current piece to ensure proper movement, and keep original shape in case of rotates
     * @param original
     * @return
     */
    private int[][] copyShape(int[][] original){
        int[][] copy = new int[original.length][original[0].length];
        for(int i = 0; i < original.length; i++){
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    public String toString(){
        return "Piece type is: " + type;
    }

    public Color getColor(){
        return color;
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

    public void moveDown(){
        row++;
    }
    /**
     * Rotates the current piece 90 degrees clockwise by transposing then flipping over rows
     */
    public void rotate(){
        int x = shape.length;
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

    /**
     * Check bounds to see if the current piece is able to rotate
     * @param grid
     * @return
     */
    public boolean canRotate(Color[][] grid){
        int[][] rotated = new int[shape.length][shape.length];
        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                rotated[i][j] = shape[i][j];
            }
        }
        for(int i = 0; i < shape.length; i++){
            for(int j = i + 1; j < shape[i].length; j++){
                int temp = rotated[i][j];
                rotated[i][j] = rotated[j][i];
                rotated[j][i] = temp;
            }
        }
        //flip rows over middle y axis
        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length/2; j++){
                int temp = rotated[i][j];
                rotated[i][j] = rotated[i][shape.length - 1 - j];
                rotated[i][shape.length - 1 - j] = temp;
            }
        }
        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                if(rotated[i][j] == 1){
                    int newRow = row + i;
                    int newCol = col + j;
                    if(newRow >= grid.length || newRow < 0 || newCol < 0 || newCol >= grid[0].length){
                        return false;
                    }
                    if(grid[newRow][newCol] != Color.black){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //create shapes of all Tetrominos
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

    /**
     * Gets random number from math.random, and based on this number a shape is chosen
     * @return
     */
    public static Tetromino randomShape(){
        Random random = new Random();
        int rand = random.nextInt(7);
        if(rand == 0){
            return new Tetromino(LINE_SHAPE, "Line", Color.cyan);
        } else if (rand == 1) {
            return new Tetromino(T_SHAPE, "T", Color.magenta);
        } else if (rand == 2) {
            return new Tetromino(L_SHAPE, "L", Color.orange);
        } else if (rand == 3) {
            return new Tetromino(J_SHAPE, "J", Color.blue);
        } else if (rand == 4) {
            return new Tetromino(Z_SHAPE, "Z", Color.red);
        } else if (rand == 5) {
            return new Tetromino(S_SHAPE, "S", Color.green);
        } else{
            return new Tetromino(SQUARE_SHAPE, "Square", Color.yellow);
        }
    }


}

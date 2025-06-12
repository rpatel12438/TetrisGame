import java.util.*;

public class Main {
    static Tetromino currentPiece;
    public static void main(String[] args) {
        System.out.println("Welcome to Tetris!");
        startGame();
        currentPiece = Tetromino.randomShape();
        placePiece(currentPiece);
        System.out.println();
        printGrid();
        autoDrop(currentPiece);

    }


    public static void startGame(){
        System.out.println("Game started.");
        initializeGrid();
        printGrid();
    }

    static char[][] grid = new char[20][10];

    public static void initializeGrid(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                grid[row][col] = '.';
            }
        }
    }
    public static void printGrid(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }

    public static void placePiece(Tetromino piece){
        int[][] shape = piece.getShape();
        int startCol = piece.getCol();
        int startRow = piece.getRow();
        for(int row = 0; row < shape.length; row++){
            for(int col = 0; col < shape[row].length; col++){
                if(shape[row][col] == 1){
                    grid[startRow + row][startCol + col] = '#';
                }
            }
        }
    }


    public static void autoDrop(Tetromino piece){
        piece = currentPiece;
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                int col = currentPiece.getCol();
                int row = currentPiece.getRow();
                int [][] shape = currentPiece.getShape();
                System.out.println("Current row: " + row + "current col: " + col);
                for(int i = 0; i < currentPiece.getShape().length; i++){
                    for(int j = 0; j < currentPiece.getShape()[i].length; j++){
                            if (shape[i][j] == 1){
                                grid[row + i][col + j] = '.';
                                System.out.println();
                            }
                    }
                }
                if(canMoveDown() == true) {
                    currentPiece.setRow(currentPiece.getRow() + 1);
                    placePiece(currentPiece);
                }else{
                    placePiece(currentPiece);
                    currentPiece = Tetromino.randomShape();
                    placePiece(currentPiece);
                }
                printGrid();
            }
        };
        timer.scheduleAtFixedRate(task, 0,1000);
        printGrid();
    }

    public static boolean canMoveDown(){
        int row = currentPiece.getRow();
        int col = currentPiece.getCol();
        int[][] shape = currentPiece.getShape();

        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                if(shape[i][j] == 1){
                    int newRow = row + i + 1;
                    int newCol = col + j;

                    if(newRow >= grid.length){
                        return false;
                    }

                    if(grid[newRow][newCol] == '#'){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
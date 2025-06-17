import java.util.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main {
    static Tetromino currentPiece;
    public static void main(String[] args) {
        System.out.println("Welcome to Tetris!");
        startGame();
        currentPiece = Tetromino.randomShape();
        placePiece(currentPiece);
        System.out.println();
        GamePanel panel = new GamePanel((grid));
        GameWindow window = new GameWindow(panel);
        autoDrop(panel);

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

    public static void erasePiece(){
        int [][] shape = currentPiece.getShape();
        int col = currentPiece.getCol();
        int row = currentPiece.getRow();
        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                if(shape[i][j] == 1){
                    grid[row + i][col + j] = '.';
                }
            }
        }
    }


    public static void autoDrop(GamePanel panel){
        java.util.Timer timer = new java.util.Timer();
        java.util.TimerTask task = new java.util.TimerTask(){
            @Override
            public void run(){
                int col = currentPiece.getCol();
                int row = currentPiece.getRow();
                int [][] shape = currentPiece.getShape();
                System.out.println("Current row: " + row + "current col: " + col);
                erasePiece();
                if(canMoveDown() == true) {
                    currentPiece.setRow(row + 1);
                    moveRight();
                    if(currentPiece.canRotate(grid)){
                        currentPiece.rotate();
                    }
                    placePiece(currentPiece);
                }else{
                    placePiece(currentPiece);
                    currentPiece = Tetromino.randomShape();
                    placePiece(currentPiece);
                }
                printGrid();
                panel.repaint();
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

    public static void moveRight(){
        int col = currentPiece.getCol();
        int row = currentPiece.getRow();
        int [][] shape = currentPiece.getShape();
        erasePiece();
        if(canMoveRight() == true) {
            currentPiece.setCol(col + 1);
            placePiece(currentPiece);
        }
    }

    public static boolean canMoveRight(){
        int row = currentPiece.getRow();
        int col = currentPiece.getCol();
        int[][] shape = currentPiece.getShape();

        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                if(shape[i][j] == 1){
                    int newRow = row + i;
                    int newCol = col + j + 1;

                    if(newCol >= grid[0].length){
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

    public static void moveLeft(){
        int [][] shape = currentPiece.getShape();
        int col = currentPiece.getCol();
        int row = currentPiece.getRow();
        erasePiece();
        if(canMoveLeft() == true){
            currentPiece.setCol(col - 1);
            placePiece(currentPiece);
        }

    }

    public static boolean canMoveLeft(){
        int [][] shape = currentPiece.getShape();
        int col = currentPiece.getCol();
        int row = currentPiece.getRow();

        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                if(shape[i][j] == 1){
                    int newRow = row + i;
                    int newCol = col + j - 1;
                    if(newCol < 0 || grid[newRow][newCol] == '#'){
                        return false;
                    }

                }
            }
        }
        return true;
    }

}
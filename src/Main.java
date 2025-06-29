import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main {
    static Tetromino currentPiece;

    /**
     * Shows the game window and game panel and starts the autodrop to begin Tetris game
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Tetris!");
        startGame();
        currentPiece = Tetromino.randomShape();
        System.out.println();
        GamePanel panel = new GamePanel((grid));
        GameWindow window = new GameWindow(panel);
        autoDrop(panel);

    }

    /**
     Gets the game ready for user by showing board
     */
    public static void startGame(){
        System.out.println("Game started.");
        initializeGrid();
        printGrid();
    }

    static Color[][] grid = new Color[20][10];

    /**
     * Create 2D array to represent the grid
     */
    public static void initializeGrid(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                grid[row][col] = Color.darkGray;
            }
        }
    }

    /**
     * Iterate through 2D array to print the actual board
     */
    public static void printGrid(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * Searches where a piece is on the grid, then prints '#' if there is a piece
     * @param piece
     */
    public static void placePiece(Tetromino piece){
        int[][] shape = piece.getShape();
        int startCol = piece.getCol();
        int startRow = piece.getRow();
        Color color = piece.getColor();
        for(int row = 0; row < shape.length; row++){
            for(int col = 0; col < shape[row].length; col++){
                if(shape[row][col] == 1){
                    grid[startRow + row][startCol + col] = color;
                }
            }
        }
    }

    /**
     * Deletes the current position of the current piece, used to reprint pieces that move
     */
    public static void erasePiece(){
        int [][] shape = currentPiece.getShape();
        int col = currentPiece.getCol();
        int row = currentPiece.getRow();
        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                if(shape[i][j] == 1){
                    grid[row + i][col + j] = Color.darkGray;
                }
            }
        }
    }


    /**
     * Automatically moves pieces down per 1 row per second, like Tetris
     * @param panel
     */
    public static void autoDrop(GamePanel panel){
        java.util.Timer timer = new java.util.Timer();
        java.util.TimerTask task = new java.util.TimerTask(){
            @Override
            public void run(){
                erasePiece();
                int col = currentPiece.getCol();
                int row = currentPiece.getRow();
                System.out.println("Current row: " + row + "current col: " + col);
                erasePiece();
                if(canMoveDown() == true) {
                    currentPiece.setRow(row + 1);
                    placePiece(currentPiece);
                }else{
                    placePiece(currentPiece);
                    clearFullLines();

                    if (checkGameOver(currentPiece)) {
                        panel.repaint();
                        placePiece(currentPiece);
                        JOptionPane.showMessageDialog(null, "Game Over!\n Total line cleared: " + totalLinesCleared, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                    currentPiece = Tetromino.randomShape();
                    placePiece(currentPiece);
                }
                placePiece(currentPiece);
                printGrid();
                panel.repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 0,1000);
    }

    /**
     * Method to move the current piece down 1 row
     */
    public static void moveDown(){
        int row = currentPiece.getRow();
        erasePiece();
        if(canMoveDown() == true){
            currentPiece.setRow((row + 1));
            placePiece(currentPiece);
            clearFullLines();
        }else{
            placePiece(currentPiece);
            clearFullLines();
            currentPiece = Tetromino.randomShape();
            placePiece(currentPiece);
        }
        placePiece(currentPiece);
        clearFullLines();
        printGrid();
    }

    /**
     * Checks bounds to see if the current piece is able to move down a row
     * @return
     */
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

                    if(grid[newRow][newCol] != Color.darkGray){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Moves the current piece to the right 1 column
     */
    public static void moveRight(){
        int col = currentPiece.getCol();
        erasePiece();
        if(canMoveRight() == true) {
            currentPiece.setCol(col + 1);
            placePiece(currentPiece);
            clearFullLines();
        }
    }

    /**
     * Checks bounds to see if the current piece is able to move to the right
     * @return
     */
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

                    if(grid[newRow][newCol] != Color.darkGray){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Moves the current piece to the left 1 column
     */
    public static void moveLeft(){
        int [][] shape = currentPiece.getShape();
        int col = currentPiece.getCol();
        erasePiece();
        if(canMoveLeft() == true){
            currentPiece.setCol(col - 1);
            placePiece(currentPiece);
            clearFullLines();
        }

    }

    /**
     * Checks bounds to see if the current piece is able to move left
     * @return
     */
    public static boolean canMoveLeft(){
        int [][] shape = currentPiece.getShape();
        int col = currentPiece.getCol();
        int row = currentPiece.getRow();

        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < shape[i].length; j++){
                if(shape[i][j] == 1){
                    int newRow = row + i;
                    int newCol = col + j - 1;
                    if(newCol < 0 || grid[newRow][newCol] != Color.darkGray){
                        return false;
                    }

                }
            }
        }
        return true;
    }

    /**
     * Rotates the current piece clockwise, then replaces it on the board
     * @param panel
     */
    public static void rotatePiece(GamePanel panel) {
        erasePiece();
        if (currentPiece.canRotate(grid)) {
            currentPiece.rotate();
        }
        placePiece(currentPiece);
        clearFullLines();
        panel.repaint();
    }


    /**
     * Brings the current piece to the lowest possible position
     * @param panel
     */
    public static void hardDrop(GamePanel panel){
        erasePiece();
        while(canMoveDown()) {
            currentPiece.moveDown();
        }
        placePiece(currentPiece);
        clearFullLines();
        currentPiece = Tetromino.randomShape();
        placePiece(currentPiece);
        printGrid();
        panel.repaint();
        }

    /**
     * Checks to see if the game should be ended if certain conditions are met
     * @param piece
     * @return
     */
    public static boolean checkGameOver(Tetromino piece){
            int [][] shape = piece.getShape();
            int row = piece.getRow();

            for(int i = 0; i < shape.length; i++){
                for(int j = 0; j < shape[i].length; j++){
                    if(shape[i][j] == 1){
                        int gridRow = row + i;
                        if(gridRow == 0){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    static int totalLinesCleared = 0;

    /**
     * If a row is completely full, the line is cleared
     */
    public static void clearFullLines() {
        int count = 0;
            for (int row = 0; row < grid.length; row++) {
                boolean fullLine = true;
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == Color.darkGray) {  // empty cells are darkGray
                        fullLine = false;
                        break;
                    }
                }
                if (fullLine) {
                    clearLine(row);
                    count++;
                }
            }
            totalLinesCleared += count;
        }

    /**
     * Method that actually clears the line and move all other rows down 1
     * @param line
     */
    public static void clearLine(int line) {
            for (int row = line; row > 0; row--) {
                System.arraycopy(grid[row - 1], 0, grid[row], 0, grid[row].length);
            }
            for (int col = 0; col < grid[0].length; col++) {
                grid[0][col] = Color.darkGray;
            }
        }

}
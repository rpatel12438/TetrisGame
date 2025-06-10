public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Tetris!");
        startGame();

    }

    public static void startGame(){
        System.out.println("Game started.");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Game is running...");
        }
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

}
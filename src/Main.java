public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Tetris!");
        startGame();
        Tetromino piece = Tetromino.randomShape();
        placePiece(piece);
        System.out.println();
        printGrid();
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


}
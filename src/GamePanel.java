import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private char[][] grid;

    public GamePanel(char[][] grid){
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int cellSize = 30;
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col] == '#'){
                    g.setColor(Color.blue);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }else{
                    g.setColor(Color.lightGray);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
                g.setColor(Color.black);
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public Dimension getPreferredSize(){
        int cellSize = 30;
        int width = grid[0].length * cellSize;
        int height = grid.length * cellSize;
        return new Dimension(width, height);
    }
}

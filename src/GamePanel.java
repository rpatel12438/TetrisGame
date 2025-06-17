import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    private char[][] grid;

    public GamePanel(char[][] grid){
        this.grid = grid;
        setFocusable(true);
        requestFocusInWindow();
        keyBindings();
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

    private void keyBindings(){
        InputMap input = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap action = getActionMap();
        input.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        action.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.moveRight();
                repaint();
            }
        });

        input.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        action.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.moveLeft();
                repaint();
            }
        });

        input.put(KeyStroke.getKeyStroke("SPACE"), "hardDrop");
        action.put("hardDrop", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.hardDrop(GamePanel.this);
                repaint();
            }
        });

        input.put(KeyStroke.getKeyStroke("UP"), "rotate");
        action.put("rotate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.rotatePiece(GamePanel.this);
                repaint();
            }
        });
    }
}
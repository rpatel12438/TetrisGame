import javax.swing.*;


public class GameWindow extends JFrame{

    public GameWindow(char[][] grid){
        setTitle("Tetris");
        setSize(400,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        GamePanel panel = new GamePanel(grid);
        add(panel);
        pack();
        setVisible(true);
    }
}

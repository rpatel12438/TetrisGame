import javax.swing.*;


public class GameWindow extends JFrame{

    /**
     * Create the game window using JFrame
     * @param panel
     */
    public GameWindow(GamePanel panel){
        this.setTitle("Tetris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
}

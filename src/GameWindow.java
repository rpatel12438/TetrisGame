import javax.swing.*;


public class GameWindow extends JFrame{

    public GameWindow(GamePanel panel){
        this.setTitle("Tetris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
}

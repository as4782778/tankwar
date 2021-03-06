package Game;

import com.sun.javafx.scene.traversal.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;
    Boolean stop;
    GameClient(){
        this.setPreferredSize(new Dimension(800,600));
    }
    public GameClient(int screenWidth,int screenHeight){
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        init();

       new Thread(new Runnable() {
           public void run() {
               while (!stop){
                   repaint();
                   try{
                       Thread.sleep(50);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       }).start();
    }
    public void init(){
        playerTank = new Tank(400,100, Direction.DOWN);
    }
        @Override
    public void paintComponent(Graphics g){
        g.drawImage(playerTank.getImage(), playerTank.getX(), playerTank.getY(), null);

        }

    public void keypressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                break;
        }
        playerTank.move();
    }
}

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_A:
                    if(!GamePanel.course.equals("Right")){
                        GamePanel.course = "Left";
                    }
                    break;
                case KeyEvent.VK_D:
                    if(!GamePanel.course.equals("Left")){
                        GamePanel.course = "Right";
                    }
                    break;
                case KeyEvent.VK_S:
                    if(!GamePanel.course.equals("Down")){
                        GamePanel.course = "Up";
                    }
                    break;
                case KeyEvent.VK_W:
                    if(!GamePanel.course.equals("Up")){
                        GamePanel.course = "Down";
                    }
                    break;
        }
    }
}
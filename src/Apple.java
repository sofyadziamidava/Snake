import java.util.Random;

public class Apple {

    int xpos;
    int ypos;
    int width;
    int boxSize;
    Random random;

    public Apple(int width, int boxSize) {
        this.random = new Random();
        this.width = width;
        this. boxSize = boxSize;
        setNewPos();
    }

    public int getXpos() {
        return xpos;
    }

    public void setNewPos(){
        this.xpos = random.nextInt(width / boxSize) * boxSize;
        this.ypos = random.nextInt(width / boxSize) * boxSize;
    }

    public int getYpos() {
        return ypos;
    }

}


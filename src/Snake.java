public class Snake {

    int size;
    int sizePerPart;
    int speed;
    final int[] x;
    final int[] y;


    public Snake(int initialSize, int sizePerPart, int speed, int gameUnits) {
        this.size = initialSize;
        this.sizePerPart = sizePerPart;
        this.speed = speed;
        this.x = new int[gameUnits];
        this.y = new int[gameUnits];
    }

    public int getSize() {
        return size;
    }

    public void increaseSize(int size) {
        this.size += size;
    }

    public int getSpeed() {
        return speed;
    }

    public void increaseSpeed() {
        this.speed -= 50;
    }

    public int getXPosHead(){
        return x[0];
    }

    public int getYPosHead(){
        return y[0];
    }

    public int getXPosBodyPart(int i){
        return x[i];
    }

    public int getYPosBodyPart(int i){
        return y[i];
    }

    public void moveSnake(String course){
        for (int i = this.getSize(); i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (course) {
            case "Right" -> x[0] = x[0] + sizePerPart;
            case "Left" -> x[0] = x[0] - sizePerPart;
            case "Up" -> y[0] = y[0] - sizePerPart;
            case "Down" -> y[0] = y[0] + sizePerPart;
        }
    }
}

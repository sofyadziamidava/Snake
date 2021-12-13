public class Snake {

    int size;
    int sizePerPart;
    int speed;


    public Snake(int initialSize, int sizePerPart, int speed) {
        this.size = initialSize;
        this.sizePerPart = sizePerPart;
        this.speed = speed;
    }

    public int getSize() {
        return size;
    }

    public void increaseSize(int size) {
        this.size += size;
    }

    public int getSizePerPart() {
        return sizePerPart;
    }

    public void setSizePerPart(int sizePerPart) {
        this.sizePerPart = sizePerPart;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

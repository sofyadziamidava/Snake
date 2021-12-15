import javax.swing.*;

public class Game {

    private static Game game_instance = null;
    Snake snake;
    Apple apple;
    int score;
    int width;
    int height;

    private Game(Snake snake, Apple apple, int width, int height){

        this.snake = snake;
        this.apple = apple;
        this.score = 0;
        this.width = width;
        this.height = height;
    }

    public int getSnakeSize(){
        return snake.getSize();
    }

    public int getSpeed(){
        return snake.getSpeed();
    }

    public int getScore(){
        return score;
    }

    public void increaseScore(){
        this.score ++;
    }

    public void moveSnake(String course){
        snake.moveSnake(course);
  }

    public void eatApple(){
        if(snake.getXPosHead() == apple.getXpos() && snake.getYPosHead() == apple.getYpos()){
            snake.increaseSize(1);
            this.increaseScore();
            apple.setNewPos();
            snake.increaseSpeed();
            GamePanel.timer.setDelay(snake.getSpeed());

        }
    }

    public boolean snakeInjured(){
        for (int i = this.getSnakeSize(); i > 0 ; i--) {
            if((snake.getXPosHead() == snake.getXPosBodyPart(i)) &&
                    (snake.getYPosHead() == snake.getYPosBodyPart(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean wallCollision(){
        return snake.getXPosHead() < 0 || snake.getXPosHead() > width ||
                snake.getYPosHead() < 0 || snake.getYPosHead() > height;

       /*
        snake.getXPosHead() < 0 -> ormen går in i vänstra väggen
        snake.getXPosHead() > width -> ormen går in i högra väggen
        snake.getYPosHead() < 0 -> ormen går in i översta väggen
        snake.getYPosHead() > height -> ormen går in i nedersta väggen
        */
    }

    public static Game getInstance(Snake snake, Apple apple, int width, int height)
    {
        if (game_instance == null)
            game_instance = new Game(snake, apple, width, height);
        return game_instance;
    }
}

public class Game {

    Snake snake;
    Apple apple;
    int score;

    public Game(Snake snake, Apple apple){

        this.snake = snake;
        this.apple = apple;
        this.score = 0;

    }

    public Snake getSnake(){
        return snake;
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


    public void eatSnack(){
        if(snake.getXPosHead() == apple.getXpos() &&
                snake.getYPosHead() == apple.getYpos()){
            snake.increaseSize(1);
            this.increaseScore();
            apple.setNewPos();
        }
    }
}

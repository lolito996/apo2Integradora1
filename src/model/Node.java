package model;

public class Node {

    private Node next;
    private Node previous;
    private int id;
    private Snakes snake;
    private Ladders ladders;


    public Node( int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public model.Node getNext() {
        return next;
    }

    public void setNext(model.Node next) {
        this.next = next;
    }

    public model.Node getPrevious() {
        return previous;
    }

    public void setPrevious(model.Node previous) {
        this.previous = previous;
    }
    public Snakes getSnake(){
        return snake;
    }
    public void setSnake(Snakes snake){
        this.snake=snake;
    }
    public Ladders getLadder(){
        return ladders;
    }
    public void setLadder(Ladders ladders){
        this.ladders=ladders;
    }


   
}
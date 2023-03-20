package  model;
public class Player  {
    private String name ;
    private int score =0;
    private int pos ;
    private Player next;
    private Player previous;

       
    public Player(String name){
        this.name=name;
        this.pos=1;
    }
    public int getPos(){
        return this.pos;
    }
    public void setPos(int pos){
        this.pos=pos;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score=score;
    }
    public Player getNext(){
        return next;
    }
    public void  setNext(Player next){
        this.next=next;
    }
    public Player getPrevious(){
        return this.previous;
    }
    public void setPrevious(Player previous){
        this.previous=previous;
    }



}
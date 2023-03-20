package model;

public class Ladders extends Node {
    private String display;
    private Node connect;
    private boolean exist=false;

    public Ladders(int id) {
        super(id);
        this.display = " L ";
    }

    public Node getConnect() {
        return this.connect;
    }
    public boolean getExist(){
        return exist;
    }
    public void setExist(boolean exist){
        this.exist=exist;
    }

    public void setConnect(Node connect){
        this.connect = connect;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    
}

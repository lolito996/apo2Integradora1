package model;

public class Snakes extends Node {
    private String display;
    private Node connect;
    private boolean connected;
    private boolean exist;

    public Snakes(int id) {
        super(id);
        this.display = " S ";
        this.connected = false;
    }
    public boolean getExist(){
        return exist;
    }
    public void setExist(boolean exist){
        this.exist=exist;
    }

    public Node getConnect() {
        return connect;
    }

    public void setConnect(Node connect) {
        this.connect = connect;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

}

package ui;
import java.util.Scanner;
import model.*;
public class Main {


    private Scanner reader ;
    private GameController controller;
    public Main(){
        this.reader = new Scanner(System.in);
        this.controller=new GameController();
        
      }
    public static void main(String[] args) {

        Main main = new Main();
       
        int option = -1;
        do{
            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);
    }
    public int getOptionShowMenu(){
        int option = 0;
        System.out.println(printMenu());
        option = reader.nextInt();
        return option;
    }

    public String printMenu(){
        return
                "\n" +
                        "<< --------------------------------------------------------------------- >>\n" +
                        "<<                      jugo de las serpientes y escaleras                \n"+
                        "<< --------------------------------------------------------------------- >>\n"+
                        "1. Jugar\n"+
                        "0. Exit";
    }
    public String nextMenu(){
        return 
        "<< --------------------------------------------------------------------- >>\n" +
        "<<                    Es tu turno jugador "+controller.turnPlayer()+ "\n"+
        "<< --------------------------------------------------------------------- >>\n"+
        "1. tirar el dado\n"+
        "2. ver escaleras y serpientes ";
    }
    
    public void selectOption(int option){
        int option2;
        switch(option){
            case 1:
                controller.lanzarDado();
                if(controller.finishGame()==false){
                    System.out.println(nextMenu());
                    option2=reader.nextInt();
                    selectOption(option2);
                }else{
                    getOptionShowMenu();
                    
                }
            break;
            case 2:
                System.out.println(controller.printSnakesAndLadders());
                System.out.println(nextMenu());
                option2=reader.nextInt();
                selectOption(option2);

            break;
            default: 
                System.out.println("Su eleccion ha sido incorrecta");
                System.out.println(nextMenu());
                option2=reader.nextInt();
                selectOption(option2);
        }
    }
    public void executeOption(int option){

        switch(option){
            
            case 1:

                System.out.println("Cuantas filas tiene el tablero");
                int rows =reader.nextInt();
                System.out.println("Cuantas columnas tiene el tablero ");
                int columns=reader.nextInt();
                System.out.println("Cuantas serpientes tiene el tablero");
                int snakers=reader.nextInt();
                System.out.println("Cuantas escaleras  tiene el tablero");
                int stairs=reader.nextInt();
                Boolean pass=controller.validateValue(rows, columns,snakers,stairs);
                if (pass==true){
                    controller.createBoard(rows, columns,snakers,stairs);
                    controller.createPlayer();
                    controller.initTimeScore();
                    controller.addSnakesAndLadders();
                    System.out.println(nextMenu());
                    option=reader.nextInt();
                    selectOption(option);
                    

                }else{
                    System.out.print("No se puede crear tablero con valores 0 o negativos, el tablero debe ser almenos de 3 x 3 , no se admite tener mas escaleras o serpientes que casilas");
                }
                break;

            case 0:
                System.out.print("Exit program");
                break;

            default :
                System.out.println("Su eleccion no es correcta");
            break;
        }


    }
   


}

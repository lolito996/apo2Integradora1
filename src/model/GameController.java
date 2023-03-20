package model;

import java.util.Random;
import java.lang.Math;

public class GameController {
    private int rows;
    private int id;
    private Node tail;
    private Node head;
    private Player tailPlayer;
    private Player headPlayer;
    private int columns;
    private int snakes;
    private int ladders;
    private Player turn;
    private Score score;
    private boolean finish=false;

    public GameController() {
    }

    public void initTimeScore() {
        score = new Score(0.0);
        score.startTimer();
    }

    public void validateTurnPlayer(int id) {
        Node current = getNodeById(id);
        if (current.getLadder()!=null) {
            Ladders ladder = current.getLadder();
            Node conect = ladder.getConnect();
            if(conect!=null){
                
                if(conect.getId()!=current.getId()){
                    turn.setPos(conect.getId());
                }else{

                }
            }else{

            }

        } else if (current.getSnake()!=null) {
                Snakes snake = current.getSnake();
                Node conect = snake.getConnect();
                if(conect!= null){
                    if (conect.getId() != current.getId()) {
                        turn.setPos(conect.getId());
                    } else {

                    }
                }else{

                }
        } else {

        }
    }

    public void closeTimeScore() {
        score.stopTimer();
        double t = score.calculateScore();
        System.out.println("Felicidades "+ this.turn.getName()+ " llegaste al final del tablero tu score es " + t);
        finish=true;
    }
    public boolean finishGame(){
        return finishGame(finish);
    }
    public boolean finishGame(boolean n){
        boolean finis=n;
        return finis;
    }

    public void lanzarDado(){
        System.out.println(printPlayers());
        Random random = new Random();
        int resultado = random.nextInt(6) + 1;
        if (turn.getPos() + resultado < tail.getId()) {
            turn.setPos(turn.getPos() + resultado);
            System.out.println(" jugador " + turn.getName() + " sacaste el numero " + resultado);
            validateTurnPlayer(turn.getPos());
            System.out.println(printPlayers());
            this.turn=turn.getNext();
        } else if(turn.getPos()+resultado==tail.getId()){
            closeTimeScore();
        }
    }


    public void addPlayerNode(Player current) {
        addPlayerNode(current);
    }

    public void addSnakesAndLadders() {

        addSnakesAndLadders(this.ladders, this.snakes, 0, 0);
    }

    public Node getNodeById(int id) {
        return getNodeById(head, id);
    }

    private Node getNodeById(Node current, int id) {
        if (current == null) {
            return null;
        } else if (current.getId() == id) {
            return current;
        } else if (current.getNext() == null) {
            return null;
        } else {
            return getNodeById(current.getNext(), id);
        }
    }

    public int createPosForSnakerandLadder() {
        int total = this.rows * this.columns;
        Random random = new Random();
        int pos = random.nextInt((total - 1) + 2);
        return pos;
    }

    private void addSnakesAndLadders(int ladders, int snakes, int countLadder, int countSnakes) {
        if (countSnakes >= snakes && countLadder >= ladders) {
            return; // se han creado suficientes serpientes y escaleras
        }
        if (countSnakes < snakes) {
            int posSnake = createPosForSnakerandLadder();
            int posConectedSnake = createPosForSnakerandLadder();
            if (posSnake > posConectedSnake && posSnake != 1 && posConectedSnake != 1 && posSnake != rows * columns&& posConectedSnake != rows * columns) {
                Node nodeSnake = getNodeById(posSnake);
                Node conectionSanke = getNodeById(posConectedSnake);
                if (nodeSnake.getLadder() == null && conectionSanke.getLadder() == null && nodeSnake.getSnake() == null && conectionSanke.getSnake() == null) {
                    Snakes snakeI = new Snakes(nodeSnake.getId());
                    nodeSnake.setSnake(snakeI);
                    snakeI.setExist(true);
                    conectionSanke.setSnake(snakeI);
                    snakeI.setConnect(conectionSanke);
                    countSnakes++;
                }
            }
            addSnakesAndLadders(ladders, snakes, countLadder, countSnakes);
        }
        else if (countLadder < ladders) {
            int pos = createPosForSnakerandLadder();
            int posConected = createPosForSnakerandLadder();
            if (pos < posConected && pos != 1 && posConected != 1 && pos != (rows * columns) && posConected != (rows * columns)) {
                Node node = getNodeById(pos);
                Node conection = getNodeById(posConected);
                if (node.getSnake() == null && conection.getSnake() == null && node.getLadder() == null && conection.getLadder() == null) {
                    Ladders ladI = new Ladders(node.getId());
                    conection.setLadder(ladI);
                    node.setLadder(ladI);
                    ladI.setConnect(conection);
                    ladI.setExist(true);
                    countLadder++;
                    addSnakesAndLadders(ladders, snakes, countLadder, countSnakes);
                } else {
                    addSnakesAndLadders(ladders, snakes, countLadder, countSnakes);
                }
            } else {
                addSnakesAndLadders(ladders, snakes, countLadder, countSnakes);
            }
        }
    }

    public void addPlayer(Player player) {
        if (headPlayer == null) {
            headPlayer = player;
            headPlayer.setNext(headPlayer);
            headPlayer.setPrevious(headPlayer);
            turn = headPlayer;
        } else {
            Player tailPlayer = headPlayer.getPrevious();
            player.setNext(headPlayer);
            headPlayer.setPrevious(player);
            tailPlayer.setNext(player);
            player.setPrevious(tailPlayer);
        }
    }

    public void createPlayer() {

        Player playerA = new Player("*");
        addPlayer(playerA);
        Player playerB = new Player("$");
        addPlayer(playerB);
        Player playerC = new Player("%");
        addPlayer(playerC);
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getColumns() {
        return this.columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getSnakes() {
        return snakes;
    }

    public void setSnakes(int snakes) {
        this.snakes = snakes;
    }

    public int getLadders() {
        return ladders;
    }

    public void setLadders(int ladders) {
        this.ladders = ladders;
    }

    public Player getTurn() {
        return this.turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public void createBoard(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        createBoard(rows, columns, (rows * columns), 1);
    }

    public boolean validateValue(int rows, int columns, int snakers, int stairs) {
        int totalSnakersAndLadder = snakers + ladders;
        boolean pass = true;
        if (rows < 3 || columns < 3 || snakers >= Math.floor(((rows * columns) / 2))
                || stairs >= Math.floor((rows * columns) / 2)
                        && totalSnakersAndLadder > Math.floor(((rows * columns) / 3))) {
            pass = false;
        }
        return pass;

    }

    public String turnPlayer() {
        return this.turn.getName();
    }

    public void createBoard(int rows, int columns, int snakers, int stairs) {
        this.snakes = snakers;
        this.ladders = stairs;
        this.rows = rows;
        this.columns = columns;
        String msj = "";
        createBoard(rows, columns, (rows * columns), 0, msj);
    }

    private void createBoard(int rows, int columns, int total, int i, String msj) {
        // Es menor o igual se crean
        if (i < total) {
            Node node = new Node(i);
            if (this.head == null) {
                this.head = node;
                this.tail = node;
                this.tail.setNext(head);
                this.head.setPrevious(tail);
                node.setId(1);
                createBoard(rows, columns, total, (i + 1), msj);

            } else {
                this.tail.setNext(node);
                node.setPrevious(this.tail);
                this.tail = node;
                this.tail.setNext(this.head);
                this.head.setPrevious(this.tail);
                node.setId(node.getPrevious().getId() + 1);
                createBoard(rows, columns, total, (i + 1), msj);
            }
        } else {
            // Es mayor ya no se crea
        }
    }

    public Node search(int value) {
		return search(this.head, value);
	}
    public Node search(Node current, int slotNumber) {
		if (this.head == null) {
			return null;
		} else if (current != null && current.getId() == slotNumber) {
			return current;
		} else if (current != null && current.equals(this.tail)) {
			return null;
		}
		return search(current.getNext(), slotNumber);
	}


    public String printSnakesAndLadders() {
		if (this.head == null)
			return "[ ]";
		int end = this.tail.getId();
		return print(this.tail, this.rows, this.tail.getId(), end - columns, true);
    }


    private String printNode(Node current) {
		String msg = current.getId() + " ";
        Player player1=headPlayer;
        Player player2=headPlayer.getNext();
        Player player3=headPlayer.getNext().getNext();
		if (player1.getPos()==(current.getId())) {
			msg += player1.getName();
		}
		if (player2.getPos()==(current.getId())) {
			msg += player2.getName();
		}
		if (player3.getPos()==(current.getId())) {
			msg += player3.getName();
		}
		if (!msg.equals(current.getId() + " ")) {
			msg += " ";
		}
		return msg;
	}
    public String printPlayers() {
		if (this.head == null)
			return "[ ]";
		int end = this.tail.getId();
		return print(this.tail, this.rows, this.tail.getId(),end - this.columns, false);
	}


    private String printReverseNode(Node current, int end, int i) {
		if (end - 1 == i)
			return "[ " + printNode(current) + "]";
		return printReverseNode(current.getPrevious(), --end, i) + " [ " + printNode(current) + "]";


	}


    private String printNodes(Node current, int fromIndex, int toIndex) {
		if (fromIndex - 1 == toIndex)
			return "[ " + printNode(current) + "]";
		return "[ " + printNode(current) + "] " + printNodes(current.getPrevious(), --fromIndex, toIndex);
	}


    private String print(Node  current, int row, int end, int i, boolean snakesAndLadders) {
		if (row == 0)
			return "";
		    Node nextNode = search(current.getId() - columns);
		if (row % 2 != 0) {
			if (snakesAndLadders)
				return printReverseSAndL(current, end, i) + "\n"
						+ print(nextNode, --row, end - columns, i - columns, snakesAndLadders);
			else
				return printReverseNode(current, end, i) + "\n"
						+ print(nextNode, --row, end - columns, i - columns, snakesAndLadders);
		} else {
			if (snakesAndLadders)
				return  printSAndL(current, end, i) + "\n"
						+ print(nextNode, --row, end - columns, i - columns, snakesAndLadders);
			else
				return printNodes(current, end, i) + "\n"
						+ print(nextNode, --row, end - columns, i - columns, snakesAndLadders);
            
		}
    }


    private String printSAndL(Node current, int end, int i) {
		String msg = "  ";
		if (current.getSnake() != null){
			msg = current.getSnake().getDisplay().substring(0, current.getSnake().getDisplay().length() - 1);
        }
		else if (current.getLadder() != null){
			msg = current.getLadder().getDisplay().substring(0, current.getLadder().getDisplay().length() - 1);
        }
		if (msg.equals("  "))
			msg += " ";
		if (end - 1 == i)
			return "[ " + msg + "]";
		else {
			return "[ " + msg + "] "
					+ printSAndL(current.getPrevious(), --end, i);
		}
    }
    private String printReverseSAndL(Node current, int end, int i) {
		String msg = "  ";
		if(current.getSnake()!=null){
		    if (current.getSnake().getDisplay() != null)
			    msg = current.getSnake().getDisplay().substring(0, current.getSnake().getDisplay().length() - 1);
            }
		    else if (current.getLadder()!= null)
			   msg = current.getLadder().getDisplay().substring(0, current.getLadder().getDisplay().length() - 1);
		if (msg.equals("  "))
			msg += " ";
		if (end - 1 == i)
			return "[ " + msg + "]";
		else {
			return printReverseSAndL(current.getPrevious(), --end, i) + " [ "
				+ msg + "]";
		}
	}
}
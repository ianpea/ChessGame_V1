/**
* This class is responsible for the controller part of MVC design pattern
* This class contains the start method and controls the view and the models.
* @author Gan Jia Yong(1161304051), Tan Sheh Jing(1161304110), Pee Wu Jian(1161303933)
*/

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.nio.file.Paths;
import javafx.stage.FileChooser;

public class GameController extends Application{
    private Player redPlayer = new Player();  //Player 1,
    private Player bluePlayer = new Player(); //Player 2
    private Player currentPlayer = redPlayer;   //record the current player
    private ChessBoard board = ChessBoard.getBoard();  //the chess board
    private GameView view;    //a instance of GameView to update the players' view
    private Button sourceSquare;  //the first button the current player click during his turn to move piece
    private Button destSquare;    //the second button the current player click during his turn to move piece
    private int sourceX;   //used to record the sourceSquare x-coordinate
    private int sourceY;   //used to record the sourceSquare y-coordinate
    private int turn;  //counter to keep track of the number of turns 
    
    /**
    * Author: Gan Jia Yong
    * Default constructor
    */
    public GameController()
    {}

    /**
    * Author: Tan Sheh Jing & Pee Wu Jian & Gan Jia Yong
    * This is the main method of the whole program
    */
    public void start(Stage stage) throws IOException
    {
        //initialize the GUI view
        view = new GameView(board.getWidth(), board.getHeight(), board);

        //set the color of player: 1 for red, 2 for blue
        redPlayer.setColor(1);
        bluePlayer.setColor(2);

        //disable end game and save game button before start or load game
        view.getEndGame().setDisable(true);
        view.getSaveGame().setDisable(true);

        //attach event handler to start game button
        view.getStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGame();

                //enable the end game and save game button
                view.getEndGame().setDisable(false);
                view.getSaveGame().setDisable(false);

                //disable start game button
                view.getStartGame().setDisable(true);
                view.getLoadGame().setDisable(true);
            }
        });

        //attach event handler to end game button
        view.getEndGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                endGame();
            }
        });

        //attach event handler to load game button
        view.getLoadGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    loadGame();

                    //enable the end game and save game button
                    view.getEndGame().setDisable(false);
                    view.getSaveGame().setDisable(false);

                    //disable start game button
                    view.getStartGame().setDisable(true);
                    view.getLoadGame().setDisable(true);
                }
                catch(Exception e){}
            }
        });

        //attach event handler to save game button
        view.getSaveGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    saveGame();
                }
                catch(Exception e){}
            }
        });

        //attach event handler to view rules button
        view.getViewRules().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showRules();
            }
        });
    }

    /**
    * Author: Gan Jia Yong
    * This method is used to get the current player
    */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
    * Author: Gan Jia Yong
    * This method is used to get the board
    */
    public ChessBoard getBoard()
    {
        return board;
    }

    /**
    * Author: Tan Sheh Jing
    * This method is used to get both the players' name
    */
    public void getPlayerName()
    {
        //create pop up window for user to enter name
        Dialog popUpDialog = new Dialog();
        Label p1 = new Label ("Player 1 Name: ");
        Label p2 = new Label ("Player 2 Name: ");

        TextField redPlayerName = new TextField();
        TextField bluePlayerName = new TextField();

        //create gridPane to align all label and textField inside
        GridPane grid = new GridPane();
        grid.add(p1, 1,1);
        grid.add(redPlayerName, 2,1);
        grid.add(p2, 1,2);
        grid.add(bluePlayerName, 2,2);

        ButtonType buttonTypeOk = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);

        //set window title
        popUpDialog.setTitle("Start A New Game!");
        popUpDialog.getDialogPane().setContent(grid);
        popUpDialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        popUpDialog.showAndWait();

        //assign the input from textfield into a string variable
        String player1 = redPlayerName.getText();
        String player2 = bluePlayerName.getText();

        //set name for both player 1 and player 2
        redPlayer.setName(player1);
        bluePlayer.setName(player2);
    }

    /**
    * Author: Tan Sheh Jing & Pee Wu Jian & Gan Jia Yong
    * This method is used to flip the view of the chess board when it is another player's turn
    */
    public void flipView()
    {
        Piece[][] newPiecesOnBoard = new Piece[board.getWidth()][board.getHeight()];

        //assign each piece to a corresponding opposite position
        for(int j = 0; j < 6; j++)
        {
            for(int i = 0; i < 7; i++)
            {
                if(board.isEmpty(i, j))
                    continue;
                else 
                {
                    newPiecesOnBoard[board.getWidth() - i - 1][board.getHeight() - j - 1] = board.getPiece(i, j);
                }
            }
        }

        //assign the newPiecesOnBoard to piecesOnBoard
        board.setPiecesOnBoard(newPiecesOnBoard);

        //update the GUI view
        view.assignPieceIcon();
    }

    /**
    * Author: Tan Sheh Jing
    * This method is used to start the game
    */
    public void startGame()
    {
        getPlayerName();
        attachHandlerToButton();

        currentPlayer = redPlayer;
        //update current player label
        view.setCurrentPlayerLabel(currentPlayer);
        
        turn = 0;
    }

    /**
    * Author: Tan Sheh Jing & Gan Jia Yong
    * This method is used to end the game in the middle of the game
    */
    public void endGame()
    {
        //display an alert popup window to confirm the quit
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert!");
        alert.setContentText("Are you sure want to quit?");

        //disable window default close button
        alert.getDialogPane().getScene().getWindow().setOnCloseRequest(e -> e.consume());

        Optional<ButtonType> result = alert.showAndWait();
        //if confirm to quit, the loser will be the current player who request to end game
        //a message window will be popup to show who is the loser
        if (result.get() == ButtonType.OK){
            Dialog displayMsg = new Dialog();
            Label msg = new Label(getCurrentPlayer().getName() + " loses.");

            GridPane gPane = new GridPane();
            gPane.add(msg, 1, 1);
            ButtonType buttonTypeOkay = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);

            displayMsg.setTitle("End Game!");
            displayMsg.getDialogPane().setContent(gPane);
            displayMsg.getDialogPane().getButtonTypes().add(buttonTypeOkay);

            displayMsg.showAndWait();

            startAgain();
            board.clearBoard();
            board.initialize();
            view.assignPieceIcon();
        }
        else  //if player cancel the request, do nothing, back to main window
        {}
    }

    /**
    * Author: Pee Wu Jian & Tan Sheh Jing
    * This method is used to load game from a previously saved game
    */
    public void loadGame() throws IOException
    {
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        Scanner input = new Scanner(selectedFile);

        //load the players' name
        redPlayer.setName(input.nextLine());
        bluePlayer.setName(input.nextLine());
		
		//check and set the current player
        if(input.nextLine().equals(redPlayer.getName()))
        	currentPlayer = redPlayer;
        else
        	currentPlayer = bluePlayer;

        //load the turn value
        turn = Integer.parseInt(input.nextLine());

        String s = "";

        //load the pieces to their positions
        for(int j = 0; j < 6; j++)
        {
            for(int i = 0; i < 7; i++)
            {
                s = input.nextLine();
                if(s.equals("x"))
                {
                    board.setPiece(i, j, null);
                }
                else
                {
                    int typeID = Integer.parseInt(s);
                    int color = Integer.parseInt(input.nextLine());
                    board.setPiece(i,j, PiecePrototype.getPiece(typeID));
                    board.getPiece(i,j).setColor(color);
                }
            }
        }

        //update the GUI view
        view.assignPieceIcon();
        view.setCurrentPlayerLabel(currentPlayer);
        attachHandlerToButton();
    }

    /**
    * Author: Pee Wu Jian & Gan Jia Yong
    * This method is used to save current game
    */
    public void saveGame() throws IOException
    {
        //ask the player to enter file name
        TextInputDialog saveDialog = new TextInputDialog();
        Label s1 = new Label("Enter the file name");
        TextField tf = new TextField();

        GridPane p1 = new GridPane();
        p1.add(s1,1,1);
        p1.add(tf,2,1);

        saveDialog.setTitle("Save game");
        saveDialog.getDialogPane().setContent(p1);
        Optional<String> result = saveDialog.showAndWait();

        //if player click ok with/without filename, save the data
        if(result.isPresent())
        {
        	String fileName = tf.getText();

        	//make sure the file name ended with .txt
        	if(fileName.length() <= 4)
        	{
        		fileName = fileName + ".txt";
        	}
        	else
        	{
        		if(fileName.charAt(fileName.length() - 4) == '.' &&
        			fileName.charAt(fileName.length() - 3) == 't' &&
        			fileName.charAt(fileName.length() - 2) == 'x' &&
        			fileName.charAt(fileName.length() - 1) == 't')
        		{
        			fileName = fileName;
        		}
        		else
        		{
        			fileName = fileName + ".txt";
        		}
        	}

        	File file = new File(fileName);

        	//save to text file
        	try(PrintWriter output = new PrintWriter(file);){
        		output.println(redPlayer.getName());
        		output.println(bluePlayer.getName());
        		output.println(currentPlayer.getName());
        		output.println(turn);

        		for(int j = 0; j < 6; j++)
        		{
        			for(int i = 0; i < 7; i++)
        			{
        				if(board.getPiece(i,j) == null)
        				{
        					output.println("x");
        				}
        				else
        				{
        					output.println(board.getPiece(i,j).getTypeID() + "\n" + board.getPiece(i,j).getColor());
        				}
        			}
        		}
        	}
        	catch(IOException e){System.out.println("Error.");}
        }
    }

    /**
    * Author: Gan Jia Yong
    * This method is used to show the game rules
    */
    public void showRules()
    {
        Label label1 = new Label("Rules: ");
        Label label2 = new Label("1. The Plus can move vertically or horizontally, up to 2 steps in any direction in a straight line.");
        Label label3 = new Label("2. The Triangle can move diagonally, both up and down, up to 2 steps in any direction in a straight line.");
        Label label4 = new Label("3. The Chevron must move in an L shape, exactly two steps then 1 step right or left. (This is the only piece that can jump over other pieces.)");
        Label label5 = new Label("4. The Sun can move only 1 step in any direction. The game ends when the sun is captured by the other side.");
        Label label6 = new Label("5. After 3 turns, a Plus will turn into a Triangle, a Triangle will turn into a Chevron, and a Chevron will turn into a Plus.");

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(label1, label2, label3, label4, label5, label6);
        vBox.setPadding(new Insets(20));

        Scene scene = new Scene(vBox, 800, 200);

        Stage stage = new Stage();
        stage.setTitle("Rules of The Game");
        stage.setScene(scene);
        stage.show();
    }

    /**
    * Author: Gan Jia Yong & Tan Sheh Jing & Pee Wu Jian
    * This method is used to attach the mouse event handler to all the button on the GUI chess board
    * This is an implicit game loop
    */
    public void attachHandlerToButton()
    {
        for(int j = 0; j < board.getHeight(); j++)
        {
            for(int i = 0; i < board.getWidth(); i++)
            {
                Button currentSquare = view.getASquare(i, j);

                //get x and y coordinate of the current square to be used in the event handling inner class
                final int I = i;
                final int J = j;

                //attach handler to handle mouse click event
                currentSquare.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent e)
                    {
                        //if sourceSquare is null(first click to select the piece to move), set sourceSquare first
                        if(sourceSquare == null)
                        {
                            sourceSquare = view.getASquare(I, J);
                            Piece current = board.getPiece(I, J);

                            //if clicked on empty square button, reset the sourceSquare to null
                            if(current == null)
                            {
                                sourceSquare = null;
                            }
                            else
                            {
                                //check whether it is opponent's piece or currentPlayer's piece
                                //if opponent's, reset sourceSquare to null and give warning
                                if(current.getColor() != currentPlayer.getColor())
                                {
                                    wrongPieceWarning();
                                    sourceSquare = null;
                                    current = null;
                                }
                                //correct piece, then show valid move coordinate in yellow, show opponent's pieces that can be eaten in green button
                                else 
                                {
                                    sourceX = I;
                                    sourceY = J;

                                    Piece[][] piecesOnBoard = board.getPiecesOnBoard();
                                    ArrayList<Coordinate> destinations = board.getPiece(I, J).getMove().generateValidPath(I,J,piecesOnBoard);

                                    for(Coordinate d : destinations)
                                    {
                                        if(board.getPiece(d.getX(), d.getY()) == null)
                                            view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: yellow;");
                                        else if(board.getPiece(d.getX(), d.getY()).getColor() != currentPlayer.getColor())
                                            view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: green;");
                                        else
                                            ;  //own piece, do nothing
                                    }
                                }
                            }
                        }
                        //if source square is not null, got previous click
                        else
                        {
                            destSquare = view.getASquare(I, J);

                            Piece[][] piecesOnBoard = board.getPiecesOnBoard();

                            //get a set of valid possible coordinate to move
                            ArrayList<Coordinate> destinations = board.getPiece(sourceX, sourceY).getMove().generateValidPath(sourceX,sourceY,piecesOnBoard);
                            
                            //check the current clicked button's coordinate is within the set of valid possible coordinate or not
                            boolean validPosition = piecesOnBoard[sourceX][sourceY].move(I, J, sourceX, sourceY, piecesOnBoard);

                            //if an invalid button is pressed, then clear the hightlight
                            if(!validPosition || destSquare == sourceSquare)
                            {
                                for(Coordinate d : destinations)
                                {
                                    if(board.getPiece(d.getX(), d.getY()) == null)
                                    {
                                        if((d.getX()+d.getY())%2 == 0)
                                            view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: black;");
                                        else
                                            view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: white;");
                                    }
                                }
                            }
                            else
                            {
                                //if the player clicks on his own piece, then clear the hightlight and issue an warning
                                if(piecesOnBoard[I][J] != null && board.getPiece(I, J).getColor() == currentPlayer.getColor())
                                {
                                        for(Coordinate d : destinations)
                                        {
                                            if(board.getPiece(d.getX(), d.getY()) == null)
                                            {
                                                if((d.getX()+d.getY())%2 == 0)
                                                    view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: black;");
                                                else
                                                    view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: white;");
                                            }
                                        }
                                        dontEatYourselfWarning();
                                }
                                else  //if a valid button is clicked, then move piece
                                {
                                    boolean win = false;

                                    //check if is the opponent's Sun be eaten, if yes then win is true
                                    if(!board.isEmpty(I, J) && piecesOnBoard[I][J].getTypeID() == 4)
                                        win = true;

                                    //move the piece to new position and may eat opponent's piece
                                    board.setPiece(I, J, piecesOnBoard[sourceX][sourceY]);

                                    //remove the piece from old position
                                    board.removePiece(sourceX, sourceY);

                                    //update the view
                                    view.assignPieceIcon();

                                    //clear the yellow and green colour
                                    for(Coordinate d : destinations)
                                    {
                                        if((d.getX() + d.getY()) % 2 == 0)
                                            view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: black;");
                                        else
                                            view.getASquare(d.getX(), d.getY()).setStyle("-fx-background-color: white;");
                                    }

                                    //if win then declare winner and reset all
                                    if(win)
                                    {
                                        winMessage();
                                        startAgain();
                                        board.clearBoard();
                                        board.initialize();
                                        view.assignPieceIcon();
                                    }
                                    else
                                    {
                                        //change current player
                                        if(currentPlayer.getColor() == 1)
                                            currentPlayer = bluePlayer;
                                        else
                                            currentPlayer = redPlayer;

                                        //update the GUI view for current player label
                                        view.setCurrentPlayerLabel(currentPlayer);
                                        turn++;

                                        //for every 3 turns, the pieces will transform accordingly except Sun
                                        if(turn % 3 == 0)
                                        {
                                            for(int j = 0; j < 6; j++)
                                            {
                                                for(int i = 0; i < 7; i++)
                                                {
                                                    if(!board.isEmpty(i,j) && piecesOnBoard[i][j].getTypeID() != 4)
                                                    {
                                                        if(piecesOnBoard[i][j].getColor() == 1)
                                                        {
                                                            piecesOnBoard[i][j] = piecesOnBoard[i][j].transform();
                                                            piecesOnBoard[i][j].setColor(1);
                                                        }
                                                        else
                                                        {
                                                            piecesOnBoard[i][j] = piecesOnBoard[i][j].transform();
                                                            piecesOnBoard[i][j].setColor(2);
                                                        }
                                                        //update view
                                                        view.assignPieceIcon();
                                                    }
                                                }
                                            }
                                        }
                                        flipView();
                                    }
                                }
                            }
                            //reset after a move is successful
                            sourceSquare = null;
                            sourceX = 100;
                            sourceY = 100;
                        }
                    }
                });
            }
        }
    }

    /**
    * Author: Tan Sheh Jing & Gan Jia Yong
    * This method is used to remind the players to move only their own piece during their turns
    */
    public void wrongPieceWarning()
    {
        //display an alert popup window to confirm the quit
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        if(currentPlayer.getColor() == 1)
        {
            alert.setContentText("It is red player's (player 1) turn!");
        }
        else
        {
            alert.setContentText("It is blue player's (player 2) turn!");
        }
        alert.showAndWait();
    }

    /**
    * Author: Tan Sheh Jing & Gan Jia Yong
    * This method is used to remind the player that he cannot move his own piece to the position where another piece of him is there
    */
    public void dontEatYourselfWarning()
    {
        //display an alert popup window to confirm the quit
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        
        alert.setContentText("You cannot eat your own piece!! ");
        alert.showAndWait();
    }

    /**
    * Author: Tan Sheh Jing & Gan Jia Yong
    * This method is used to show the message that declare the winner
    */
    public void winMessage()
    {
        //create pop up window for user to enter name
        Dialog<Player> popUpDialog = new Dialog<>();
        Label p1 = new Label (currentPlayer.getName() + " wins!");
        
        //create gridPane to align all label and textField inside
        GridPane grid = new GridPane();
        grid.add(p1, 1,1);

        ButtonType buttonTypeOk = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);

        popUpDialog.setTitle("Congratulation!");
        popUpDialog.getDialogPane().setContent(grid);
        popUpDialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        popUpDialog.showAndWait();
    }

    /**
    * Author: Tan Sheh Jing & Pee Wu Jian & Gan Jia Yong
    * This method is used to ask if the players want to start the game again
    * If yes, then restart the game
    * If no, then exit the program
    */
    public void startAgain()
    {
        //create pop up window for user to enter name
        Dialog<ButtonType> popUpDialog = new Dialog<>();
        Label p1 = new Label ("Do you want to start a new game or exit?");
        
        //create gridPane to align all label and textField inside
        GridPane grid = new GridPane();
        grid.add(p1, 1,1);

        ButtonType buttonTypeOk = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeExit = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

        popUpDialog.setTitle("New Game");
        popUpDialog.getDialogPane().setContent(grid);
        popUpDialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeExit);

        Optional<ButtonType> result = popUpDialog.showAndWait();

        //if want yes, then start again; else exit the program
        if(result.get() == buttonTypeOk)
            startGame();
        else
            System.exit(0);
    }
}

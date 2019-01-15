/**
* This class is responsible for the view part of MVC design pattern
* This class presents the view the players see.
* @author Gan Jia Yong(1161304051)
*/

import javafx.application.Application;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameView 
{
	private ChessBoard board;  //chess board
	private Button[][] square; //buttons on chess board that correspond to the coordinate of each square
	
	private Button startGame = new Button("Start Game");  //start game button
	private Button endGame = new Button("End Game");      //end game button
	private Button loadGame = new Button("Load Game");    //load game button
	private Button saveGame = new Button("Save Game");    //save game button
	private Button viewRules = new Button("View Rules");  //view rules button
	private Label currentPlayerLabel = new Label("Current Player: -");   //current player label

	/**
    * Author: Gan Jia Yong
    * Constructor
    * This constructor initialize the GUI of this game
    */
	public GameView(int column, int row, ChessBoard board)
	{
		this.board = board;
		square = new Button[column][row];

		//place the currentPlayerLabel at the right
		HBox playerLabel = new HBox();
		playerLabel.getChildren().add(currentPlayerLabel);
		playerLabel.setAlignment(Pos.CENTER_RIGHT);
		HBox.setHgrow(playerLabel, Priority.ALWAYS);

		//create a menu bar
		HBox menuBar = new HBox();
		menuBar.getChildren().addAll(startGame, endGame, loadGame, saveGame, viewRules, playerLabel);
		menuBar.setPadding(new Insets(10));

		BorderPane borderPane = new BorderPane();
		//place the menu bar at the top
		borderPane.setTop(menuBar);

		GridPane gridPane = new GridPane();

		//create and place the buttons as the GUI of the chess board
		for(int j = 0; j < 6; j++)
		{
			for(int i = 0; i < 7; i++)
			{
				square[i][j] = new Button();

				//fix the max and min size of button
				square[i][j].setMaxHeight(80);
				square[i][j].setMaxWidth(80);
				square[i][j].setMinHeight(80);
				square[i][j].setMinWidth(80);
				if((i+j)%2 == 0)
				{
					square[i][j].setStyle("-fx-background-color: #000;"); //black colour
				}
				else
				{
					square[i][j].setStyle("-fx-background-color: #FFF;"); //white colour
				}
				
				gridPane.add(square[i][j], i, j);
			}
		}
		gridPane.setPadding(new Insets(50));

		//make the chess board always at the center of window
		HBox gridPaneWrapper = new HBox();
		gridPaneWrapper.getChildren().add(gridPane);
		gridPaneWrapper.setAlignment(Pos.CENTER);
		HBox.setHgrow(gridPaneWrapper, Priority.ALWAYS);

		borderPane.setCenter(gridPaneWrapper);

		//assign the icon of the piece to the button where the piece located
		assignPieceIcon();

		Scene scene = new Scene(borderPane);

		Stage stage = new Stage();

		//set min and max size of the window
		stage.setMinWidth(680);
		stage.setMinHeight(660);
		stage.setScene(scene);
		stage.setTitle("Myrmidon Chess");
		stage.show();
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to assign piece icons to the buttons if the coordinate of a piece is on the button position
	*/
	public void assignPieceIcon()
	{
		Image bluePlus = new Image("images/bluePlus.png");
		Image blueTriangle = new Image("images/blueTriangle.png");
		Image blueChevron = new Image("images/blueChevron.png");
		Image blueSun = new Image("images/blueSun.png");

		Image redPlus = new Image("images/redPlus.png");
		Image redTriangle = new Image("images/redTriangle.png");
		Image redChevron = new Image("images/redChevron.png");
		Image redSun = new Image("images/redSun.png");

		Image nothing = new Image("images/nothing.png");

		for(int j = 0; j < 6; j++)
		{
			for(int i = 0; i < 7; i++)
			{
				//if no piece on the button, place a transparent image on the button
				if(board.getPiece(i, j) == null)
				{
					square[i][j].setGraphic(new ImageView(nothing));
				}
				else if(board.getPiece(i, j).getTypeID() == 1) //if a Plus on the button
				{
					if(board.getPiece(i, j).getColor() == 1) //red player
					{
						square[i][j].setGraphic(new ImageView(redPlus));
					}
					else
					{
						square[i][j].setGraphic(new ImageView(bluePlus));
					}
				}
				else if(board.getPiece(i, j).getTypeID() == 2) //if a Triangle on the button
				{
					if(board.getPiece(i, j).getColor() == 1)
					{
						square[i][j].setGraphic(new ImageView(redTriangle));
					}
					else
					{
						square[i][j].setGraphic(new ImageView(blueTriangle));
					}
				}
				else if(board.getPiece(i, j).getTypeID() == 3) //if a Chevron on the button
				{
					if(board.getPiece(i, j).getColor() == 1)
					{
						square[i][j].setGraphic(new ImageView(redChevron));
					}
					else
					{
						square[i][j].setGraphic(new ImageView(blueChevron));
					}
				}
				else //if a Sun on the button
				{
					if(board.getPiece(i, j).getColor() == 1)
					{
						square[i][j].setGraphic(new ImageView(redSun));
					}
					else
					{
						square[i][j].setGraphic(new ImageView(blueSun));
					}
				}
			}
		}
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return startGame button
	* @param column
	* @param row
	* @return square[column][row]
	*/
	public Button getASquare(int column, int row)
	{
		return square[column][row];
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return startGame button
	* @return startGame
	*/
	public Button getStartGame()
	{
		return startGame;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return endGame button
	* @return endGame
	*/
	public Button getEndGame()
	{
		return endGame;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return loadGame button
	* @return loadGame
	*/
	public Button getLoadGame()
	{
		return loadGame;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return saveGame button
	* @return saveGame
	*/
	public Button getSaveGame()
	{
		return saveGame;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return viewRules button
	* @return viewRules
	*/
	public Button getViewRules()
	{
		return viewRules;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return currentPlayerLabel label
	* @return currentPlayerLabel
	*/
	public Label getCurrentPlayerLabel()
	{
		return currentPlayerLabel;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to set currentPlayerLabel
	* @param currentPlayer
	*/
	public void setCurrentPlayerLabel(Player currentPlayer)
	{
		currentPlayerLabel.setText("Current Player: " + currentPlayer.getName());
	}
}


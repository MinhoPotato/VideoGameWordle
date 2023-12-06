import javafx.application.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.beans.value.*;
import javafx.event.*; 
import javafx.animation.*;
import javafx.geometry.*;
import java.io.*;
import java.util.*;
import javafx.scene.control.TextField;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.TableColumn.*;
import javafx.util.converter.*;
import javafx.scene.chart.*;

/**
 *  Delaney Noel
 *  11/17/2023
 *  
 *  Final Project, Video game Wordle
 *  This game is a wordle like copy, but using video games
 *  Below is the code to run it, if there are games you wish to have always show up
 *  add them into the ArraySetUp method.
 *  
 *  I would like to give a huge thank you to my friend Dylan Roos, who designed the logo and icon image for this game.
 * 
 *   JavaFX API: https://openjfx.io/javadoc/11/
 */ 
public class finalProject extends Application 
{
    // run the application
    int guessNumber;
    VideoGame myHiddenGame;
    public static void main(String[] args) 
    {
        try
        {
            // creates Stage, calls the start method
            launch(args);
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }

    // Application is an abstract class,
    //  requires the method: public void start(Stage s)
    public void start(Stage mainStage) 
    {
        // set the text that appears in the title bar
        mainStage.setTitle("Video Game Wordle");
        mainStage.setResizable(true);
        mainStage.getIcons().add(new Image("images/VGWordleLogo.png"));

        // layout manager: organize window contents
        BorderPane root = new BorderPane();

        // set font size of objects
        root.setStyle(  "-fx-font-size: 18;"  );

        // May want to use a Box to add multiple items to a region of the screen
        VBox box = new VBox();
        // add padding/margin around area
        box.setPadding( new Insets(16) );
        // add space between objects
        box.setSpacing( 16 );
        // set alignment of objects (default: Pos.TOP_LEFT)
        box.setAlignment( Pos.CENTER );
        // Box objects store contents in a list
        List<Node> boxList = box.getChildren();
        // if you choose to use this, add it to one of the BorderPane regions

        // Scene: contains window content
        // parameters: layout manager; width window; height window
        Scene mainScene = new Scene(root);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        // custom application code below -------------------

        ArrayList<VideoGame> games = arraySetUp();
        //array list of our games
        ComboBox comboBox = new ComboBox();

        for(int i = 0; i < games.size(); i++){
            comboBox.getItems().add(games.get(i).getTitle());
            //combo box to select the game
        }
        //make our combo box full of game titles
        GridPane guesses = new GridPane();
        guesses.setAlignment(Pos.CENTER);
        guesses.setPadding(new Insets(16));
        guesses.setHgap(16);
        guesses.setVgap(20);
        //set comboBox to the top of guesses
        Button replay = new Button("Replay");
        Button home = new Button("Home");
        Button start = new Button("Start");
        Button add = new Button("Configure Game List");
        Button viewGames = new Button("View Games");

        startScreen(box, start, add, viewGames);

        Boolean gameOver = false;
        //myHiddenGame = reset(games, replay, box, guesses, comboBox);
        //System.out.println(myHiddenGame.getTitle());

        //make the hidden video game and for now print the title so that I can compare it

        comboBox.setOnKeyPressed(e -> 
            {
                if(e.getCode() == KeyCode.ENTER) { 
                    playGame(replay, myHiddenGame, box, comboBox, guesses, games, home);
                }
            }
        );

        replay.setOnAction(
            (event) ->
            {
                myHiddenGame = reset(games, replay, box, guesses, comboBox, home);
                mainStage.sizeToScene();
                //reset the game and size to screen
            }
        );

        start.setOnAction(
            (event) ->
            {
                myHiddenGame = reset(games, replay, box, guesses, comboBox, home);
                //start the game!
                Alert instructions = new Alert(AlertType.INFORMATION);
                instructions.setTitle("Instructions");
                instructions.setHeaderText("Instructions for VideoGame Wordle");
                instructions.setContentText("Each game has 6 categories, upon every guess you will get feedback\n"
                    + "An up arrow means the hidden game is higher (either alphabetically or numerically)\n"
                    + "A down arrow means the opposite!\nA green square means it is correct!\n"
                    + "You have 6 guesses to get the right game!");
                //instructions alert box
                instructions.showAndWait();
                //start game and size to scene
                mainStage.sizeToScene();
            }
        );

        home.setOnAction(
            (event) ->
            {
                startScreen(box, start, add, viewGames);
                mainStage.sizeToScene();
                //Go to Home menu and size to scene
            }
        );

        add.setOnAction(
            (event) ->
            {
                addGame(box, home, games, comboBox);
                mainStage.sizeToScene();
                //go to add game and size to scene
            }
        );

        viewGames.setOnAction(
            (event) ->{
                root.setCenter(gameScreen(box, games, home));
                mainStage.sizeToScene();
                //set the root and size to scene
            }
        );   
        root.setCenter(box);
        // custom application code above -------------------
        // after adding all content, make the Stage visible
        mainStage.show();
        mainStage.sizeToScene();
    }

    /*
     * Array set up returns an array list of video games
     * This is run at the very beginning of the program as a way to block off code
     * and help make it easier to find
     * We use a try and catch block because our video game class can throw errors
     */
    public ArrayList arraySetUp(){
        ArrayList<VideoGame> games = new ArrayList<>();
        //now arrayList of video games
        try{
            games.add(new VideoGame("Assassin's Creed Black Flag", "Action", "Adventure", "UbiSoft", 100000000, "M"));
            games.add(new VideoGame("GTA V", "Action", "Adventure", "Rockstar", 190000000, "M"));
            games.add(new VideoGame("Mario Kart", "Racing", "Action", "Nintendo", 65470000, "E"));
            games.add(new VideoGame("Minecraft", "Sandbox", "Survival", "Mojang", 300000000, "E10"));
            games.add(new VideoGame("Pokemon Black/White", "Action", "RolePlaying", "Nintendo", 25000000, "E10"));
            games.add(new VideoGame("Red Dead Redemption 2", "Action", "Adventure", "Rockstar", 57000000, "M"));
            games.add(new VideoGame("Super Mario Bros.", "Platform", "Adventure", "Nintendo", 58000000, "E"));
            games.add(new VideoGame("Tetris", "Puzzle", "Strategy", "Electronic Arts", 100000000, "E"));
            games.add(new VideoGame("The Walking Dead", "RolePlaying", "Adventure", "TellTale Games", 28000000, "M"));
            games.add(new VideoGame("Wii Sports", "Sports", "Simulation", "Nintendo", 82900000, "E"));
            //try to add games
        }
        catch(Exception e){
            System.out.println("This is an error on the backside that needs to be fixed.");
            //catch an errors
        }
        //add a bunch of games
        return games;
    }

    /*
     * Reset returns a video game and takes in an arraylist, button, box, gridpane, comboBox and another button
     * We reset our guess number to one, pick a new hidden game and reset the grid pane of boxes
     */    
    public VideoGame reset(ArrayList games, Button replay, VBox box, GridPane guesses, ComboBox combo, Button home){
        guessNumber = 1;
        //reset guess count
        int rand = (int) (Math.random() * games.size());
        //randomize new game
        replay.setVisible(false);
        replay.setDisable(true);
        home.setVisible(false);
        home.setDisable(true);
        //hide replay and disable it
        combo.setDisable(false);
        //reenable combo
        box.getChildren().clear();
        box.getChildren().add(combo);

        String[] categories = new String[]{"Title", "Genre", "SubGenre", "Publisher", "UnitsSold", "Rating"};
        for(int i = 0; i < 6; i++){
            guesses.add(new Label(categories[i]), i, 0);
            for(int j = 0; j < 6; j++){
                Rectangle guess = new Rectangle(50, 50, Color.GRAY);
                guesses.add(guess, j, i+1);
            }
        }
        //add rectangle to guesses
        guesses.getColumnConstraints().clear();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.CENTER);
        col1.setPrefWidth(90);
        guesses.getColumnConstraints().addAll(col1, col1, col1, col1, col1, col1);
        //column constraints

        //add column constraints
        box.getChildren().add(guesses);
        HBox buttons = new HBox();
        buttons.setPadding( new Insets(16) );
        buttons.setSpacing( 16 );
        buttons.setAlignment( Pos.CENTER );
        //new hbox for buttons
        buttons.getChildren().addAll(replay, home);
        box.getChildren().add(buttons);
        //clear the box then add guesses and replay
        return (VideoGame) games.get(rand); 
        //return the random video game
    }

    /*
     * This method takes in a button, videogame, vbox, string, int, comboBox and a button
     * We use this to see if the user either won the game or lost
     */
    public void wonGame(Button replay, VideoGame hiddenGame, VBox pane, String answer, int guesses, ComboBox combo, Button home){
        if(answer.equals("111111")){
            replay.setVisible(true);
            replay.setDisable(false);
            home.setVisible(true);
            home.setDisable(false);
            pane.getChildren().add(new Label("You won! The Game was " + hiddenGame.getTitle()));
            combo.setDisable(true);
            //set replay to visible and enable it, add it to the box and disable combo box
        }
        else if(guesses > 6){
            replay.setVisible(true);
            replay.setDisable(false);
            home.setVisible(true);
            home.setDisable(false);
            pane.getChildren().add(new Label("You Lost! The Game was " + hiddenGame.getTitle()));
            combo.setDisable(true);
            //set replay to visible and enable it, add it to the box and disable combo box
        }
    }

    /*
     * this method takes in a button, videogame, vbox, combobox, gridpane, arraylist and button
     * This is our main play game method! We compare each character of the string
     * To see what kind of rectangle we draw on that space
     */
    public void playGame(Button replay, VideoGame myHiddenGame, VBox box, ComboBox comboBox, GridPane guesses, ArrayList games, Button home){
        //System.out.println(comboBox.getSelectionModel().getSelectedIndex());
        int guess = comboBox.getSelectionModel().getSelectedIndex();
        //what game index they guessed
        //System.out.println(myHiddenGame.compare(games.get(guess)));
        String comparisonOutput = myHiddenGame.compare((VideoGame) games.get(guess));
        //what the comparison data is
        for(int i = 0; i < comparisonOutput.length(); i++){
            //for each char in the string make the correct box
            if(comparisonOutput.charAt(i) == '1'){
                guesses.add(new Rectangle(50, 50, Color.GREEN), i, guessNumber);
            }
            else if(comparisonOutput.charAt(i) == '0'){
                Image image = new Image("images/downArrow.png",64,64, true, true);
                ImageView iv1 = new ImageView();
                iv1.setImage(image);
                iv1.setFitWidth(50);
                iv1.setPreserveRatio(true);
                //testing a new image
                guesses.add(iv1, i, guessNumber);
            }
            else{
                Image image = new Image("images/upArrow.png",64,64, true, true);
                ImageView iv1 = new ImageView();
                iv1.setImage(image);
                iv1.setFitWidth(50);
                iv1.setPreserveRatio(true);
                guesses.add(iv1, i, guessNumber);
            }
        }
        this.guessNumber++;
        //increment guess Number
        wonGame(replay, myHiddenGame, box, comparisonOutput, guessNumber, comboBox, home);
        //check to see if the player won their game
    }

    /*
     * This method takes in a box, and three buttons
     * sets up our start screen!
     */
    public void startScreen(VBox box, Button start, Button add, Button whatGames){
        box.getChildren().clear();
        //clear box
        HBox buttons = new HBox();
        buttons.setPadding( new Insets(16) );
        buttons.setSpacing( 16 );
        buttons.setAlignment( Pos.CENTER );
        //new hbox for buttons
        buttons.getChildren().addAll(start, add, whatGames);
        //add buttons
        Image image = new Image("images/VGWordleLogo.png",300,300, true, true);
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(300);
        iv1.setPreserveRatio(true);
        //get image view
        box.getChildren().addAll(iv1, buttons);
        //add all these to our vBox
    }

    /*
     * this metho takes in a vbox, arraylist and button and returns a scroll pane
     * this allows us to show all the games on the screen
     */
    public ScrollPane gameScreen(VBox box, ArrayList games, Button home){
        box.getChildren().clear();
        for(int i = 0; i < games.size(); i++){
            box.getChildren().add(new Label(games.get(i).toString()));
            //add the to string for each to the box
        }
        box.getChildren().add(home);
        ScrollPane sp = new ScrollPane();
        sp.setContent(box);
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        //set up our scroll pane
        return sp;
    }

    /*
     * This method takes in a vbox, button, arraylist and combobox
     * This sets up the add game menu
     * And contains the code for adding a game plus error checking
     */
    public void addGame(VBox box, Button home, ArrayList games, ComboBox combo){
        box.getChildren().clear();
        //clear the box
        GridPane information = new GridPane();
        information.setAlignment(Pos.CENTER);
        information.setPadding(new Insets(16));
        information.setHgap(16);
        information.setVgap(16);
        //create gridpane to organize info
        Label titleLabel = new Label("Enter Title");
        TextField titleField = new TextField();
        information.add(titleLabel, 0, 0);
        information.add(titleField, 1, 0);
        //add title labal and title field to our gridpane
        Label genreLabel = new Label("Enter Genre");
        TextField genreField = new TextField();
        information.add(genreLabel, 0, 1);
        information.add(genreField, 1, 1);
        //genre field added
        Label subgenreLabel = new Label("Enter SubGenre");
        TextField subgenreField = new TextField();
        information.add(subgenreLabel, 0, 2);
        information.add(subgenreField, 1, 2);
        //subGenre field added
        Label publisherLabel = new Label("Enter Publisher");
        TextField publisherField = new TextField();
        information.add(publisherLabel, 0, 3);
        information.add(publisherField, 1, 3);
        //publisher field added
        Label unitsLabel = new Label("Enter Units Sold");
        TextField unitsField = new TextField();
        information.add(unitsLabel, 0, 4);
        information.add(unitsField, 1, 4);
        //units sold added
        Label ratingLabel = new Label("Enter Rating");
        TextField ratingField = new TextField();
        information.add(ratingLabel, 0, 5);
        information.add(ratingField, 1, 5);
        //add rating
        //end label and textfield setup
        Button addGame = new Button("Add Game!");
        addGame.setOnAction(
            (event) ->
            {
                try{
                    String title = titleField.getText();
                    String genre = genreField.getText();
                    String subgenre = subgenreField.getText();
                    String publisher = publisherField.getText();
                    int unitsSold = Integer.parseInt(unitsField.getText());
                    String rating = ratingField.getText();
                    //get text from all of the label
                    games.add(new VideoGame(title, genre, subgenre, publisher, unitsSold, rating));
                    combo.getItems().add(title);
                    //video game added to the array
                    Alert added = new Alert(AlertType.CONFIRMATION);
                    added.setTitle("Confirmation");
                    added.setHeaderText("Confirmation of game added");
                    added.setContentText("The game " + title + "Was successfully added!");
                    added.showAndWait();
                    //show confirmation alert
                }
                catch(Exception e){
                    Alert error = new Alert(AlertType.ERROR);
                    error.setTitle("Error!");
                    error.setContentText("This game could not be added because some of the data was incorrect/not valid\nError type " + e);
                    error.showAndWait();
                    //error alert!
                }
            }
        );

        HBox buttons = new HBox();
        buttons.setPadding( new Insets(16) );
        buttons.setSpacing( 16 );
        buttons.setAlignment( Pos.CENTER );
        //buttons hBox
        buttons.getChildren().addAll(addGame, home);

        box.getChildren().addAll(information, buttons);
    }
}

package Pro_Final_POO_Bejeweled_Code;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {

    int px1, py1, px2, py2;

    int selOneOrTwo = 1;
    private Game gameLogic=null;
    private Button[] btns = new Button[64];
    private Label numPointsLabel = new Label("0");


    @Override
    public void start(Stage primaryStage) {

        gameLogic =new Game();
        initBtnsArray();
        updateScore();
        Group root = new Group();

        HBox gameArena = new HBox();
        gameArena.getChildren().add(getGrid());
        gameArena.getChildren().add(getGamePanel());
        root.getChildren().add(gameArena);
        Scene scene = new Scene(root, 500, 320);
        primaryStage.setTitle("Bejeweled!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

    private Pane getGrid() {
        int i = 0;
        GridPane gridPane = new GridPane();

        for (int n = 1; n <= 8; n++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(40)); // column 1 is 40 wide
            gridPane.getRowConstraints().add(new RowConstraints(40)); // column 1 is 40 wide
        }

        for (Button b : btns) {
            b.setOnAction(this::handleButtonAction);
            gridPane.add(b, i / 8, (int) (i % 8));
            i++;
        }
        return gridPane;
    }
    
    private void initBtnsArray() {
        
        for (int i = 0; i < btns.length; i++) {
            btns[i] = new Button();
            btns[i].setPadding(Insets.EMPTY);
            btns[i].setMinSize(40, 40);
            btns[i].setMaxSize(40, 40);
            btns[i].setStyle("-fx-background-image: url('/testing/gem" + (gameLogic.marray[i%8][i/8]) + ".png')");

            
        }
    }


    private void handleButtonAction(ActionEvent event) {

        for (int i = 0; i < btns.length; i++) {
            if (event.getSource() == btns[i]) {
                if (selOneOrTwo == 1) {
                    System.out.print("First button clicked: ");
                    px1 = i % 8;
                    py1 = i / 8;
                    System.out.println("px1=" + px1 + " py1=" + py1);
                    selOneOrTwo = 2;
                } else if (selOneOrTwo == 2) {
                    System.out.print("Second button clicked: ");
                    px2 = i % 8;
                    py2 = i / 8;
                    System.out.println("px2=" + px2 + " py2=" + py2);
                    selOneOrTwo = 1;
                    gameLogic.movePiece(px1, py1, px2, py2);
                    updatebtn();
                    
                }
            }
        }
        updateScore();


    }
    private VBox getGamePanel() {
        VBox panelVBox = new VBox();
        panelVBox.setPrefWidth(180);
        panelVBox.setMinWidth(180);
        panelVBox.setMaxWidth(180);
        panelVBox.setAlignment(Pos.CENTER);
        panelVBox.setSpacing(10);
        panelVBox.setStyle("-fx-background-color: #EEE; ");
        Label scoreLabel = new Label("Score");
// numPointsLabel=new Label("00000");
        panelVBox.getChildren().add(scoreLabel);
        panelVBox.getChildren().add(numPointsLabel);

        return panelVBox;
    }

    private void updateScore() {
        int currentScore = gameLogic.pointNum;
        numPointsLabel.setText(String.valueOf(currentScore));
    }
    private void updatebtn() {
        
        for (int i = 0; i < btns.length; i++) {
            btns[i].setStyle("-fx-background-image: url('/testing/gem" + (gameLogic.marray[i%8][i/8]) + ".png')");


        }
    }
}
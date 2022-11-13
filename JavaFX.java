import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class JavaFX extends Application {
    private Button[][] gameBoard;
    private boolean playerX;
    private boolean playerO; // true if it is their turn
    private int playerXturns;
    private int playerOturns;
    private int playerXwins;
    private int playerOwins;

    private boolean isWinner() {
        return false;
    }

    @Override
    public void start(final Stage stage) {
        // update this method definition to complete the First JavaFX Activity
        stage.setTitle("TicTacToe");
        Label label = new Label("Turn: X");

        BorderPane border = new BorderPane();

        // init private fields
        gameBoard = new Button[3][3];
        playerX = true;
        playerO = false;
        playerXturns = 0;
        playerOturns = 0;
        playerXwins = 0;
        playerOwins = 0;
        Label scoreboard = new Label("Score:");
        Label scoreX = new Label("playerX: " + playerXwins);
        Label scoreO = new Label("playerO: " + playerOwins);
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button("   ");
                btn.setStyle("-fx-font-size:50");
                gridPane.add(btn, i, j);
                gameBoard[i][j] = btn;

                btn.setOnAction((ActionEvent e) -> {
                    // if X is playing

                    if (playerX) {
                        // check that this btn's text is " "
                        if (btn.getText() == "   ") {
                            btn.setText("X");

                            playerXturns++;
                            // check for winner or if board is filled up
                            if (isWinner()) {
                                playerXwins++;
                            }
                            playerO = true;
                            playerX = false;
                            label.setText("Turn: O");
                            // change label and end turn by flipping boolean vals

                        }
                    }
                    // if O is playing
                    else {
                        if (btn.getText() == "   ") {
                            btn.setText("O");

                            playerOturns++;
                            // check for winner or if board is filled up
                            if (isWinner()) {
                                playerOwins++;
                            }
                            playerO = false;
                            playerX = true;
                            label.setText("Turn: X");
                            // last: change label and end turn by flipping boolean vals
                        }

                    }
                });

            }
        }

        border.setCenter(gridPane);
        HBox top = new HBox();
        VBox right = new VBox();
        VBox left = new VBox();
        Button reset = new Button("reset board");
        reset.setOnAction((ActionEvent e) -> {
            if (reset.getText() == "reset board") {
                reset();
            }
        });

        left.getChildren().add(reset);
        top.getChildren().add(label);
        right.getChildren().addAll(scoreboard, scoreX, scoreO);
        border.setTop(top);
        border.setLeft(left);
        border.setRight(right);

        Group group = new Group(border);// , label);// , circle, polygon);

        Scene scene = new Scene(group, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    private void reset() {
        // reset buttons
        for (int i = 0; i < 3; i++) {
            for (Button b : gameBoard[i]) {
                b.setText("   ");
            }
        }

    }

    private void gameOver() {
        // popup message with rematch option

    }

    public static void main(String[] args) {
        Application.launch();
    }
}

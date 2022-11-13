import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    private Label label;
    private boolean playerX;
    private boolean playerO; // true if it is their turn
    private int playerXturns;
    private int playerOturns;
    private int playerXwins;
    private int playerOwins;

    private boolean isWinner() {
        if (playerX) {
            // check diagonals
            if (gameBoard[0][0].getText() == "X" && gameBoard[1][1].getText() == "X"
                    && gameBoard[2][2].getText() == "X") {
                return true;
            }
            if (gameBoard[0][2].getText() == "X" && gameBoard[1][1].getText() == "X"
                    && gameBoard[2][0].getText() == "X") {
                // gameBoard[0][2].setText("wtf");
                return true;
            }
            // check columns
            if (gameBoard[0][0].getText() == "X" && gameBoard[0][1].getText() == "X"
                    && gameBoard[0][2].getText() == "X") {
                return true;
            }
            if (gameBoard[1][0].getText() == "X" && gameBoard[1][1].getText() == "X"
                    && gameBoard[1][2].getText() == "X") {
                return true;
            }
            if (gameBoard[2][0].getText() == "X" && gameBoard[2][1].getText() == "X"
                    && gameBoard[2][2].getText() == "X") {
                return true;
            }
            // check rows
            if (gameBoard[0][0].getText() == "X" && gameBoard[1][0].getText() == "X"
                    && gameBoard[2][0].getText() == "X") {
                return true;
            }
            if (gameBoard[0][1].getText() == "X" && gameBoard[1][1].getText() == "X"
                    && gameBoard[2][1].getText() == "X") {
                return true;
            }
            if (gameBoard[0][2].getText() == "X" && gameBoard[1][2].getText() == "X"
                    && gameBoard[2][2].getText() == "X") {
                return true;
            }
        } else if (playerO) {
            if (gameBoard[0][0].getText() == "O" && gameBoard[1][1].getText() == "O"
                    && gameBoard[2][2].getText() == "O") {
                return true;
            }
            if (gameBoard[0][2].getText() == "O" && gameBoard[1][1].getText() == "O"
                    && gameBoard[2][0].getText() == "O") {
                // gameBoard[0][2].setText("wtf");
                return true;
            }
            // check columns
            if (gameBoard[0][0].getText() == "O" && gameBoard[0][1].getText() == "O"
                    && gameBoard[0][2].getText() == "O") {
                return true;
            }
            if (gameBoard[1][0].getText() == "O" && gameBoard[1][1].getText() == "O"
                    && gameBoard[1][2].getText() == "O") {
                return true;
            }
            if (gameBoard[2][0].getText() == "O" && gameBoard[2][1].getText() == "O"
                    && gameBoard[2][2].getText() == "O") {
                return true;
            }
            // check rows
            if (gameBoard[0][0].getText() == "O" && gameBoard[1][0].getText() == "O"
                    && gameBoard[2][0].getText() == "O") {
                return true;
            }
            if (gameBoard[0][1].getText() == "O" && gameBoard[1][1].getText() == "O"
                    && gameBoard[2][1].getText() == "O") {
                return true;
            }
            if (gameBoard[0][2].getText() == "O" && gameBoard[1][2].getText() == "O"
                    && gameBoard[2][2].getText() == "O") {
                return true;
            }
        }

        return false;
    }

    @Override
    public void start(final Stage stage) {
        // update this method definition to complete the First JavaFX Activity
        stage.setTitle("TicTacToe");
        label = new Label("Turn: X");

        BorderPane border = new BorderPane();
        Insets inset = new Insets(10);
        border.setPadding(inset);
        // init private fields
        gameBoard = new Button[3][3];
        playerX = true;
        playerO = false;
        playerXturns = 0;
        playerOturns = 0;
        playerXwins = 0;
        playerOwins = 0;

        // maybe a welcome screen or something

        Label scoreboard = new Label("Score:");
        Label scoreX = new Label("playerX: " + playerXwins);
        Label scoreO = new Label("playerO: " + playerOwins);
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button("   ");
                btn.setStyle("-fx-font-size:45");
                btn.setPrefWidth(100.0);
                btn.setMaxWidth(100.0);
                gridPane.add(btn, i, j);
                gameBoard[i][j] = btn;

                btn.setOnAction((ActionEvent e) -> {
                    // if X is playing

                    if (playerX) {
                        if (btn.getText() == "   ") {
                            btn.setText("X");

                            playerXturns++;
                            // check for winner or if board is filled up
                            if (isWinner()) {
                                playerXwins++;
                                scoreX.setText("playerX: " + playerXwins);
                                // end game? reset?
                                gameOver();
                            } else {
                                // change label and end turn by flipping boolean vals
                                playerO = true;
                                playerX = false;
                                label.setText("Turn: O");
                            }

                            if (playerXturns + playerOturns == 9) {
                                gameOver();
                            }
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
                                scoreO.setText("playerO: " + playerOwins);
                                // end game? reset?
                                gameOver();
                            } else {
                                playerO = false;
                                playerX = true;
                                label.setText("Turn: X");
                            }

                            if (playerXturns + playerOturns == 9) {
                                gameOver();
                            }
                            // last: change label and end turn by flipping boolean vals
                        }

                    }
                });

            }
        }
        gridPane.setVgap(12);
        gridPane.setHgap(12); // spacing between buttons (pxls)

        border.setCenter(gridPane);
        HBox top = new HBox();
        VBox right = new VBox();
        VBox left = new VBox();
        Button reset = new Button("reset board");
        reset.getStyleClass().removeAll("button");
        reset.getStyleClass().add("resetButton");
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
        BorderPane.setMargin(left, inset);
        BorderPane.setMargin(right, inset);

        Group group = new Group(border);// , label);// , circle, polygon);

        Scene scene = new Scene(group, 500, 500);
        scene.getStylesheets().add(getClass().getResource("test.css").toExternalForm());
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
        // reset labels and booleans, x always starts
        playerX = true;
        playerO = false;
        playerXturns = 0;
        playerOturns = 0;
        label.setText("Turn: X");

    }

    private void gameOver() {
        // popup message with rematch option
        // reset
        reset();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}

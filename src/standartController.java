import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class standartController {

    @FXML
    private Label duoLabel;

    @FXML
    private Label fiveLabel;

    @FXML
    private Label singleLabel;

    @FXML
    private Label trioLabel;

    @FXML
    private Label inicioLabel;

    @FXML
    void inicioAction(MouseEvent event) {
        openMainScreen();

    }

    @FXML
    void inicioExitAction(MouseEvent event) {
        inicioLabel.setEffect(null);

    }

    @FXML
    void inicioPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        inicioLabel.setEffect(dropShadow);

    }



    @FXML
    void duoExitAction(MouseEvent event) {

    }

    @FXML
    void duoPopAction(MouseEvent event) {

    }

    @FXML
    void duoRoomAction(MouseEvent event) {

    }

    @FXML
    void fiveExitAction(MouseEvent event) {

    }

    @FXML
    void fivePopAction(MouseEvent event) {

    }

    @FXML
    void fiveRoomAction(MouseEvent event) {

    }

    @FXML
    void singleExitAction(MouseEvent event) {

    }

    @FXML
    void singlePopAction(MouseEvent event) {

    }

    @FXML
    void singleRoomAction(MouseEvent event) {

    }

    @FXML
    void trioExitAction(MouseEvent event) {

    }

    @FXML
    void trioPopAction(MouseEvent event) {

    }

    @FXML
    void trioRoomAction(MouseEvent event) {

    }

    private void openMainScreen(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            Stage thirdStage = new Stage();
            thirdStage.setTitle("Main Screen");
            thirdStage.setScene(tela);
            thirdStage.show();
        }catch (Exception e) {
            System.out.println("Erro ao abrir a main screen");
        }

        Stage stage = (Stage) inicioLabel.getScene().getWindow();
        stage.close();

        }
}


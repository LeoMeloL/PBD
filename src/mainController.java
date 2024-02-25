import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class mainController {

    @FXML
    private Label contatoLabel;

    @FXML
    private Label historiaLabel;

    @FXML
    private Label inicioLabel;

    @FXML
    private Label quartosLabel;

    @FXML
    void contatoAction(MouseEvent event) {
        openContactScreen();

    }

    @FXML
    void contatoExitAction(MouseEvent event) {
        contatoLabel.setEffect(null);

    }

    @FXML
    void contatoPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        contatoLabel.setEffect(dropShadow);
    }

    @FXML
    void historiaAction(MouseEvent event) {
        openHistoryScreen();

    }

    @FXML
    void inicioAction(MouseEvent event) {

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
    void quartosExitAction(MouseEvent event) {
        quartosLabel.setEffect(null);

    }

    @FXML
    void quartosPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        quartosLabel.setEffect(dropShadow);

    }

    @FXML
    void quartosAction(MouseEvent event) {
        openRoomScreen();

    }

    @FXML
    void historiaPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        historiaLabel.setEffect(dropShadow);
    }

    @FXML
    void historiaExitAction(MouseEvent event) {
        historiaLabel.setEffect(null);

    }


    private void openHistoryScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyLayout.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("História");
            primaryStage.setScene(tela);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir a history screen");
        }
    }

    private void openContactScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contactLayout.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Contato");
            primaryStage.setScene(tela);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir a contact screen");
        }
    }

    private void openRoomScreen(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("roomLayout.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Rooms");
            primaryStage.setScene(tela);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir a room screen");
        }

    }


}


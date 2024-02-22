import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class contactController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField msgField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField telefoneField;

     @FXML
    void sendMessageAction(ActionEvent event) {
        warning("Voce ja foi longe de mais", "Não tem mais nada aqui.......");
    }

    private void warning(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}


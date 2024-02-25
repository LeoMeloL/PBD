import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class roomController {

    @FXML
    private Button searchBarButton;

    @FXML
    private TextField searchBarField;

    @FXML
    private ImageView standartImage;

    @FXML
    private ImageView suiteImage;

    @FXML
    private ImageView vipImage;

    @FXML
    void searchBarAction(ActionEvent event) {

    }

    @FXML
    void standartAction(MouseEvent event) {
        openStandartScreen();

    }

    @FXML
    void standartExitAction(MouseEvent event) {
        standartImage.setEffect(null);

    }

    @FXML
    void standartPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        standartImage.setEffect(dropShadow);

    }

    @FXML
    void suiteAction(MouseEvent event) {

    }

    @FXML
    void suiteExitAction(MouseEvent event) {
        suiteImage.setEffect(null);

    }

    @FXML
    void suitePopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        suiteImage.setEffect(dropShadow);

    }

    @FXML
    void vipAction(MouseEvent event) {

    }

    @FXML
    void vipExitAction(MouseEvent event) {
        vipImage.setEffect(null);

    }

    @FXML
    void vipPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        vipImage.setEffect(dropShadow);

    }

    private void openStandartScreen(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("standartLayout.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("standart");
            primaryStage.setScene(tela);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir a standart screen");
        }
    }
}


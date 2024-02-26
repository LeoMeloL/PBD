import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class accountController {

    String textoDigitado;
    String url = "jdbc:postgresql://192.168.0.6:5432/PBD";
    String user = "postgres";
    String password = "123456";
    String nome;
    String cpf;
    String senha;
    int id;
    String email;

    @FXML
    private Label nameLabel;

    @FXML
    private Label inicioLabel;

    @FXML
    private Label spentLabel;


    @FXML
    private Label reservasLabel;

    @FXML
    private Label reservasQtdLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private Label cpfLabel;

    @FXML
    private Label senhaLabel;

    @FXML
    private Label checkoutLabel;

    @FXML
    private Label faturaLabel;

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
    void checkoutAction(MouseEvent event) {
        String sql = "DELETE FROM reserva WHERE id_usuario = ?";
    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, id);
        int linhasAfetadas = statement.executeUpdate();
        System.out.println(linhasAfetadas + " reserva(s) do usuário " + id + " foi(foram) apagada(s).");
    } catch (SQLException e) {
        e.printStackTrace();
    }

    reservasQtdLabel.setText("0");
    spentLabel.setText("0");

    }

    @FXML
    void checkoutExitAction(MouseEvent event) {
        checkoutLabel.setEffect(null);

    }

    @FXML
    void checkoutPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        checkoutLabel.setEffect(dropShadow);
    }

    @FXML
    void faturaAction(MouseEvent event) {

    }

    @FXML
    void faturaExitAction(MouseEvent event) {
        faturaLabel.setEffect(null);

    }

    @FXML
    void faturaPopAction(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        faturaLabel.setEffect(dropShadow);
        

    }

    @FXML
    void emailEnterAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            email = emailTextField.getText();
            obterDadosUsuario();
            calcularGastoTotalDoUsuario(id);
        }
    }

    public int obterDadosUsuario() {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Connection connection;

        

        try {
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                nome = result.getString("nome");
                senha = result.getString("senha_hash");
                cpf = result.getString("cpf");
                id = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nameLabel.setText(nome);
        cpfLabel.setText(cpf);
        senhaLabel.setText(senha);
        emailLabel.setText(email);

        return 1;

    }

    public double calcularGastoTotalDoUsuario(int idUsuario) {
        double gastoTotal = 0.0;
        int quantidadeReservas = 0;
        String sql = "SELECT COUNT(*) AS total_reservas, SUM(preco) AS total_gasto FROM reserva WHERE id_usuario = ?";
        Connection connection = null;
    
        try {
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet result = statement.executeQuery();
    
            if (result.next()) {
                quantidadeReservas = result.getInt("total_reservas");
                gastoTotal = result.getDouble("total_gasto");
                System.out.println("Quantidade de reservas: " + quantidadeReservas);
                System.out.println("Gasto total do usuário: " + gastoTotal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        reservasQtdLabel.setText(Integer.toString(quantidadeReservas)); // Atualiza a label com o gasto total
        spentLabel.setText("R$" + Double.toString(gastoTotal));
        return quantidadeReservas;
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


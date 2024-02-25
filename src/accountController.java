import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


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
    void emailEnterAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            email = emailTextField.getText();
            obterDadosUsuario();
            contarReservasDoUsuario(id);
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

    public int contarReservasDoUsuario(int idUsuario) {
        int quantidadeReservas = 0;
        String sql = "SELECT COUNT(*) AS total_reservas FROM reserva WHERE id_usuario = ?";
        Connection connection;

        try {
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                quantidadeReservas = result.getInt("total_reservas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reservasQtdLabel.setText(Integer.toString(quantidadeReservas));
        return quantidadeReservas;

    }

    
}


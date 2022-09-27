package br.com.fiap.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.fiap.dao.ClienteDao;
import br.com.fiap.dao.VeiculoDao;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Veiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPlaca;
    @FXML private TextField textFieldPreco;
    @FXML private TextField textFieldAno;

    @FXML private ListView<Veiculo> listView;

    @FXML private TextField textFieldNome;
    @FXML private TextField textFieldEmail;
    @FXML private TextField textFieldTelefone;
    @FXML private TableView<Cliente> tableViewClientes;
    @FXML private TableColumn<Cliente, String> colunaNome;
    @FXML private TableColumn<Cliente, String> colunaEmail;
    @FXML private TableColumn<Cliente, String> colunaTelefone;

    public void salvar(){
        var veiculo = carregarVeiculoDoFormulario();
        try {
            VeiculoDao dao = new VeiculoDao();
            dao.inserir(veiculo);
            mostrarAlerta("Veículo cadatrado com sucesso");
            limparFormulario();
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        } catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
    }

    public void salvarCliente(){
        //try{
            var cliente = carregarClienteDoFormulario();
        //    var dao = new ClienteDao();
        //    dao.inserir(cliente);
            mostrarAlerta("Cliente cadastrado com sucesso.");
            tableViewClientes.getItems().add(cliente);
        //}catch(SQLException e){
        //    mostrarAlerta("Erro ao cadastrar cliente. " + e.getMessage());
        //}
    }

    private void atualizarListView(List<Veiculo> lista) {
        listView.getItems().clear();
        listView.getItems().addAll(lista);
    }

    private void limparFormulario() {
        textFieldMarca.setText("");
        textFieldModelo.setText("");
        textFieldPlaca.setText("");
        textFieldPreco.setText("");
        textFieldAno.setText("");
    }

    private void mostrarAlerta(String mensagem) {
        var alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

    private Veiculo carregarVeiculoDoFormulario() {
        String marca = textFieldMarca.getText();
        String modelo = textFieldModelo.getText();
        String placa = textFieldPlaca.getText();
        double preco = Double.valueOf( textFieldPreco.getText() );
        int ano = Integer.valueOf( textFieldAno.getText() );
        
        return new Veiculo(marca, modelo, placa, preco, ano);
    }

    private Cliente carregarClienteDoFormulario(){
        return new Cliente(
            textFieldNome.getText(), 
            textFieldEmail.getText(), 
            textFieldTelefone.getText()
        );
    }

    public void ordenarPorPreco(){
        // lista.sort((o1, o2) -> Double.compare(o1.getPreco(), o2.getPreco()));
    }

    public void ordenarPorAno(){
        // lista.sort((o1, o2) -> Integer.compare(o1.getAno(), o2.getAno()));
    }

    public void carregarDados(){
        try {
            atualizarListView(new VeiculoDao().buscarTodos());
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        } catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        colunaNome.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().nome()));
        colunaEmail.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().email()));
        colunaTelefone.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().telefone()));
    }
    
}

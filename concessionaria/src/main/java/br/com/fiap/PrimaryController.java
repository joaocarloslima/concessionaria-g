package br.com.fiap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PrimaryController {

    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPlaca;
    @FXML private TextField textFieldPreco;
    @FXML private TextField textFieldAno;

    @FXML private ListView<Veiculo> listView;

    private List<Veiculo> lista = new ArrayList<>();

    public void salvar(){
        lista.add(carregarVeiculoDoFormulario());
        mostrarAlerta("Veículo cadatrado com sucesso");
        limparFormulario();
        atualizarListView();
    }

    private void atualizarListView() {
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

    public void ordenarPorPreco(){
        lista.sort((o1, o2) -> Double.compare(o1.getPreco(), o2.getPreco()));

        atualizarListView();
    }

    public void ordenarPorAno(){
        lista.sort((o1, o2) -> Integer.compare(o1.getAno(), o2.getAno()) );

        atualizarListView();

    }
    
}

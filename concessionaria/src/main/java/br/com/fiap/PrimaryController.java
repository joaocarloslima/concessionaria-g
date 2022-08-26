package br.com.fiap;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPlaca;
    @FXML private TextField textFieldPreco;
    @FXML private TextField textFieldAno;

    @FXML private ListView<Veiculo> listView;

    private List<Veiculo> lista = new ArrayList<>();

    public void salvar(){
        String marca = textFieldMarca.getText();
        String modelo = textFieldModelo.getText();
        String placa = textFieldPlaca.getText();
        double preco = Double.valueOf( textFieldPreco.getText() );
        int ano = Integer.valueOf( textFieldAno.getText() );
        
        Veiculo veiculo = new Veiculo(marca, modelo, placa, preco, ano);
        lista.add(veiculo);

        listView.getItems().addAll(lista);
    }
    
}

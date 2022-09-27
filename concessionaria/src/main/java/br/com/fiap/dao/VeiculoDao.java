package br.com.fiap.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Veiculo;
import br.com.fiap.util.ConnectionFactory;

public class VeiculoDao {

    public void inserir(Veiculo veiculo) throws SQLException, IOException {
            Connection con = ConnectionFactory.getConnection();
            
            String sql = "INSERT INTO DDD_CONCESSIONARIA_TB_VEICULOS (id, marca, modelo, placa, preco, ano) "+
            "VALUES (SEQ_VEICULO.nextVal, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setDouble(4, veiculo.getPreco());
            stmt.setInt(5, veiculo.getAno());
            
            stmt.execute();
            con.close();
    }

    public List<Veiculo> buscarTodos() throws SQLException, IOException {
        List<Veiculo> lista = new ArrayList<>();
        
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM DDD_CONCESSIONARIA_TB_VEICULOS";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");
            String placa = rs.getString("placa");
            double preco = rs.getDouble("preco");
            int ano = rs.getInt("ano");
            Veiculo veiculo = new Veiculo(marca, modelo, placa, preco, ano);
            lista.add(veiculo);
        }

        con.close();
        return lista;
    }

    /* 
     *  CREATE TABLE T_DDDC_VEICULO (
     *      ID NUMBER(5) NOT NULL PRIMARY KEY,
     *      MARCA VARCHAR(200),
     *      MODELO VARCHAR(200),
     *      PLACA VARCHAR(200),
     *      PRECO NUMBER(9,2),
     *      ANO NUMBER(4)
     * );
     * 
     * CREATE SEQUENCE SEQ_VEICULO;
     */
}

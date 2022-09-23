package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Veiculo;

public class VeiculoDao {

    private String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private String user = "pf1389";
    private String password = "fiaPf21";







    
    public void inserir(Veiculo veiculo) throws SQLException {
            Connection con = DriverManager.getConnection(url, user, password);
            
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

    public List<Veiculo> buscarTodos() throws SQLException {
        List<Veiculo> lista = new ArrayList<>();
        
        Connection con = DriverManager.getConnection(url, user, password);
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

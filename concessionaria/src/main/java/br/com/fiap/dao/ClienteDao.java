package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Cliente;

public class ClienteDao {

    private String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private String user = "PF1389";
    private String password = "senha";

    public void inserir(Cliente cliente) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO DDD_CONCESSIONARIA_TB_CLIENTES (id, nome, email, telefone) "
                        + "VALUES (SEQ_CLIENTE.nextVal, ?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, cliente.nome());
        stm.setString(2, cliente.email());
        stm.setString(3, cliente.telefone());

        stm.execute();
        con.close();
    }

    public List<Cliente> buscar(){
        return new ArrayList<Cliente>();
    }

}

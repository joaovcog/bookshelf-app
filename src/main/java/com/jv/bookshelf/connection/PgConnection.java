package com.jv.bookshelf.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joaov
 */
public class PgConnection {

    private final static String URL = "jdbc:postgresql://localhost:5432/bookshelf_db";
    private final static String USUARIO = "postgres";
    private final static String SENHA = "alca";

    public static Connection getConexao() throws ConnectionException {
        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }

}
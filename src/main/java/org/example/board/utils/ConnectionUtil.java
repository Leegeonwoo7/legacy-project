package org.example.board.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.board.constants.DatabaseSource.*;

@Slf4j
public class ConnectionUtil {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            log.info("getConnection= {}, {}", connection, connection.getClass());
        } catch (SQLException e) {
            log.error("커넥션을 얻는데 실패했습니다. 데이터 소스를 확인해주세요. {} {} {}", url, username, password);
            throw new RuntimeException(e);
        }

        return connection;
    }
}

package org.example.board.user;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.board.utils.ConnectionUtil.*;

@Slf4j
public class UserRepository {

    public String findUsernameById(Long userId) {
        String sql = "SELECT * FROM USER WHERE user_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }

            log.error("존재하지 않는 이름");
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }
}

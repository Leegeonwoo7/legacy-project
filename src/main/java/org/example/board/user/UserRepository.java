package org.example.board.user;


import lombok.extern.slf4j.Slf4j;
import org.example.board.user.dto.response.UserWordResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.example.board.utils.ConnectionUtil.*;

@Slf4j
@Repository
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

    public List<UserWordResponse> findAllByUserId(Long userId) {
        String sql = """
                SELECT w.word_id, w.name, w.create_at, w.view_count
                FROM word w 
                JOIN user u 
                ON u.user_id = w.user_id
                WHERE w.user_id = ?
                """;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<UserWordResponse> list = new ArrayList<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Long wordId = rs.getLong("word_id");
                String name = rs.getString("name");
                LocalDate createAt = rs.getDate("create_at").toLocalDate();
                long viewCount = rs.getLong("view_count");
                UserWordResponse response = UserWordResponse.of(wordId, name, createAt, viewCount);
                list.add(response);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }
}

package org.example.board.word;

import org.example.board.utils.ConnectionUtil;
import org.example.board.exception.NotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {

    public void save(Word word) {
        String sql = "insert into word (name, description, create_at) values (?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, word.getName());
            pstmt.setString(2, word.getDescription());
            pstmt.setString(3, word.getCreateAt().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    public Word find(final String findName) {
        String sql = "select * from word where name = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, findName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                long wordId = rs.getLong("word_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate createAt = rs.getDate("create_at").toLocalDate();
                long userId = rs.getLong("user_id");
                long viewCount = rs.getLong("view_count");
                return new Word(wordId, name, description, createAt, userId, viewCount);
            } else {
                throw new NotFoundException("존재하지 않는 단어입니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return null;
    }

    public void updateByName(String oldName, Word newWord) {
        String sql = "update word set name = ? ,description = ? where name = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newWord.getName());
            pstmt.setString(2, newWord.getDescription());
            pstmt.setString(3, oldName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotFoundException("존재하지 않는 단어입니다");
        } finally {
            close(con, pstmt, null);
        }
    }

    public void deleteByName(final String name) {
        String sql = "delete from word where name = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotFoundException("존재하지 않는 단어입니다");
        } finally {
            close(con, pstmt, null);
        }
    }

    public List<Word> findAll() {
        String sql = "select * from word";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Word> list = new ArrayList<>();

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                long wordId = rs.getLong("word_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate createAt = rs.getDate("create_at").toLocalDate();
                long userId = rs.getLong("user_id");
                long viewCount = rs.getLong("view_count");

                Word word = new Word(wordId, name, description, createAt, userId, viewCount);
                list.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }

    private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Connection getConnection() {
        return ConnectionUtil.getConnection();
    }
}

package org.example.board.word;

import lombok.extern.slf4j.Slf4j;
import org.example.board.exception.NotFoundException;
import org.example.board.word.dto.response.FindWordResponse;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.example.board.utils.ConnectionUtil.*;

@Slf4j
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

    public Word findByName(final String findName) {
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

    public Word findById(Long id) {
        String sql = "select * from word where word_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
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

    public FindWordResponse findWordWithWriterById(Long wordId) {
        log.debug("[레포지토리] wordId = {}", wordId);
        String sql = """
                SELECT
                    w.name, w.description, u.username, w.create_at, w.view_count
                FROM word w
                JOIN user u
                ON u.user_id = w.user_id
                where w.word_id = ?
                """;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, wordId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String writer = rs.getString("username");
                LocalDate createAt = rs.getDate("create_at").toLocalDate();
                long viewCount = rs.getLong("view_count");
                log.debug("[레포지토리] name = {}", name);
                return FindWordResponse.of(name, description, writer, createAt, viewCount);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    //TODO 동시성 이슈
    public void updateViewCount(Long wordId) {
        String sql = "update word set view_count = view_count + 1 where word_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, wordId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE word";
        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }
}

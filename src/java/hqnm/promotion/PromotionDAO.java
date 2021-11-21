/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hqnm.promotion;

import hnqm.users.UserDAO;
import hnqm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Minh Hoang
 */
public class PromotionDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<PromotionDTO> getAllPromotion() throws Exception {
        List<PromotionDTO> result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT userID,rank FROM Promotion";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    int rank = rs.getInt("rank");

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    UserDAO userDAO = new UserDAO();

                    result.add(new PromotionDTO(userDAO.getUserByID(userID), rank));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public boolean insertPromotion(String UserID, int rank) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Promotion(userID,rank) values(?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, UserID);
                ps.setInt(2, rank);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public void deletePromotion(String userID) throws Exception {
        String query = "delete from Promotion\n"
                + "where userID = ?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
    }

    public boolean updateRank(String userID, int rank) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update Promotion set rank=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, rank);
                ps.setString(2, userID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }
}

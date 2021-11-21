/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.users;

import hnqm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Hoang
 */
public class UserDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public UserDTO checkLogin(String email, String password) throws SQLException, Exception {
        UserDTO result = null;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT userID,userName,password, roleID,phone,photo,status "
                        + "FROM Users "
                        + "WHERE userID = ? and password = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {

                    String name = rs.getString("userName");
                    String roleID = rs.getString("roleID");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    String status = rs.getString("status");
                    result = new UserDTO(email, name, password, roleID, photo, status, phone);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public boolean checkEmail(String email) throws Exception {
        boolean check = false;
        String sql = "select userName from Users where userID=?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public List<UserDTO> getAllUser() throws Exception {

        List<UserDTO> result = null;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "SELECT userID,userName,password, roleID,phone,photo,status,promotionStatus FROM Users";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("userName");
                    String password = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    String status = rs.getString("status");
                    String promotionStatus = rs.getString("promotionStatus");
                    UserDTO user = new UserDTO(userID, name, password, roleID, photo, status, phone, promotionStatus);

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public UserDTO getUserByID(String id) throws Exception {
        UserDTO user = null;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "SELECT userID,userName,password, roleID,phone,photo,status FROM Users WHERE userID=?";

                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("userName");
                    String password = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    String status = rs.getString("status");
                    user = new UserDTO(userID, name, password, roleID, photo, status, phone);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return user;
    }

    public List<UserDTO> searchByRoleID(String roleID) throws Exception {
        List<UserDTO> result = new ArrayList<>();
        String sql = "select userID,userName,password, roleID,phone,photo,status from Users\n"
                + "where roleID=?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, roleID);
            rs = ps.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String name = rs.getString("userName");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String photo = rs.getString("photo");
                String status = rs.getString("status");
                result.add(new UserDTO(userID, name, password, roleID, photo, status, phone));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }

        return result;
    }

    public boolean updateStatus(String userID) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update Users set status=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Inactive");
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

    public boolean updatePromotionStatus(String userID) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update Users set promotionStatus=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Unenable");
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
    public boolean updatePromotionStatus1(String userID) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update Users set promotionStatus=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Enable");
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

    public boolean updateUser(UserDTO user) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update Users set userName=?,password=?,phone=?,photo=?,status=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getPhoto());
                ps.setString(5, user.getStatus());
                ps.setString(6, user.getUserID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean updateUser1(UserDTO user) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update Users set userName=?,phone=?,photo=?,status=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPhone());
                ps.setString(3, user.getPhoto());
                ps.setString(4, user.getStatus());
                ps.setString(5, user.getUserID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean createUser(UserDTO user) throws Exception {

        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert into Users( userID,userName,password, roleID,phone,photo,status,promotionStatus) values(?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserID());
                ps.setString(2, user.getUserName());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getRoleID());
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getPhoto());
                ps.setString(7, user.getStatus());
                ps.setString(8, user.getPromotionStatus());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public List<UserDTO> searchByName(String userName) throws Exception {
        List<UserDTO> result = new ArrayList<>();
        String sql = "select userID,userName,password, roleID,phone,photo,status from Users\n"
                + "where [userName] like ?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + userName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String name = rs.getString("userName");
                String password = rs.getString("password");
                String roleID = rs.getString("roleID");
                String phone = rs.getString("phone");
                String photo = rs.getString("photo");
                String status = rs.getString("status");
                result.add(new UserDTO(userID, name, password, roleID, photo, status, phone));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }

        return result;
    }
}

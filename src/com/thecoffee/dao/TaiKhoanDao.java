/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.TaiKhoan;
import com.thecoffee.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daung
 */
public class TaiKhoanDao extends DAO<TaiKhoan, Integer> {

    String INSERT_SQL = "INSERT INTO TAIKHOAN (EMAIL, MATKHAU, MAXACTHUC, VAITRO) VALUES (?, ?, ?, ? )";
    String UPDATE_SQL = "UPDATE TAIKHOAN SET MATKHAU=?, MAXACTHUC=? WHERE EMAIL=?";
    String DELETE_SQL = "DELETE FROM TAIKHOAN WHERE MATK=?";
    String SELECT_BY_EMAIL = "SELECT * FROM TAIKHOAN WHERE EMAIL = ?";
    String UPDATE_MAXACTHUC = "UPDATE TAIKHOAN SET MAXACTHUC=? WHERE EMAIL=?";
    String SELECT_REMEMBERACC_SQL = "SELECT * FROM REMEMBERACCOUNT";
    String SAVE_SQL = "UPDATE REMEMBERACCOUNT SET EMAIL=?, MATKHAU=?";
    String SELECT_BY_ID = "SELECT * FROM TAIKHOAN WHERE MATK=?";

    @Override
    public void insert(TaiKhoan entity) {
        int generatedKey = XJdbc.insertAndGenerateID(INSERT_SQL, entity.getEmail(), entity.getMatKhau(), entity.getMaXacThuc(), entity.getVaiTro());
        entity.setMaTK(generatedKey);
    }

    @Override
    public void update(TaiKhoan entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getMatKhau(), entity.getMaXacThuc(), entity.getEmail());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public TaiKhoan selectById(Integer id) {
        List<TaiKhoan> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TaiKhoan> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<TaiKhoan> selectBySQL(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TaiKhoan entity = new TaiKhoan();
                entity.setMaTK(rs.getInt("MaTK"));
                entity.setEmail(rs.getString("Email"));
                entity.setMaXacThuc(rs.getString("MaXacThuc"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getString("VaiTro"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TaiKhoan selectByEmail(String Email) {
        List<TaiKhoan> list = this.selectBySQL(SELECT_BY_EMAIL, Email);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void saveCode(String code, String Email) {
        XJdbc.executeUpdate(UPDATE_MAXACTHUC, code, Email);
    }

    public void save(String email, String pass) {
        XJdbc.executeUpdate(SAVE_SQL, email, pass);
    }

    public String[] getSaveAccount() {
        String[] result = {"", ""};
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SELECT_REMEMBERACC_SQL);
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            return result;
        }
        if (list.size() > 0) {
            result[0] = list.get(0);
            result[1] = list.get(1);
        }
        return result;
    }
}

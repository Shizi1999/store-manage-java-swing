/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.DonHangChiTiet;
import com.thecoffee.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daung
 */
public class DonHangChiTietDAO extends DAO<DonHangChiTiet, Integer> {

    String INSERT_SQL = "INSERT INTO DONHANG_SANPHAM (MADH, MASP, SOLUONG, GIA) VALUES (?, ?, ?, ? )";
    String SELECT_BY_MADH = "SELECT DHSP.*, SP.TEN, SP.ANH FROM DONHANG_SANPHAM AS DHSP INNER JOIN SANPHAM AS SP ON DHSP.MASP =SP.MASP WHERE MADH = ?";
    String UPDATE_SOLUONG_SQL = "UPDATE DONHANG_SANPHAM SET SOLUONG=? WHERE MADH=? AND MASP=?";
    String DELETE_SQL = "DELETE FROM DONHANG_SANPHAM WHERE MADH=? AND MASP=?";

    @Override
    public void insert(DonHangChiTiet entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getMaDH(), entity.getMaSP(), entity.getSoLuong(), entity.getGia());
    }

    @Override
    public void update(DonHangChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DonHangChiTiet selectById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DonHangChiTiet> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<DonHangChiTiet> selectBySQL(String sql, Object... args) {
        List<DonHangChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DonHangChiTiet entity = new DonHangChiTiet();
                entity.setMaDH(rs.getInt("DHSP.MaDH"));
                entity.setMaSP(rs.getInt("DHSP.MaSP"));
                entity.setAnh(rs.getString("SP.Anh"));
                entity.setTenSP(rs.getString("SP.Ten"));
                entity.setSoLuong(rs.getInt("DHSP.SoLuong"));
                entity.setGia(rs.getDouble("DHSP.Gia"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DonHangChiTiet> selectByMaDH(int MaDH) {
        return this.selectBySQL(SELECT_BY_MADH, MaDH);
    }

    public void updateSoluong(DonHangChiTiet dhct) {
        XJdbc.executeUpdate(UPDATE_SOLUONG_SQL, dhct.getSoLuong(), dhct.getMaDH(), dhct.getMaSP());
    }

    public void deleteByDonHangChiTiet(DonHangChiTiet dhct) {
        XJdbc.executeUpdate(DELETE_SQL, dhct.getMaDH(), dhct.getMaSP());
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.KhachHang;
import com.thecoffee.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daung
 */
public class KhachHangDAO extends DAO<KhachHang, Integer> {

    String INSERT_SQL = "INSERT INTO KHACHHANG (TEN, SDT, TICHDIEM) VALUES (?, ?, ?)";
    String UPDATE_SQL = "UPDATE KHACHHANG SET TEN=?, TICHDIEM=? WHERE MAKH=?";
    String DELETE_SQL = "DELETE FROM KHACHHANG WHERE MAKH=?";
    String SELECT_BY_ID = "SELECT * FROM KHACHHANG WHERE MAKH = ?";
    String SELECT_BY_PHONE_NUMBER = "SELECT * FROM KHACHHANG WHERE SDT =? AND TEN != \"Admin\"";
    String SELECT_ALL = "SELECT * FROM KHACHHANG WHERE TEN != \"Admin\" ";
    String THONGKE = "SELECT KH.*, COUNT(*) AS TONGDONHANG, SUM(DH.THANHTOAN) AS TONGCHITIEU FROM KHACHHANG AS KH INNER JOIN DONHANG AS DH ON KH.MAKH = DH.MAKH WHERE KH.TEN != \"Admin\" GROUP BY KH.MAKH ";

    @Override
    public void insert(KhachHang entity) {
        int generateId = XJdbc.insertAndGenerateID(INSERT_SQL, entity.getTen(), entity.getSdt(), entity.getTichDiem());
        entity.setMaKH(generateId);
    }

    @Override
    public void update(KhachHang entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getTen(), entity.getTichDiem(), entity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public KhachHang selectById(Integer id) {
        List<KhachHang> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    @Override
    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setTen(rs.getString("Ten"));
                entity.setSdt(rs.getString("Sdt"));
                entity.setTichDiem(rs.getInt("TichDiem"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhachHang> thongKeTongDonHang() {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(THONGKE);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getInt("KH.MaKH"));
                entity.setTen(rs.getString("KH.Ten"));
                entity.setSdt(rs.getString("KH.Sdt"));
                entity.setTichDiem(rs.getInt("TichDiem"));
                entity.setTongDonHang(rs.getInt("TongDonHang"));
                entity.setTongChiTieu(rs.getDouble("TongChiTieu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public KhachHang selectByPhone(String phone) {
        List<KhachHang> list = this.selectBySQL(SELECT_BY_PHONE_NUMBER, phone);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.NhanVien;
import com.thecoffee.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daung
 */
public class NhanVienDAO extends DAO<NhanVien, Integer> {

    String INSERT_SQL = "INSERT INTO NHANVIEN (MANV, TEN, SDT, DIACHI, CHUCVU, LUONGTHEONGAY, GIOITINH) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE NHANVIEN SET TEN=?, SDT=?,DIACHI=?,CHUCVU=?, LUONGTHEONGAY=?, GIOITINH=? WHERE MANV=?";
    String DELETE_SQL = "DELETE FROM NHANVIEN WHERE MANV=?";
    String SELECT_BY_ID = "SELECT * FROM NHANVIEN WHERE MANV = ?";
    String SELECT_ALL = "SELECT * FROM NHANVIEN WHERE MANV != 1";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getMaNV(), entity.getTen(), entity.getSdt(), entity.getDiaChi(), entity.getChucvu(), entity.getLuongTheoNgay(), entity.isGioiTinh());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getTen(), entity.getSdt(), entity.getDiaChi(), entity.getChucvu(), entity.getLuongTheoNgay(),entity.isGioiTinh(), entity.getMaNV());

    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public NhanVien selectById(Integer id) {
        List<NhanVien> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0); 
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getInt("MaNV"));
                entity.setTen(rs.getString("Ten"));
                entity.setChucvu(rs.getString("ChucVu"));
                entity.setLuongTheoNgay(rs.getDouble("LuongTheoNgay"));
                entity.setSdt(rs.getString("Sdt"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

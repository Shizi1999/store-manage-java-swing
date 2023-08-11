/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.DonHang;
import com.thecoffee.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daung
 */
public class DonHangDAO extends DAO<DonHang, Integer> {

    String INSERT_SQL = "INSERT INTO DONHANG (MANV, MAKH, MAVOUCHER, GIAMGIA, DIEM, THANHTOAN, NGAYTAO) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String SELECT_ALL = "SELECT DH.*, NV.TEN, KH.* FROM DONHANG AS DH INNER JOIN NHANVIEN AS NV ON NV.MANV = DH.MANV INNER JOIN KHACHHANG AS KH ON KH.MAKH=DH.MAKH ORDER BY NGAYTAO DESC";
    String SELECT_BY_TIME = "SELECT DH.*, NV.TEN, KH.* FROM DONHANG AS DH INNER JOIN NHANVIEN AS NV ON NV.MANV = DH.MANV INNER JOIN KHACHHANG AS KH ON KH.MAKH=DH.MAKH WHERE DAY(DH.NGAYTAO)=? AND MONTH(DH.NGAYTAO)=? AND YEAR(DH.NGAYTAO)=? ORDER BY NGAYTAO DESC";
    String UPDATE = "UPDATE DONHANG SET GIAMGIA=?, THANHTOAN=? WHERE MADH=?";
    String THONG_KE_DON_HANG = "SELECT * FROM DONHANG WHERE YEAR(NGAYTAO)=? AND MONTH(NGAYTAO)=?";
    String SELECT_BY_MAKH = "SELECT DH.*, NV.TEN, KH.* FROM DONHANG AS DH INNER JOIN NHANVIEN AS NV ON NV.MANV = DH.MANV INNER JOIN KHACHHANG AS KH ON KH.MAKH=DH.MAKH WHERE KH.MAKH = ? ORDER BY NGAYTAO DESC";
    String DELETE_SQL = "DELETE FROM DONHANG WHERE MADH=?";
    String SELECT_IN_RANGE_TIME = "SELECT DH.*, NV.TEN, KH.* FROM DONHANG AS DH INNER JOIN NHANVIEN AS NV ON NV.MANV = DH.MANV INNER JOIN KHACHHANG AS KH ON KH.MAKH=DH.MAKH WHERE DH.NGAYTAO BETWEEN ? AND ?";

    @Override
    public void insert(DonHang entity) {
        int generatedKey = XJdbc.insertAndGenerateID(INSERT_SQL, entity.getMaNV(), entity.getMaKH(), entity.getMaVoucher(), entity.getGiamGia(), entity.getDiem(), entity.getThanhToan(), entity.getNgayTao());
        entity.setMaDH(generatedKey);
    }

    @Override
    public void update(DonHang entity) {
        XJdbc.executeUpdate(UPDATE, entity.getGiamGia(), entity.getThanhToan(), entity.getMaDH());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public DonHang selectById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DonHang> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    @Override
    protected List<DonHang> selectBySQL(String sql, Object... args) {
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DonHang entity = new DonHang();
                entity.setMaKH(rs.getInt("DH.MaKH"));
                entity.setMaDH(rs.getInt("DH.MaDH"));
                entity.setMaNV(rs.getInt("DH.MaNV"));
                entity.setGiamGia(rs.getInt("DH.GiamGia"));
                entity.setMaVoucher(rs.getString("DH.MaVoucher"));
                entity.setNgayTao(rs.getDate("DH.NgayTao"));
                entity.setTenKH(rs.getString("KH.Ten"));
                entity.setThanhToan(rs.getDouble("DH.ThanhToan"));
                entity.setDiem(rs.getInt("DH.Diem"));
                entity.setTenNV(rs.getString("NV.Ten"));
                entity.setSdt(rs.getString("KH.SDT"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DonHang> thongKeDonHang(int year, int month) {
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(THONG_KE_DON_HANG, year, month);
            while (rs.next()) {
                DonHang entity = new DonHang();
                entity.setThanhToan(rs.getDouble("ThanhToan"));
                entity.setMaVoucher(rs.getString("MaVoucher"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DonHang> selectByMaKH(int maKh) {
        return this.selectBySQL(SELECT_BY_MAKH, maKh);
    }

    public List<DonHang> selectByTime(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String time = sdf.format(d);
        String[] timeDetail = time.split(":");
        return this.selectBySQL(SELECT_BY_TIME, timeDetail[0], timeDetail[1], timeDetail[2]);
    }

    public List<DonHang> selectInRangeTime(Date start, Date end) {
        return this.selectBySQL(SELECT_IN_RANGE_TIME, start, end);
    }
}

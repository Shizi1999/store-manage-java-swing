/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.SanPham;
import com.thecoffee.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daung
 */
public class SanPhamDAO extends DAO<SanPham, Integer> {

    String INSERT_SQL = "INSERT INTO SANPHAM (MALOAI, TEN, DONVITINH, ANH, GIA, DELETED) VALUES (?, ?, ?, ?, ? ,0)";
    String UPDATE_SQL = "UPDATE SANPHAM SET MALOAI=?, TEN=?, DONVITINH=?, ANH=?, GIA=? WHERE MASP=?";
    String DELETE_SQL = "UPDATE SANPHAM SET DELETED=1 WHERE MASP=?";
    String SELECT_BY_ID = "SELECT SP.*, LSP.TENLOAI FROM SANPHAM AS SP INNER JOIN LOAISANPHAM AS LSP ON SP.MALOAI=LSP.MALOAI WHERE SP.MASP = ?";
    String SELECT_ALL = "SELECT * FROM SANPHAM AS SP INNER JOIN LOAISANPHAM AS LSP ON SP.MAlOAI=LSP.MAlOAI WHERE SP.DELETED = 0";
    String COUNT_BY_CATEGORY = "SELECT COUNT(*) AS TOTAL FROM SANPHAM WHERE MALOAI = ? AND DELETED = 0";
    String SELECT_BY_CATEGORY_WITH_PAGINATION = "SELECT SP.*,LSP.TENLOAI FROM SANPHAM AS SP INNER JOIN LOAISANPHAM AS LSP ON SP.MALOAI=LSP.MALOAI WHERE SP.DELETED =0 AND SP.MALOAI=? LIMIT ?, ?";
    String SELECT_ALL_DELETED = "SELECT * FROM SANPHAM AS SP INNER JOIN LOAISANPHAM AS LSP ON SP.MAlOAI=LSP.MAlOAI WHERE SP.DELETED = 1";
    String SEARCH_SQL = "SELECT SP.*, LSP.TENLOAI FROM SANPHAM AS SP INNER JOIN LOAISANPHAM AS LSP ON SP.MALOAI=LSP.MALOAI WHERE SP.TEN LIKE ? OR SP.MASP=? ";
    String SELECT_BY_MASP = "SELECT SP.*,LSP.TENLOAI FROM SANPHAM AS SP INNER JOIN LOAISANPHAM AS LSP ON SP.MALOAI=LSP.MALOAI WHERE SP.DELETED =0 AND SP.MALOAI=?";
    String RESTORE_SQL = "UPDATE SANPHAM SET DELETED=0 WHERE MASP=?";

    @Override
    public void insert(SanPham entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getMaLoai(), entity.getTen(), entity.getDonViTinh(), entity.getAnh(), entity.getGia());
    }

    @Override
    public void update(SanPham entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getMaLoai(), entity.getTen(), entity.getDonViTinh(), entity.getAnh(), entity.getGia(), entity.getMaSP());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public SanPham selectById(Integer id) {
        List<SanPham> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    @Override
    protected List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham();
                entity.setMaSP(rs.getInt("SP.MASP"));
                entity.setMaLoai(rs.getInt("SP.MALOAI"));
                entity.setTen(rs.getString("SP.TEN"));
                entity.setTenLoai(rs.getString("LSP.TENLOAI"));
                entity.setAnh(rs.getString("SP.ANH"));
                entity.setDonViTinh(rs.getString("SP.DONVITINH"));
                entity.setGia(rs.getDouble("GIA"));
                entity.setDeleted(rs.getBoolean("DELETED"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalByCategory(int maLoai) {
        int total = 0;
        try {
            ResultSet rs = XJdbc.executeQuery(COUNT_BY_CATEGORY, maLoai);
            while (rs.next()) {
                total = rs.getInt("TOTAL");
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            return total;
        }
        return total;
    }

    public List<SanPham> selectByLoaiSanPhamWithPaginatio(int MaLoai, int Skip, int Limit) {
        return this.selectBySQL(SELECT_BY_CATEGORY_WITH_PAGINATION, MaLoai, Skip, Limit);
    }

    public List<SanPham> selectAllDeleted() {
        return this.selectBySQL(SELECT_ALL_DELETED);
    }

    public List<SanPham> search(String searchValue) {
        return this.selectBySQL(SEARCH_SQL,"%" + searchValue + "%", searchValue);
    }

    public List<SanPham> selectByLoaiSanPham(int MaLoai) {
        return this.selectBySQL(SELECT_BY_MASP, MaLoai);
    }

    public void restore(int id) {
        XJdbc.executeUpdate(RESTORE_SQL, id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.Voucher;
import com.thecoffee.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daung
 */
public class VoucherDAO extends DAO<Voucher, String> {

    String INSERT_SQL = "INSERT INTO VOUCHER (MAVOUCHER, TILE, SOLUONG, NGAYBATDAU, NGAYKETTHUC) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE VOUCHER SET TILE=?, SOLUONG=?, NGAYBATDAU=?, NGAYKETTHUC=? WHERE MAVOUCHER=?";
    String DELETE_SQL = "DELETE FROM VOUCHER WHERE MAVOUCHER=?";
    String SELECT_BY_ID = "SELECT * FROM VOUCHER WHERE MAVOUCHER = ?";
    String SELECT_ALL = "SELECT * FROM VOUCHER";
    String USE_VOUCHER = "UPDATE VOUCHER SET SOLUONG=? WHERE MAVOUCHER=?";

    @Override
    public void insert(Voucher entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getMaVoucher(), entity.getTiLe(), entity.getSoLuong(), entity.getNgayBatDau(), entity.getNgayKetThuc());
    }

    @Override
    public void update(Voucher entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getTiLe(), entity.getSoLuong(), entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.getMaVoucher());

    }

    @Override
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public Voucher selectById(String id) {
        List<Voucher> list = this.selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Voucher> selectAll() {
        return this.selectBySQL(SELECT_ALL);
    }

    @Override
    protected List<Voucher> selectBySQL(String sql, Object... args) {
        List<Voucher> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Voucher entity = new Voucher();
                entity.setMaVoucher(rs.getString("MaVoucher"));
                entity.setTiLe(rs.getInt("TiLe"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setNgayBatDau(rs.getDate("NgayBatDau"));
                entity.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized public void useVoucher(String maVoucher) {
        Voucher vc = this.selectById(maVoucher);
        if (vc != null) {
            XJdbc.executeUpdate(USE_VOUCHER, vc.getSoLuong() - 1, maVoucher);
        }
    }

}

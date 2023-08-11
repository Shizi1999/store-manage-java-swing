/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author daung
 */
public class ThongKeDAO {

    private String SELECT_DOANH_THU = "SELECT SUM(THANHTOAN) FROM DONHANG WHERE YEAR(NGAYTAO)=? GROUP BY MONTH(NGAYTAO)";
    private String THONG_KE_THEO_THOI_GIAN = "SELECT SUM(THANHTOAN) AS TONG, MIN(THANHTOAN) AS THAPNHAT, MAX(THANHTOAN) AS CAONHAT, AVG(THANHTOAN) AS TRUNGBINH FROM DONHANG WHERE NGAYTAO BETWEEN ? AND ?";

    public List<Double> layDoanhThuTheoNam(int year) {
        List<Double> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SELECT_DOANH_THU, year);
            while (rs.next()) {
                list.add(rs.getDouble(1));
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Double> thongKeDoanhThuTheoThoiGian(Date start, Date end) {
        List<Double> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(THONG_KE_THEO_THOI_GIAN, start, end);
            while (rs.next()) {
                list.add(rs.getDouble("Tong"));
                list.add(rs.getDouble("CaoNhat"));
                list.add(rs.getDouble("ThapNhat"));
                list.add(rs.getDouble("TrungBinh"));
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.dao;

import com.thecoffee.entity.ChamCong;
import com.thecoffee.utils.XJdbc;
import java.sql.Timestamp;
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
public class ChamCongDAO {

    String SELECT_SQL = "SELECT * FROM CHAMCONG WHERE MANV=? AND YEAR(THOIGIAN)=? AND MONTH(THOIGIAN)=? ORDER BY THOIGIAN";
    String INSERT_SQL = "INSERT INTO CHAMCONG(MANV, THOIGIAN) VALUES (?,?)";
    String KIEM_TRA_CHAM_CONG = "SELECT * FROM CHAMCONG WHERE MANV=? AND DAY(THOIGIAN)= ? AND MONTH(THOIGIAN)=? AND YEAR(THOIGIAN)=?";
    String DELETE_SQL = "DELETE FROM CHAMCONG WHERE MANV=?";
    String DELETE_NGAY_CONG = "DELETE FROM CHAMCONG WHERE MANV=? AND DAY(THOIGIAN)= ? AND MONTH(THOIGIAN)=? AND YEAR(THOIGIAN)=?";

    public List<ChamCong> selectByTime(int MaNV, int Year, int Month) {
        List<ChamCong> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SELECT_SQL, MaNV, Year, Month);
            while (rs.next()) {
                ChamCong entity = new ChamCong();
                entity.setMaNV(rs.getInt("MaNV"));
                Timestamp ts = rs.getTimestamp("ThoiGian");
                entity.setThoiGian(new Date(ts.getTime()));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void chamCong(int id) {
        XJdbc.executeUpdate(INSERT_SQL, id, new Date());
    }

    public void chamCong(int id, Date d) {
        XJdbc.executeUpdate(INSERT_SQL, id, d);
    }

    public void delete(int id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    public void deleteNgayCong(int id, Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String time = sdf.format(d);
        String[] timeDetail = time.split(":");
        XJdbc.executeUpdate(DELETE_NGAY_CONG, id, timeDetail[0], timeDetail[1], timeDetail[2]);
    }

    public boolean kiemTraChamCong(int id) {
        boolean flag = false;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String time = sdf.format(now);
        String[] timeDetail = time.split(":");
        try {
            ResultSet rs = XJdbc.executeQuery(KIEM_TRA_CHAM_CONG, id, timeDetail[0], timeDetail[1], timeDetail[2]);
            while (rs.next()) {
                flag = true;
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            flag = true;
        }
        return flag;
    }

    public boolean kiemTraChamCong(int id, Date now) {
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String time = sdf.format(now);
        String[] timeDetail = time.split(":");
        try {
            ResultSet rs = XJdbc.executeQuery(KIEM_TRA_CHAM_CONG, id, timeDetail[0], timeDetail[1], timeDetail[2]);
            while (rs.next()) {
                flag = true;
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            flag = true;
        }
        return flag;
    }

}

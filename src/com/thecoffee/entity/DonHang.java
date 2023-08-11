/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.entity;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author daung
 */
@Data
public class DonHang {

    private int MaDH, MaKH, MaNV, giamGia, diem;
    private String maVoucher, tenNV, tenKH, sdt;
    private double thanhToan;
    private Date ngayTao;

    public Object[] toRow() {
        return new Object[]{this.MaDH, this.tenKH, this.thanhToan,this.giamGia, this.maVoucher, this.tenNV};
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.entity;

import lombok.Data;

/**
 *
 * @author daung
 */
@Data

public class NhanVien {

    private int maNV;
    private String ten, sdt, diaChi, chucvu;
    private double luongTheoNgay;
    private boolean gioiTinh;
    public Object[] toRow() {
        return new Object[]{this.ten, this.sdt, this.diaChi, this.chucvu, this.gioiTinh ? "Nam" : "Nữ", this.luongTheoNgay};
    }
}

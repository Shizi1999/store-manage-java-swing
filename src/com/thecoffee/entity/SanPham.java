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
public class SanPham {

    private int MaSP, MaLoai, soluong;
    private String ten, tenLoai, donViTinh, Anh;
    private double gia;
    private boolean deleted;

    public Object[] toRow() {
        return new Object[]{this.MaSP, this.ten, this.tenLoai, this.gia, this.donViTinh, this.Anh};
    }

    public Object[] toRowOrder() {
        return new Object[]{this.ten, this.gia, this.soluong, this.soluong * this.gia};
    }

}

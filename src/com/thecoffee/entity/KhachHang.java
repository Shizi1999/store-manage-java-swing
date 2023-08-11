/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.entity;

import com.thecoffee.utils.XConverter;
import lombok.Data;

/**
 *
 * @author daung
 */
@Data
public class KhachHang {

    private int maKH, tongDonHang, tichDiem;
    private String ten, sdt;
    private double tongChiTieu;

    public Object[] toRow() {
        return new Object[]{this.ten, this.sdt, this.tichDiem, XConverter.convertNumberToString(this.tongChiTieu), this.tongDonHang};
    }
}

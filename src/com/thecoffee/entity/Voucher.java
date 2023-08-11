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
public class Voucher {

    private String maVoucher;
    private int tiLe, soLuong;
    private Date ngayBatDau, ngayKetThuc;

    public boolean isNotStart() {
        Date now = new Date();
        return ngayBatDau.compareTo(now) >= 0;
    }

    public boolean isEnd() {
        Date now = new Date();
        return ngayKetThuc.compareTo(now) <= 0;
    }

    public boolean isOver() {
        return this.soLuong <= 0;
    }
}

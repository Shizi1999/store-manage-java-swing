/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.resource;

import com.thecoffee.entity.KhachHang;
import com.thecoffee.entity.NhanVien;
import com.thecoffee.entity.TaiKhoan;

/**
 *
 * @author daung
 */
public class Auth {

    public static int userID = 1;
    public static String role = "Nhân Viên";
    public static TaiKhoan tk = null;
    public static String userName = "";

    public static void setData(TaiKhoan taikhoan) {
        userID = taikhoan.getMaTK();
        role = taikhoan.getVaiTro();
    }

}

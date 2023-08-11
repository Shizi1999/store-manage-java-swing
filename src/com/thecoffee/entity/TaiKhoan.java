/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author daung
 */
@Data
@NoArgsConstructor
public class TaiKhoan {
    private int maTK;
    private String email, matKhau, maXacThuc, vaiTro;
}

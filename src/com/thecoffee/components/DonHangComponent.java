/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.components;

import com.thecoffee.entity.DonHangChiTiet;
import com.thecoffee.utils.XImage;
import javax.swing.JLabel;
import lombok.AllArgsConstructor;

/**
 *
 * @author daung
 */
@AllArgsConstructor
public class DonHangComponent {
    private JLabel image;
    private JLabel price;
    private JLabel title;
    private JLabel number;


    public void show(DonHangChiTiet dhct) {
        XImage.setLabelIcon(image, dhct.getAnh());
        price.setText(dhct.getGia() + "");
        title.setText(dhct.getTenSP());
        number.setText(dhct.getSoLuong() + "");
    }

    public void clear() {
        XImage.setLabelIcon(image, "");
        price.setText("");
        title.setText("");
        number.setText("");
    }
}

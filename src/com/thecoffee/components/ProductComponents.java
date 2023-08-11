/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.components;

import com.thecoffee.entity.SanPham;
import com.thecoffee.utils.XImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.AllArgsConstructor;

/**
 *
 * @author daung
 */
@AllArgsConstructor
public class ProductComponents {

    private JPanel panel;
    private JLabel image;
    private JLabel title;
    private JLabel price;

    public void show(SanPham sp) {
        XImage.setLabelIcon(image, sp.getAnh());
        title.setText(sp.getTen());
        price.setText(sp.getGia() + "");
    }

    public void clear() {
        XImage.setLabelIcon(image, "");
        this.title.setText("");
        this.price.setText( "");
    }
}

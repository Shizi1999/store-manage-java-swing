/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.components;

import com.thecoffee.utils.XImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author daung
 */
public class GioHangComponent {

    private JPanel wrapper;
    private JLabel image;
    private JLabel price;
    private JLabel title;
    private JLabel number;

    public GioHangComponent(JPanel wrapper, JLabel image, JLabel price, JLabel title, JLabel number, JLabel tang, JLabel giam) {
        this.wrapper = wrapper;
        this.image = image;
        this.price = price;
        this.title = title;
        this.number = number;

    }

    public void show() {

    }

    public void clear() {

    }
}

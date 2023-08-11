/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.components;

import com.thecoffee.utils.XDate;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author daung
 */
@Data
@AllArgsConstructor
public class ChamCongComponent {

    private JLabel time;
    private JLabel day;
    private JPanel wrapper;

    public void show(Date d) {

        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss");
        
        time.setText(sdf.format(d));
        if (XDate.diLamMuon(d)) {
            wrapper.setBackground(new Color(255, 250, 215));
        } else {
            wrapper.setBackground(new Color(255, 159, 159));
        }
    }

    public void clear() {
        time.setText("");
        wrapper.setBackground(new Color(242, 242, 242));
    }
}

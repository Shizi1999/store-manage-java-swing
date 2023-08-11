/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.thecoffee.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.thecoffee.components.ChamCongComponent;
import com.thecoffee.components.FormField;
import com.thecoffee.components.InputField;
import com.thecoffee.dao.ChamCongDAO;
import com.thecoffee.dao.NhanVienDAO;
import com.thecoffee.entity.ChamCong;
import com.thecoffee.entity.NhanVien;
import com.thecoffee.resource.Auth;
import com.thecoffee.resource.Constant;
import com.thecoffee.utils.MsgBox;
import com.thecoffee.utils.XDate;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author daung
 */
public class NhanVienUI extends javax.swing.JDialog {

    private static NhanVien nhanvien = null;
    private int month = 1;
    private int year = 2022;
    JLabel[] times;
    JLabel[] days;
    JPanel[] wrappers;
    List<ChamCong> list = new ArrayList<>();
    List<ChamCongComponent> listComponents = new ArrayList<>();
    private ChamCongDAO ccdao = new ChamCongDAO();
    private NhanVienDAO nvdao = new NhanVienDAO();
    private static QuanLiNhanVienJDiaLog qlnv;

    /**
     * Creates new form NhanVienUI
     */
    public NhanVienUI(java.awt.Frame parent, boolean modal, NhanVien nv, QuanLiNhanVienJDiaLog qlnv) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.qlnv = qlnv;
        this.nhanvien = nv;
        fillForm();
        setPermission();
        initChamCongComponent();
        initCombobox();
        hienThiChamCong();
        initEvent();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\appIcon.png"));
    }

    public NhanVienUI(QuanLiNhanVienJDiaLog qlnv, NhanVien nv) {
        initComponents();
        setLocationRelativeTo(null);
        this.nhanvien = nv;
        fillForm();
        setPermission();
        initChamCongComponent();
        initCombobox();
        hienThiChamCong();
        initEvent();
    }

    void initEvent() {
        if (Auth.role.equals(Constant.VAI_TRO_ADMIN)) {
            JCheckBox chk = new JCheckBox();
            chk.setText("Đi muộn");
            AdminPanel.add(chk);
            for (int i = 0; i < wrappers.length; i++) {
                JPanel panel = wrappers[i];
                panel.setName(i + "");
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int index = Integer.parseInt(panel.getName());
                        String day = days[index].getText();
                        if (!day.trim().equals("")) {
                            try {
                                int date = Integer.parseInt(day);
                                int month = Integer.parseInt((String) cboMonth.getSelectedItem());
                                String year = (String) cboYear.getSelectedItem();
                                String thang = month + "";
                                String ngay = day + "";
                                String hour = " 06:00:00";
                                if (chk.isSelected()) {
                                    hour = " 08:00:00";
                                }
                                if (month < 10) {
                                    thang = "0" + month;
                                }
                                if (date < 10) {
                                    ngay = "0" + day;
                                }
                                String time = ngay + "-" + thang + "-" + year + hour;
                                Date d = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss").parse(time);
                                if (!ccdao.kiemTraChamCong(nhanvien.getMaNV(), d)) {
                                    String status = chk.isSelected() ? "muộn" : "đúng giờ";
                                    if (MsgBox.confirm(null, "Xác nhận nhân viên đi làm " + status)) {
                                        ccdao.chamCong(nhanvien.getMaNV(), d);
                                        MsgBox.alert(null, "Thành công");
                                        hienThiChamCong();
                                    }
                                } else {
                                    if (MsgBox.confirm(null, "Xác nhận xoá ngày công?")) {
                                        ccdao.deleteNgayCong(nhanvien.getMaNV(), d);
                                        MsgBox.alert(null, "Thành công");
                                        clearComponent(index);
                                    }
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(NhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                );
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        wrapper1 = new javax.swing.JPanel();
        day1 = new javax.swing.JLabel();
        time1 = new javax.swing.JLabel();
        wrapper2 = new javax.swing.JPanel();
        day2 = new javax.swing.JLabel();
        time2 = new javax.swing.JLabel();
        wrapper3 = new javax.swing.JPanel();
        day3 = new javax.swing.JLabel();
        time3 = new javax.swing.JLabel();
        wrapper4 = new javax.swing.JPanel();
        day4 = new javax.swing.JLabel();
        time4 = new javax.swing.JLabel();
        wrapper5 = new javax.swing.JPanel();
        day5 = new javax.swing.JLabel();
        time5 = new javax.swing.JLabel();
        wrapper6 = new javax.swing.JPanel();
        day6 = new javax.swing.JLabel();
        time6 = new javax.swing.JLabel();
        wrapper7 = new javax.swing.JPanel();
        day7 = new javax.swing.JLabel();
        time7 = new javax.swing.JLabel();
        wrapper8 = new javax.swing.JPanel();
        day8 = new javax.swing.JLabel();
        time8 = new javax.swing.JLabel();
        wrapper9 = new javax.swing.JPanel();
        day9 = new javax.swing.JLabel();
        time9 = new javax.swing.JLabel();
        wrapper10 = new javax.swing.JPanel();
        day10 = new javax.swing.JLabel();
        time10 = new javax.swing.JLabel();
        wrapper11 = new javax.swing.JPanel();
        day11 = new javax.swing.JLabel();
        time11 = new javax.swing.JLabel();
        wrapper12 = new javax.swing.JPanel();
        day12 = new javax.swing.JLabel();
        time12 = new javax.swing.JLabel();
        wrapper13 = new javax.swing.JPanel();
        day13 = new javax.swing.JLabel();
        time13 = new javax.swing.JLabel();
        wrapper14 = new javax.swing.JPanel();
        day14 = new javax.swing.JLabel();
        time14 = new javax.swing.JLabel();
        wrapper15 = new javax.swing.JPanel();
        day15 = new javax.swing.JLabel();
        time15 = new javax.swing.JLabel();
        wrapper16 = new javax.swing.JPanel();
        day16 = new javax.swing.JLabel();
        time16 = new javax.swing.JLabel();
        wrapper17 = new javax.swing.JPanel();
        day17 = new javax.swing.JLabel();
        time17 = new javax.swing.JLabel();
        wrapper18 = new javax.swing.JPanel();
        day18 = new javax.swing.JLabel();
        time18 = new javax.swing.JLabel();
        wrapper19 = new javax.swing.JPanel();
        day19 = new javax.swing.JLabel();
        time19 = new javax.swing.JLabel();
        wrapper20 = new javax.swing.JPanel();
        day20 = new javax.swing.JLabel();
        time20 = new javax.swing.JLabel();
        wrapper21 = new javax.swing.JPanel();
        day21 = new javax.swing.JLabel();
        time21 = new javax.swing.JLabel();
        wrapper22 = new javax.swing.JPanel();
        day22 = new javax.swing.JLabel();
        time22 = new javax.swing.JLabel();
        wrapper23 = new javax.swing.JPanel();
        day23 = new javax.swing.JLabel();
        time23 = new javax.swing.JLabel();
        wrapper24 = new javax.swing.JPanel();
        day24 = new javax.swing.JLabel();
        time24 = new javax.swing.JLabel();
        wrapper25 = new javax.swing.JPanel();
        day25 = new javax.swing.JLabel();
        time25 = new javax.swing.JLabel();
        wrapper26 = new javax.swing.JPanel();
        day26 = new javax.swing.JLabel();
        time26 = new javax.swing.JLabel();
        wrapper27 = new javax.swing.JPanel();
        day27 = new javax.swing.JLabel();
        time27 = new javax.swing.JLabel();
        wrapper28 = new javax.swing.JPanel();
        day28 = new javax.swing.JLabel();
        time28 = new javax.swing.JLabel();
        wrapper29 = new javax.swing.JPanel();
        day29 = new javax.swing.JLabel();
        time29 = new javax.swing.JLabel();
        wrapper30 = new javax.swing.JPanel();
        day30 = new javax.swing.JLabel();
        time30 = new javax.swing.JLabel();
        wrapper31 = new javax.swing.JPanel();
        day31 = new javax.swing.JLabel();
        time31 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboMonth = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtLuongThang = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        input1 = new javax.swing.JTextField();
        error1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        input2 = new javax.swing.JTextField();
        error2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        input3 = new javax.swing.JTextField();
        error3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        input4 = new javax.swing.JTextField();
        error4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        input5 = new javax.swing.JTextField();
        error5 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        AdminPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý nhân viên");

        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setLayout(new java.awt.GridLayout(6, 6, 4, 4));

        wrapper1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper1Layout = new javax.swing.GroupLayout(wrapper1);
        wrapper1.setLayout(wrapper1Layout);
        wrapper1Layout.setHorizontalGroup(
            wrapper1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper1Layout.setVerticalGroup(
            wrapper1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper1);

        wrapper2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper2Layout = new javax.swing.GroupLayout(wrapper2);
        wrapper2.setLayout(wrapper2Layout);
        wrapper2Layout.setHorizontalGroup(
            wrapper2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper2Layout.setVerticalGroup(
            wrapper2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper2);

        wrapper3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper3Layout = new javax.swing.GroupLayout(wrapper3);
        wrapper3.setLayout(wrapper3Layout);
        wrapper3Layout.setHorizontalGroup(
            wrapper3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper3Layout.setVerticalGroup(
            wrapper3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper3);

        wrapper4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper4Layout = new javax.swing.GroupLayout(wrapper4);
        wrapper4.setLayout(wrapper4Layout);
        wrapper4Layout.setHorizontalGroup(
            wrapper4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper4Layout.setVerticalGroup(
            wrapper4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper4);

        wrapper5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper5Layout = new javax.swing.GroupLayout(wrapper5);
        wrapper5.setLayout(wrapper5Layout);
        wrapper5Layout.setHorizontalGroup(
            wrapper5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper5Layout.setVerticalGroup(
            wrapper5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper5);

        wrapper6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper6Layout = new javax.swing.GroupLayout(wrapper6);
        wrapper6.setLayout(wrapper6Layout);
        wrapper6Layout.setHorizontalGroup(
            wrapper6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper6Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper6Layout.setVerticalGroup(
            wrapper6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper6);

        wrapper7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper7Layout = new javax.swing.GroupLayout(wrapper7);
        wrapper7.setLayout(wrapper7Layout);
        wrapper7Layout.setHorizontalGroup(
            wrapper7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper7Layout.setVerticalGroup(
            wrapper7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper7);

        wrapper8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper8Layout = new javax.swing.GroupLayout(wrapper8);
        wrapper8.setLayout(wrapper8Layout);
        wrapper8Layout.setHorizontalGroup(
            wrapper8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper8Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper8Layout.setVerticalGroup(
            wrapper8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper8);

        wrapper9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper9Layout = new javax.swing.GroupLayout(wrapper9);
        wrapper9.setLayout(wrapper9Layout);
        wrapper9Layout.setHorizontalGroup(
            wrapper9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper9Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper9Layout.setVerticalGroup(
            wrapper9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper9);

        wrapper10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper10Layout = new javax.swing.GroupLayout(wrapper10);
        wrapper10.setLayout(wrapper10Layout);
        wrapper10Layout.setHorizontalGroup(
            wrapper10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper10Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper10Layout.setVerticalGroup(
            wrapper10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper10);

        wrapper11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper11Layout = new javax.swing.GroupLayout(wrapper11);
        wrapper11.setLayout(wrapper11Layout);
        wrapper11Layout.setHorizontalGroup(
            wrapper11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper11Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper11Layout.setVerticalGroup(
            wrapper11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper11);

        wrapper12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper12Layout = new javax.swing.GroupLayout(wrapper12);
        wrapper12.setLayout(wrapper12Layout);
        wrapper12Layout.setHorizontalGroup(
            wrapper12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper12Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper12Layout.setVerticalGroup(
            wrapper12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper12);

        wrapper13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper13Layout = new javax.swing.GroupLayout(wrapper13);
        wrapper13.setLayout(wrapper13Layout);
        wrapper13Layout.setHorizontalGroup(
            wrapper13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper13Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper13Layout.setVerticalGroup(
            wrapper13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper13);

        wrapper14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper14Layout = new javax.swing.GroupLayout(wrapper14);
        wrapper14.setLayout(wrapper14Layout);
        wrapper14Layout.setHorizontalGroup(
            wrapper14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper14Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper14Layout.setVerticalGroup(
            wrapper14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper14);

        wrapper15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper15Layout = new javax.swing.GroupLayout(wrapper15);
        wrapper15.setLayout(wrapper15Layout);
        wrapper15Layout.setHorizontalGroup(
            wrapper15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper15Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper15Layout.setVerticalGroup(
            wrapper15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper15);

        wrapper16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper16Layout = new javax.swing.GroupLayout(wrapper16);
        wrapper16.setLayout(wrapper16Layout);
        wrapper16Layout.setHorizontalGroup(
            wrapper16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper16Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper16Layout.setVerticalGroup(
            wrapper16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper16);

        wrapper17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper17Layout = new javax.swing.GroupLayout(wrapper17);
        wrapper17.setLayout(wrapper17Layout);
        wrapper17Layout.setHorizontalGroup(
            wrapper17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper17Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper17Layout.setVerticalGroup(
            wrapper17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper17);

        wrapper18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper18Layout = new javax.swing.GroupLayout(wrapper18);
        wrapper18.setLayout(wrapper18Layout);
        wrapper18Layout.setHorizontalGroup(
            wrapper18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper18Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper18Layout.setVerticalGroup(
            wrapper18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time18, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper18);

        wrapper19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper19Layout = new javax.swing.GroupLayout(wrapper19);
        wrapper19.setLayout(wrapper19Layout);
        wrapper19Layout.setHorizontalGroup(
            wrapper19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper19Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper19Layout.setVerticalGroup(
            wrapper19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time19, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper19);

        wrapper20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper20Layout = new javax.swing.GroupLayout(wrapper20);
        wrapper20.setLayout(wrapper20Layout);
        wrapper20Layout.setHorizontalGroup(
            wrapper20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper20Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper20Layout.setVerticalGroup(
            wrapper20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time20, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper20);

        wrapper21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper21Layout = new javax.swing.GroupLayout(wrapper21);
        wrapper21.setLayout(wrapper21Layout);
        wrapper21Layout.setHorizontalGroup(
            wrapper21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper21Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper21Layout.setVerticalGroup(
            wrapper21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper21);

        wrapper22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper22Layout = new javax.swing.GroupLayout(wrapper22);
        wrapper22.setLayout(wrapper22Layout);
        wrapper22Layout.setHorizontalGroup(
            wrapper22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper22Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day22, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper22Layout.setVerticalGroup(
            wrapper22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper22);

        wrapper23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper23Layout = new javax.swing.GroupLayout(wrapper23);
        wrapper23.setLayout(wrapper23Layout);
        wrapper23Layout.setHorizontalGroup(
            wrapper23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper23Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper23Layout.setVerticalGroup(
            wrapper23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time23, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper23);

        wrapper24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper24Layout = new javax.swing.GroupLayout(wrapper24);
        wrapper24.setLayout(wrapper24Layout);
        wrapper24Layout.setHorizontalGroup(
            wrapper24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper24Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper24Layout.setVerticalGroup(
            wrapper24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper24);

        wrapper25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper25Layout = new javax.swing.GroupLayout(wrapper25);
        wrapper25.setLayout(wrapper25Layout);
        wrapper25Layout.setHorizontalGroup(
            wrapper25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper25Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper25Layout.setVerticalGroup(
            wrapper25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day25, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time25, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper25);

        wrapper26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper26Layout = new javax.swing.GroupLayout(wrapper26);
        wrapper26.setLayout(wrapper26Layout);
        wrapper26Layout.setHorizontalGroup(
            wrapper26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper26Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day26, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper26Layout.setVerticalGroup(
            wrapper26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time26, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper26);

        wrapper27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper27Layout = new javax.swing.GroupLayout(wrapper27);
        wrapper27.setLayout(wrapper27Layout);
        wrapper27Layout.setHorizontalGroup(
            wrapper27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper27Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day27, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper27Layout.setVerticalGroup(
            wrapper27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day27, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time27, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper27);

        wrapper28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper28Layout = new javax.swing.GroupLayout(wrapper28);
        wrapper28.setLayout(wrapper28Layout);
        wrapper28Layout.setHorizontalGroup(
            wrapper28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper28Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day28, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper28Layout.setVerticalGroup(
            wrapper28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time28, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper28);

        wrapper29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper29Layout = new javax.swing.GroupLayout(wrapper29);
        wrapper29.setLayout(wrapper29Layout);
        wrapper29Layout.setHorizontalGroup(
            wrapper29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper29Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day29, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper29Layout.setVerticalGroup(
            wrapper29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time29, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper29);

        wrapper30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper30Layout = new javax.swing.GroupLayout(wrapper30);
        wrapper30.setLayout(wrapper30Layout);
        wrapper30Layout.setHorizontalGroup(
            wrapper30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper30Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day30, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper30Layout.setVerticalGroup(
            wrapper30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day30, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time30, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper30);

        wrapper31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        day31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        time31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout wrapper31Layout = new javax.swing.GroupLayout(wrapper31);
        wrapper31.setLayout(wrapper31Layout);
        wrapper31Layout.setHorizontalGroup(
            wrapper31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper31Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(day31, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(wrapper31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        wrapper31Layout.setVerticalGroup(
            wrapper31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapper31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time31, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(wrapper31);

        jLabel2.setText("Bảng chấm công tháng:");

        jLabel4.setText("Năm");

        jLabel5.setText("Lương tháng này: ");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Thông tin nhân viên");

        jLabel7.setText("Tên");

        error1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error1.setForeground(new java.awt.Color(255, 51, 51));

        jLabel8.setText("SĐT:");

        error2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error2.setForeground(new java.awt.Color(255, 51, 51));

        jLabel9.setText("Địa chỉ:");

        error3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error3.setForeground(new java.awt.Color(255, 51, 51));

        jLabel10.setText("Chức vụ:");

        error4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error4.setForeground(new java.awt.Color(255, 51, 51));

        jLabel11.setText("Lương theo ngày:");

        error5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error5.setForeground(new java.awt.Color(255, 51, 51));

        btnSave.setText("Lưu thông tin");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel14.setText("Giới tính:");

        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(input1)
                            .addComponent(error1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(input2)
                            .addComponent(error2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(input3)
                            .addComponent(error3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(input4)
                            .addComponent(error4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(input5)
                            .addComponent(error5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdNam)
                    .addComponent(rdNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jButton4.setText("Xem");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText(" ");

        jLabel13.setText("Đi làm");

        jLabel15.setText("Đi làm muộn");

        jLabel17.setText("Không đi làm");

        jPanel5.setBackground(new java.awt.Color(255, 250, 215));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 159, 159));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        AdminPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20)
                        .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jButton4))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(37, 37, 37)
                        .addComponent(AdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLuongThang, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel5, jPanel6, jPanel8});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4))
                    .addComponent(AdminPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel12)
                .addGap(8, 8, 8)
                .addComponent(lblTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtLuongThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel5, jPanel6, jPanel8});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(504, 504, 504)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        hienThiChamCong();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        thayDoiThongTin();
    }//GEN-LAST:event_btnSaveActionPerformed
    private void setPermission() {
        if (Auth.role.equals(Constant.VAI_TRO_ADMIN)) {
            btnSave.setText("Lưu thông tin");
            input1.setEditable(true);
            input2.setEditable(true);
            input3.setEditable(true);
            input4.setEditable(true);
            input5.setEditable(true);
        } else {
            btnSave.setText("Chấm công");
            input1.setEditable(false);
            input2.setEditable(false);
            input3.setEditable(false);
            input4.setEditable(false);
            input5.setEditable(false);

            if (ccdao.kiemTraChamCong(nhanvien.getMaNV())) {
                btnSave.setEnabled(false);
            }
        }
    }

    private void initChamCongComponent() {
        days = new JLabel[]{day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31};
        times = new JLabel[]{time1, time2, time3, time4, time5, time6, time7, time8, time9, time10, time11, time12, time13, time14, time15, time16, time17, time18, time19, time20, time21, time22, time23, time24, time25, time26, time27, time28, time29, time30, time31};
        wrappers = new JPanel[]{wrapper1, wrapper2, wrapper3, wrapper4, wrapper5, wrapper6, wrapper7, wrapper8, wrapper9, wrapper10, wrapper11, wrapper12, wrapper13, wrapper14, wrapper15, wrapper16, wrapper17, wrapper18, wrapper19, wrapper20, wrapper21, wrapper22, wrapper23, wrapper24, wrapper25, wrapper26, wrapper27, wrapper28, wrapper29, wrapper30, wrapper31};
        for (int i = 0; i < 31; i++) {
            listComponents.add(new ChamCongComponent(times[i], days[i], wrappers[i]));
        }
    }

    private void initCombobox() {
        month = LocalDate.now().getMonthValue();
        year = LocalDate.now().getYear();
        for (int i = 1; i <= 12; i++) {
            cboMonth.addItem(i + "");
        }
        for (int i = 2020; i <= year; i++) {
            cboYear.addItem(i + "");
            if (i == year) {
                cboYear.setSelectedIndex(i - 2020);
            }
        }
        cboMonth.setSelectedIndex(month - 1);
    }

    private void loadData() {
        list = ccdao.selectByTime(nhanvien.getMaNV(), year, month);
    }

    private void fillDate() {
        int totalDay = XDate.getTotalDayInMonth(month, year);
        for (int i = 0; i < days.length; i++) {
            if (i < totalDay) {
                days[i].setText((i + 1) + "");
            } else {
                days[i].setText("");
            }
        }
        lblTime.setText("Tháng " + month + " - " + year);
    }

    void clearComponent(int index) {
        listComponents.get(index).clear();
    }

    private void fillComponent() {
        int count = 0;
        if (list.size() > 0) {
            for (int i = 0; i < listComponents.size(); i++) {
                if (count < list.size()) {
                    if ((i + 1) == getDayOfMonth(list.get(count).getThoiGian())) {
                        listComponents.get(i).show(list.get(count).getThoiGian());
                        count++;
                    }
                } else {
                    listComponents.get(i).clear();
                    System.out.println(i);
                }
            }
        } else {
            for (int i = 0; i < listComponents.size(); i++) {
                listComponents.get(i).clear();
            }
        }
    }

    int getDayOfMonth(Date d) {
        return Integer.parseInt(new SimpleDateFormat("dd").format(d));
    }

    private void hienThiChamCong() {
        month = Integer.parseInt((String) cboMonth.getSelectedItem());
        year = Integer.parseInt((String) cboYear.getSelectedItem());
        loadData();
        fillDate();
        fillComponent();
        tinhLuong();
    }

    private void fillForm() {
        input1.setText(nhanvien.getTen());
        input2.setText(nhanvien.getSdt());
        input3.setText(nhanvien.getDiaChi());
        input4.setText(nhanvien.getChucvu());
        input5.setText((int) nhanvien.getLuongTheoNgay() + "");
        if (nhanvien.isGioiTinh()) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
    }

    private void tinhLuong() {
        int soNgayDiTre = 0;
        for (int i = 0; i < list.size(); i++) {
            if (XDate.diLamMuon(list.get(i).getThoiGian())) {
                soNgayDiTre++;
            }
        }
        int tienLuong = (int) (nhanvien.getLuongTheoNgay() * list.size() - soNgayDiTre * Constant.DI_TRE);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        txtLuongThang.setText(formatter.format(tienLuong) + " VND");
    }

    private boolean isValidate() {
        boolean flag = true;
        List<FormField> fields = new ArrayList<>();
        fields.add(new InputField(input1, error1, InputField.REQUIRE, "Tên không được để trống"));
        fields.add(new InputField(input2, error2, InputField.PHONE, "SĐT không đúng định dạng"));
        fields.add(new InputField(input3, error3, InputField.REQUIRE, "Địa chỉ không được để trống"));
        fields.add(new InputField(input4, error4, InputField.REQUIRE, "Chức vụ không được để trống"));

        for (FormField field : fields) {
            if (!field.isValid()) {
                flag = false;
            }
        }

        InputField luong = new InputField(input5, error5);
        if (!luong.isMoreThan(0, "Lương phải là số lớn hơn 0")) {
            flag = false;
        }

        return flag;
    }

    private void thayDoiThongTin() {
        if (Auth.role.equals(Constant.VAI_TRO_ADMIN)) {
            if (isValidate()) {
                nhanvien.setTen(input1.getText());
                nhanvien.setSdt(input2.getText());
                nhanvien.setDiaChi(input3.getText());
                nhanvien.setChucvu(input4.getText());
                nhanvien.setLuongTheoNgay(Double.parseDouble(input5.getText()));
                nhanvien.setGioiTinh(rdNam.isSelected());
                nvdao.update(nhanvien);
                MsgBox.alert(this, "Cập nhật thành công!");
                qlnv.loadData();
                qlnv.fillTable();
            }
        } else {
            ccdao.chamCong(nhanvien.getMaNV());
            MsgBox.alert(this, "Chấm công thành công!");
            btnSave.setEnabled(false);
            hienThiChamCong();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhanVienUI dialog = new NhanVienUI(new javax.swing.JFrame(), true, nhanvien, qlnv);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboMonth;
    private javax.swing.JComboBox<String> cboYear;
    private javax.swing.JLabel day1;
    private javax.swing.JLabel day10;
    private javax.swing.JLabel day11;
    private javax.swing.JLabel day12;
    private javax.swing.JLabel day13;
    private javax.swing.JLabel day14;
    private javax.swing.JLabel day15;
    private javax.swing.JLabel day16;
    private javax.swing.JLabel day17;
    private javax.swing.JLabel day18;
    private javax.swing.JLabel day19;
    private javax.swing.JLabel day2;
    private javax.swing.JLabel day20;
    private javax.swing.JLabel day21;
    private javax.swing.JLabel day22;
    private javax.swing.JLabel day23;
    private javax.swing.JLabel day24;
    private javax.swing.JLabel day25;
    private javax.swing.JLabel day26;
    private javax.swing.JLabel day27;
    private javax.swing.JLabel day28;
    private javax.swing.JLabel day29;
    private javax.swing.JLabel day3;
    private javax.swing.JLabel day30;
    private javax.swing.JLabel day31;
    private javax.swing.JLabel day4;
    private javax.swing.JLabel day5;
    private javax.swing.JLabel day6;
    private javax.swing.JLabel day7;
    private javax.swing.JLabel day8;
    private javax.swing.JLabel day9;
    private javax.swing.JLabel error1;
    private javax.swing.JLabel error2;
    private javax.swing.JLabel error3;
    private javax.swing.JLabel error4;
    private javax.swing.JLabel error5;
    private javax.swing.JTextField input1;
    private javax.swing.JTextField input2;
    private javax.swing.JTextField input3;
    private javax.swing.JTextField input4;
    private javax.swing.JTextField input5;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblTime;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JLabel time1;
    private javax.swing.JLabel time10;
    private javax.swing.JLabel time11;
    private javax.swing.JLabel time12;
    private javax.swing.JLabel time13;
    private javax.swing.JLabel time14;
    private javax.swing.JLabel time15;
    private javax.swing.JLabel time16;
    private javax.swing.JLabel time17;
    private javax.swing.JLabel time18;
    private javax.swing.JLabel time19;
    private javax.swing.JLabel time2;
    private javax.swing.JLabel time20;
    private javax.swing.JLabel time21;
    private javax.swing.JLabel time22;
    private javax.swing.JLabel time23;
    private javax.swing.JLabel time24;
    private javax.swing.JLabel time25;
    private javax.swing.JLabel time26;
    private javax.swing.JLabel time27;
    private javax.swing.JLabel time28;
    private javax.swing.JLabel time29;
    private javax.swing.JLabel time3;
    private javax.swing.JLabel time30;
    private javax.swing.JLabel time31;
    private javax.swing.JLabel time4;
    private javax.swing.JLabel time5;
    private javax.swing.JLabel time6;
    private javax.swing.JLabel time7;
    private javax.swing.JLabel time8;
    private javax.swing.JLabel time9;
    private javax.swing.JTextField txtLuongThang;
    private javax.swing.JPanel wrapper1;
    private javax.swing.JPanel wrapper10;
    private javax.swing.JPanel wrapper11;
    private javax.swing.JPanel wrapper12;
    private javax.swing.JPanel wrapper13;
    private javax.swing.JPanel wrapper14;
    private javax.swing.JPanel wrapper15;
    private javax.swing.JPanel wrapper16;
    private javax.swing.JPanel wrapper17;
    private javax.swing.JPanel wrapper18;
    private javax.swing.JPanel wrapper19;
    private javax.swing.JPanel wrapper2;
    private javax.swing.JPanel wrapper20;
    private javax.swing.JPanel wrapper21;
    private javax.swing.JPanel wrapper22;
    private javax.swing.JPanel wrapper23;
    private javax.swing.JPanel wrapper24;
    private javax.swing.JPanel wrapper25;
    private javax.swing.JPanel wrapper26;
    private javax.swing.JPanel wrapper27;
    private javax.swing.JPanel wrapper28;
    private javax.swing.JPanel wrapper29;
    private javax.swing.JPanel wrapper3;
    private javax.swing.JPanel wrapper30;
    private javax.swing.JPanel wrapper31;
    private javax.swing.JPanel wrapper4;
    private javax.swing.JPanel wrapper5;
    private javax.swing.JPanel wrapper6;
    private javax.swing.JPanel wrapper7;
    private javax.swing.JPanel wrapper8;
    private javax.swing.JPanel wrapper9;
    // End of variables declaration//GEN-END:variables
}

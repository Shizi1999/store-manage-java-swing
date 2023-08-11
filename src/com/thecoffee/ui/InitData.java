/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.thecoffee.ui;

import com.thecoffee.dao.ChamCongDAO;
import com.thecoffee.dao.DonHangChiTietDAO;
import com.thecoffee.dao.DonHangDAO;
import com.thecoffee.dao.KhachHangDAO;
import com.thecoffee.dao.NhanVienDAO;
import com.thecoffee.dao.SanPhamDAO;
import com.thecoffee.entity.DonHang;
import com.thecoffee.entity.DonHangChiTiet;
import com.thecoffee.entity.KhachHang;
import com.thecoffee.entity.NhanVien;
import com.thecoffee.entity.SanPham;
import com.thecoffee.utils.MsgBox;
import com.thecoffee.utils.XDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daung
 */
public class InitData extends javax.swing.JFrame {
    
    List<SanPham> listsp = new ArrayList<>();
    List<DonHangChiTiet> listDhct = new ArrayList<>();
    List<KhachHang> listkh = new ArrayList<>();
    List<NhanVien> listnv = new ArrayList<>();
    SanPhamDAO spdao = new SanPhamDAO();
    DonHangChiTietDAO dhctdao = new DonHangChiTietDAO();
    DonHangDAO dhdao = new DonHangDAO();
    KhachHangDAO khdao = new KhachHangDAO();
    ChamCongDAO ccdao = new ChamCongDAO();
    NhanVienDAO nvdao = new NhanVienDAO();

    /**
     * Creates new form InitData
     */
    public InitData() {
        initComponents();
        loadData();
        jDate.setDate(new Date());
//        try {
//            for(int i=2; i<=7; i++){
//                randomChamCong(i);
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(InitData.class.getName()).log(Level.SEVERE, null, ex);
//        }
        createInRange(1, 12);
    }
    
    int randomNumber(int max, int min) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
    
    void loadData() {
        listsp = spdao.selectAll();
        listkh = khdao.selectAll();
        listnv = nvdao.selectAll();
    }
    
    void taoDHCT(int maDH) {
        listDhct.clear();
        int randomNum = randomNumber(10, 1);
        List<Integer> listId = new ArrayList<>();
        for (int i = 0; i < randomNum; i++) {
            int ranDomSoluong = randomNumber(4, 1);
            int randomSanPham = 0;
            while (true) {
                randomSanPham = randomNumber(listsp.size() - 1, 0);
                if (!listId.contains(randomSanPham)) {
                    listId.add(randomSanPham);
                    break;
                }
            }
            DonHangChiTiet dhct = new DonHangChiTiet();
            dhct.setMaDH(maDH);
            dhct.setMaSP(listsp.get(randomSanPham).getMaSP());
            dhct.setSoLuong(ranDomSoluong);
            dhct.setGia(listsp.get(randomSanPham).getGia());
            listDhct.add(dhct);
        }
    }
    
    void create() {
        try {
            int tongDonHang = Integer.parseInt(txtSoluong.getText());
            for (int i = 0; i < tongDonHang; i++) {
                Date date = jDate.getDate();
                if (chkIsMonth.isSelected()) {
                    int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
                    int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
                    int totalDate = XDate.getTotalDayInMonth(month, year);
                    int randomDate = randomNumber(totalDate, 1);
                    date = convertDate(date, randomDate);
                    
                } else {
                    int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
                    date = convertDate(date, day);
                }
                KhachHang kh = randomKhachHang();
                NhanVien nv = randomNhanVien();
                DonHang dh = new DonHang();
                dh.setNgayTao(date);
                dh.setDiem(0);
                dh.setGiamGia(0);
                dh.setMaNV(nv.getMaNV());
                dh.setMaVoucher("");
                dh.setMaKH(kh.getMaKH());
                dhdao.insert(dh);
                taoDHCT(dh.getMaDH());
                double tongtien = 0;
                for (DonHangChiTiet dhct : listDhct) {
                    dhctdao.insert(dhct);
                    tongtien += dhct.getGia() * dhct.getSoLuong();
                }
                dh.setThanhToan(tongtien + tongtien * 0.1);
                dhdao.update(dh);
            }
            MsgBox.alert(this, "Thành công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Date convertDate(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.DATE, day);
        int h = randomNumber(23, 0);
        int m = randomNumber(59, 0);
        int s = randomNumber(59, 0);
        cal.set(Calendar.HOUR, h);
        cal.set(Calendar.MINUTE, m);
        cal.set(Calendar.SECOND, s);
        return cal.getTime();
    }
    
    private Date createDate(int day, int month, int year) throws ParseException {
        String ngay = "" + day;
        String thang = "" + month;
        String nam = "" + year;
        
        if (day < 10) {
            ngay = "0" + ngay;
        }
        if (month < 10) {
            thang = "0" + thang;
        }
        int h = randomNumber(7, 6);
        String mm = randomNumber(59, 10) + "";
        String ss = randomNumber(59, 10) + "";
        String hh = "" + h;
        if (h < 10) {
            hh = "0" + h;
        }
        String time = ngay + "-" + thang + "-" + nam + " " + hh + "-" + mm + "-" + ss;
        System.out.println(time);
        return new SimpleDateFormat("dd-MM-yyyy kk-mm-ss").parse(time);
    }
    
    void add10KhachHang() {
        for (int i = 0; i < 10; i++) {
            String name = randomName();
            String phone = randomPhoneNumber();
            KhachHang kh = new KhachHang();
            kh.setTen(name);
            kh.setSdt(phone);
            kh.setTichDiem(0);
            khdao.insert(kh);
        }
        listkh = khdao.selectAll();
        MsgBox.alert(this, "Thành công");
    }
    
    String randomName() {
        String[] ho = {"Nguyễn", "Trần", "Hồ", "Hoàng", "Đậu", "Bùi", "Lê", "Phạm", "Phan", "Đặng", "Ngô", "Vũ"};
        String[] dem = {"Văn", "Thị", "Ngọc", "Thiên", "Trung", "Hoàng", "Quốc", "Minh"};
        String[] ten = {"An", "Anh", "Ánh", "Bảo", "Bình", "Chánh", "Cảnh", "Dương", "Dung", "Danh", "Đức", "Đạt", "Đại", "Hưng", "Huy", "Khánh", "Khá", "Khoa", "Linh", "Lan", "Lành", "Lụa"};
        int num1 = randomNumber(ho.length - 1, 0);
        int num2 = randomNumber(dem.length - 1, 0);
        int num3 = randomNumber(ten.length - 1, 0);
        return ho[num1] + " " + dem[num2] + " " + ten[num3];
    }
    
    String randomPhoneNumber() {
        String[] startWiths = {"032", "033", "034", "035", "036", "037", "038", "039", "070", "079", "078", "077", "076", "084", "085", "081", "082"};
        String phoneNumber = "";
        while (true) {
            int index = randomNumber(startWiths.length - 1, 0);
            int num = randomNumber(9999999, 1000000);
            phoneNumber = startWiths[index] + num;
            KhachHang kh = khdao.selectByPhone(phoneNumber);
            if (kh == null) {
                return phoneNumber;
            }
        }
    }
    
    KhachHang randomKhachHang() {
        int num = randomNumber(listkh.size() - 1, 0);
        return listkh.get(num);
    }
    
    NhanVien randomNhanVien() {
        int num = randomNumber(listnv.size() - 1, 0);
        return listnv.get(num);
    }
    
    void randomChamCong(int manv) throws ParseException {
        for (int i = 2020; i <= 2022; i++) {
            for (int j = 1; j <= 12; j++) {
                for (int k = 1; k <= XDate.getTotalDayInMonth(j, i); k++) {
                    ccdao.chamCong(manv, createDate(k, j, i));
                }
            }
        }
    }
    
    void createInRange(int start, int end) {
        for (int i = start; i <= end; i++) {
            Date d = new Date();
            d = convertDate(d, i);
            for (int j = 0; j <= 20; j++) {
                KhachHang kh = randomKhachHang();
                NhanVien nv = randomNhanVien();
                DonHang dh = new DonHang();
                dh.setNgayTao(d);
                dh.setDiem(0);
                dh.setGiamGia(0);
                dh.setMaNV(nv.getMaNV());
                dh.setMaVoucher("");
                dh.setMaKH(kh.getMaKH());
                dhdao.insert(dh);
                taoDHCT(dh.getMaDH());
                double tongtien = 0;
                for (DonHangChiTiet dhct : listDhct) {
                    dhctdao.insert(dhct);
                    tongtien += dhct.getGia() * dhct.getSoLuong();
                }
                dh.setThanhToan(tongtien + tongtien * 0.1);
                dhdao.update(dh);
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

        jDate = new com.toedter.calendar.JDateChooser();
        txtSoluong = new javax.swing.JTextField();
        chkIsMonth = new javax.swing.JCheckBox();
        btnCreate = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chkIsMonth.setText("In month?");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jButton1.setText("Thêm mới 10 khách hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(txtSoluong)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkIsMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreate))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkIsMonth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        create();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        add10KhachHang();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(InitData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InitData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InitData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InitData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InitData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JCheckBox chkIsMonth;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtSoluong;
    // End of variables declaration//GEN-END:variables
}

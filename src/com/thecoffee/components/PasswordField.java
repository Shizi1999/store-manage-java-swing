/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.components;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 *
 * @author daung
 */
public class PasswordField extends FormField {

    private JPasswordField input;
    private JLabel error;
    private String message;

    
    public PasswordField(JPasswordField input, JLabel error, String message) {
        this.input = input;
        this.error = error;
        this.message = message;
    }

    public JPasswordField getInput() {
        return input;
    }

    public JLabel getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
    
    

    @Override
    public boolean isValid() {
        return this.isRequire();
    }

    public boolean isRequire() {
        String text = new String(input.getPassword());
        if (text.length() <= 0) {
            error.setText(this.message);
            return false;
        } else {
            error.setText(" ");
            return true;
        }
    }

    public boolean isEquals(PasswordField rePassword) {
        String password = new String(this.input.getPassword());
        String confirmPassword = new String(rePassword.getInput().getPassword());
        if (password.equals(confirmPassword)) {
            rePassword.getError().setText(" ");
            return true;
        } else {
            rePassword.getError().setText(rePassword.getMessage());
            return false;
        }
    }
}

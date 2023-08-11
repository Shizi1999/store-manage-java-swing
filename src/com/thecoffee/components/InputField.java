/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.components;

import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author daung
 */
public class InputField extends FormField {

    private JTextField input;
    private JLabel error;
    private String emailRegex = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    private String phoneRegex = "0[0-9]{9}";
    private int type;
    private String message;
    public static final int REQUIRE = 0;
    public static final int EMAIL = 1;
    public static final int PHONE = 2;
    public static final int INTEGER = 3;
    public static final int MORETHAN = 4;
    public static final int LESSTHAN = 5;

    public InputField(JTextField input, JLabel error) {
        this.input = input;
        this.error = error;
    }

    public InputField(JTextField input, JLabel error, int type, String errorMessage) {
        this.input = input;
        this.error = error;
        this.type = type;
        this.message = errorMessage;
    }

    @Override
    public boolean isValid() {
        switch (this.type) {
            case REQUIRE:
                return this.isRequire(this.message);
            case EMAIL:
                return this.isEmail(this.message);
            case PHONE:
                return this.isPhoneNumber(this.message);
            case INTEGER:
                return this.isInteger(this.message);
            default:
                throw new AssertionError();
        }
    }

    public JTextField getInput() {
        return input;
    }

    public JLabel getError() {
        return error;
    }

    public boolean isRequire(String errorMessage) {
        String text = this.input.getText();
        if (text.trim().length() <= 0) {
            this.error.setText(errorMessage);
            return false;
        } else {
            this.error.setText(" ");
            return true;
        }
    }

    public boolean isEmail(String errorMessage) {
        String email = this.input.getText();
        if (Pattern.matches(emailRegex, email)) {
            this.error.setText(" ");
            return true;
        } else {
            this.error.setText(errorMessage);
            return false;
        }
    }

    public boolean isPhoneNumber(String errorMessage) {
        String phone = this.input.getText();
        if (Pattern.matches(phoneRegex, phone)) {
            this.error.setText(" ");
            return true;
        } else {
            this.error.setText(errorMessage);
            return false;
        }
    }

    public boolean isInteger(String message) {
        try {
            int value = Integer.parseInt(this.input.getText());
            this.error.setText(" ");
            return true;
        } catch (Exception e) {
            this.error.setText(message);
            return false;
        }
    }

    public boolean isMoreThan(int number, String message) {
        try {
            int value = Integer.parseInt(this.input.getText());
            if (value <= number) {
                this.error.setText(message);
                return false;
            } else {
                this.error.setText("");
                return true;
            }
        } catch (Exception e) {
            this.error.setText("Phải là số");
            return false;
        }
    }

    public boolean isLessThan(int number, String message) {
        try {
            int value = Integer.parseInt(this.input.getText());
            if (value >= number) {
                this.error.setText(message);
                return false;
            } else {
                this.error.setText("");
                return true;
            }
        } catch (Exception e) {
            this.error.setText("Trường này phải là số");
            return false;
        }
    }

}

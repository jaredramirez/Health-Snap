package main;

import objects.CalorieEntry;
import objects.ExerciseEntry;
import objects.Person;
import objects.WaterEntry;

import java.util.ArrayList;
import java.io.*;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import static javafx.scene.paint.Color.RED;

public class Managers {

    public String FILENAME = "data.ser";

    private ArrayList<Person> userList = new ArrayList<Person>();

    public void createUser(Double height, Double weight, Double age, Double waist, boolean gender, String username, String password,
                           ArrayList<CalorieEntry> c, ArrayList<WaterEntry> w, ArrayList<ExerciseEntry> e, String intensity) {
        Person person = new Person(height, weight, age, waist, gender, username, password, c, w, e, intensity);

        userList.add(person);
    }

    public int getArraySize() {
        return userList.size();
    }

    public ArrayList getArrayList() {
        return userList;
    }

    public boolean validateTextString(String string, int maxLen, int minLen, Label errorLabel,
                                      String nullError, String tooSmallError, String tooBigError) {
        errorLabel.setTextFill(RED);

        if (!string.equals("")) {
            if (string.length() > maxLen) {
                errorLabel.setText(tooBigError);
                return false;
            } else if (string.length() < minLen) {
                errorLabel.setText(tooSmallError);
                return false;
            } else {
                errorLabel.setText("");
                return true;
            }
        } else {
            errorLabel.setText(nullError);
            return false;
        }
    }

    public boolean validateTextInt(String string, int maxNum, int minNum, Label errorLabel,
                                   String nullError, String tooSmallError, String tooBigError) {
        errorLabel.setTextFill(RED);

        if (!string.equals("")) {
            if (isNumeric(string)) {
                Double num = Double.parseDouble(string);
                if (num > maxNum) {
                    errorLabel.setText(tooBigError);
                    return false;
                } else if (num < minNum) {
                    errorLabel.setText(tooSmallError);
                    return false;
                } else {
                    errorLabel.setText("");
                    return true;
                }
            } else {
                errorLabel.setText("Not a number");
                return false;
            }
        } else {
            errorLabel.setText(nullError);
            return false;
        }
    }

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int getInt(String string) {
        return Integer.parseInt(string);
    }

    public double getDouble(String string) {
        return Double.parseDouble(string);
    }

    public boolean validateDate(String string, Label error) {
        error.setTextFill(RED);

        if (string.length() == 8) {
            if (isNumeric(string.substring(0, 2)) && string.substring(2, 3).equals("/") && isNumeric(string.substring(3, 5))
                    && string.substring(5, 6).equals("/") && isNumeric(string.substring(6))) {
                error.setText("");
                return true;
            } else {
                error.setText("Date invalid");
                return false;
            }
        } else {
            error.setText("Date invalid");
            return false;
        }
    }

    public boolean isDate(String string) {
        // dd/mm/yy //
        String days = string.substring(0, 2);
        String months = string.substring(3, 5);
        String years = string.substring(6);
        if (string.substring(2, 3).equals("/") && string.substring(5, 6).equals("/")) {
            if (isNumeric(days) && isNumeric(months) && isNumeric(years)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validateComboBox(ComboBox comboBox, Label errorLabel, String errorMsg) {
        errorLabel.setTextFill(RED);

        if (comboBox.getValue() != null) {
            errorLabel.setText("");
            return true;
        } else {
            errorLabel.setText(errorMsg);
            return false;
        }
    }

    public boolean checkPassword(String password, String password2, Label errorLabel, String errorMsg) {
        errorLabel.setTextFill(RED);

        if (password.equals(password2) && !password.equals("") && !password2.equals("")) {
            errorLabel.setText("");
            return true;
        } else {
            errorLabel.setText(errorMsg);
            return false;
        }

    }

    public void save() {
        FileOutputStream fileID;
        ObjectOutputStream outFile;

        try {
            fileID = new FileOutputStream(FILENAME);
            outFile = new ObjectOutputStream(fileID);

            outFile.writeObject(userList);

            outFile.close();
        } catch (IOException e) {
            System.out.println("Error writing to data file: " + e.getMessage());
        }
    }

    public void load() {
        FileInputStream fileID;
        ObjectInputStream inFile;

        try {
            fileID = new FileInputStream(FILENAME);
            inFile = new ObjectInputStream(fileID);

            userList = (ArrayList<Person>) inFile.readObject();

            inFile.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error trying to open file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error trying to open file: " + e.getMessage());
        }
    }

    public boolean isNull() {
        FileInputStream fileID;
        ObjectInputStream inFile;

        try {
            fileID = new FileInputStream(FILENAME);
            inFile = new ObjectInputStream(fileID);

            if (inFile.readObject() == null) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            return true;
        } catch (IOException e) {
            return true;
        }

        return false;
    }

    public boolean isSerNull() {
        return isNull() == false;

    }
}


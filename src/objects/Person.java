package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {

    Double height, weight, age, waist;
    boolean gender; //true=male false=female
    String username, password, intensity;
    ArrayList<CalorieEntry> calorieData;
    ArrayList<WaterEntry> waterData;
    ArrayList<ExerciseEntry> exerciseData;

    public Person(Double height, Double weight, Double age, Double waist, boolean gender,
                  String username, String password, ArrayList calorieData, ArrayList waterData, ArrayList exerciseData, String intensity) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.waist = waist;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.intensity = intensity;
        this.calorieData = calorieData;
        this.waterData = waterData;
        this.exerciseData = exerciseData;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getAge() {
        return age;
    }

    public Double getWaist() {
        return waist;
    }

    public boolean getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList getCalorieData() {
        return calorieData;
    }

    public ArrayList getWaterData() {
        return waterData;
    }

    public void createCalorieArray() {
        ArrayList<CalorieEntry> c = new ArrayList();
        calorieData = c;
    }

    public ArrayList getExerciseData() {
        return exerciseData;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setHeight(Double newHeight) {
        height = newHeight;
    }

    public void setWeight(Double newWeight) {
        weight = newWeight;
    }

    public void setAge(Double newAge) {
        age = newAge;
    }

    public void setWaist(Double newWaist) {
        waist = newWaist;
    }

    public void setGender(boolean newGender) {
        gender = newGender;
    }

    public void setUser(String u) {
        username = u;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public void addCalorieEntry(CalorieEntry c) {
        calorieData.add(c);
    }

    public void addWaterEntry(WaterEntry w) {
        waterData.add(w);
    }

    public void addExerciseData(ExerciseEntry e) {
        exerciseData.add(e);
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String toString() {
        return "[Username  : " + username
                + " Password    : " + password
                + " Height    : " + height
                + " Weight    : " + weight
                + " Age       : " + age
                + " Waist Size: " + waist
                + " Gender    : " + gender
                + " Intensity : " + intensity
                + "]";
    }
}

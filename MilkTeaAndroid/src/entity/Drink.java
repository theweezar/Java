/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hpmdu
 */
public class Drink {
    
    private int id;
    private String drinkName;
    private String drinkImage;
    private int drinkType;
    private String describe;

    public Drink() {
    }

    public Drink(int id, String drinkName, String drinkImage, int drinkType, String describe) {
        this.id = id;
        this.drinkName = drinkName;
        this.drinkImage = drinkImage;
        this.drinkType = drinkType;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkImage() {
        return drinkImage;
    }

    public void setDrinkImage(String drinkImage) {
        this.drinkImage = drinkImage;
    }

    public int getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(int drinkType) {
        this.drinkType = drinkType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    
}

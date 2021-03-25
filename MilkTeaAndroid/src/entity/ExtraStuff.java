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
public class ExtraStuff {
    
    private int id;
    private String extraName;
    private String extraImage;
    private int extraType;
    private String describe;

    public ExtraStuff() {
    }

    public ExtraStuff(int id, String extraName, String extraImage, int extraType, String describe) {
        this.id = id;
        this.extraName = extraName;
        this.extraImage = extraImage;
        this.extraType = extraType;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public String getExtraImage() {
        return extraImage;
    }

    public void setExtraImage(String extraImage) {
        this.extraImage = extraImage;
    }

    public int getExtraType() {
        return extraType;
    }

    public void setExtraType(int extraType) {
        this.extraType = extraType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
}

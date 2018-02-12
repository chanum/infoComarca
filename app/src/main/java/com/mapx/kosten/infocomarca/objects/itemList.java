package com.mapx.kosten.infocomarca.objects;

/**
 * Created by orkeke on 2/12/18.
 */

public class itemList {

    private int itemId;
    private String itemName;
    private String itemSubName;
    private Integer itemThumb;

    // constructors ----------------------------------------------------//

    public itemList (String name, String subName, Integer thumb) {
        this.itemName = name;
        this.itemSubName = subName;
        this.itemThumb = thumb;
    }


    // getters and setters ---------------------------------------------//

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSubName() {
        return itemSubName;
    }

    public void setItemSubName(String itemSubName) {
        this.itemSubName = itemSubName;
    }

    public Integer getItemThumb() {
        return itemThumb;
    }

    public void setItemThumb(Integer itemThumb) {
        this.itemThumb = itemThumb;
    }
}

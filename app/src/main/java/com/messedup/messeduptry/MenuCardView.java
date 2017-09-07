package com.messedup.messeduptry;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.messedup.messeduptry.R.id.map;

/**
 * Created by saurabh on 23/8/17.
 */

public class MenuCardView implements Serializable {

    /*private HashMap<String,String> MessDetailsHasMap=new HashMap<>();
    private HashMap<String ,String> MenuHashMap=new HashMap<>();*/
    String MessID,Rice,VegieOne,VegieTwo,VegieThree,Roti,Special,SpecialExtra,Other,GCharge,OTime,CTime,Stat;

    public String getGCharge() {
        return GCharge;
    }

    public void setGCharge(String GCharge) {
        this.GCharge = GCharge;
    }

    public String getOTime() {
        return OTime;
    }

    public void setOTime(String OTime) {
        this.OTime = OTime;
    }

    public String getCTime() {
        return CTime;
    }

    public void setCTime(String CTime) {
        this.CTime = CTime;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String stat) {
        Stat = stat;
    }

    public String getMessID() {
        return MessID;
    }

    public void setMessID(String messID) {
        MessID = messID;
    }

    public String getRice() {
        return Rice;
    }

    public void setRice(String rice) {
        Rice = rice;
    }

    public String getVegieOne() {
        return VegieOne;
    }

    public void setVegieOne(String vegieOne) {
        VegieOne = vegieOne;
    }

    public String getVegieTwo() {
        return VegieTwo;
    }

    public void setVegieTwo(String vegieTwo) {
        VegieTwo = vegieTwo;
    }

    public String getVegieThree() {
        return VegieThree;
    }

    public void setVegieThree(String vegieThree) {
        VegieThree = vegieThree;
    }

    public String getRoti() {
        return Roti;
    }

    public void setRoti(String roti) {
        Roti = roti;
    }

    public String getSpecial() {
        return Special;
    }

    public void setSpecial(String special) {
        Special = special;
    }

    public String getSpecialExtra() {
        return SpecialExtra;
    }

    public void setSpecialExtra(String specialExtra) {
        SpecialExtra = specialExtra;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public MenuCardView(/*HashMap<String, String> messDetailsHasMap, HashMap<String, String> menuHashMap*/) {
       /* MessDetailsHasMap = messDetailsHasMap;
        MenuHashMap = menuHashMap;

        getDetails();*/
    }

   /* private void getDetails() {

        for (Map.Entry<String,String> entry: MenuHashMap.entrySet()) {
            switch (entry.getKey())
            {
                case "messid":
                    setMessID(entry.getValue());
                    break;
                case "rice":
                    setRice(entry.getValue());
                    break;
                case "vegieone":
                    setVegieOne(entry.getValue());
                    break;
                case "vegietwo":
                    setVegieTwo(entry.getValue());
                    break;
                case "vegiethree":
                    setVegieThree(entry.getValue());
                    break;
                case "roti":
                    setRoti(entry.getValue());
                    break;
                case "special":
                    setSpecial(entry.getValue());
                    break;
                case "specialextra":
                    setSpecialExtra(entry.getValue());
                    break;
                case "other":
                    setOther(entry.getValue());
                    break;

            }

        }
    }*/

    @Override
    public String toString() {

        try {

            String menu = null;
            if (Special != null && !Special.equals("null")) {
                menu = Special + ", ";
            }
            if (SpecialExtra != null && !SpecialExtra.equals("null")) {
                menu = menu + SpecialExtra + ", ";
            }
            if (VegieOne != null && !VegieOne.equals("null")) {
                menu = menu + VegieOne + ", ";
            }
            if (VegieTwo != null && !VegieTwo.equals("null")) {
                menu = menu + VegieTwo + ", ";
            }
            if (VegieThree != null && !VegieThree.equals("null")) {
                menu = menu + VegieThree + ", ";
            }
            if (Rice != null && !Rice.equals("null")) {
                menu = menu + Rice + ", ";
            }
            if ((Roti != null && !Roti.equals("null")) && (Other != null && !Other.equals("null"))) {
                menu = menu + Roti + ", ";
            }
            if ((Roti != null && !Roti.equals("null")) && (Other == null && Other.equals("null"))) {
                menu = menu + Roti + ".";
            }
            if (Other != null && !Other.equals("null")) {
                menu = menu + Other + ".";
            }


            return menu;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Menu of: "+MessID;
        }
    }
}

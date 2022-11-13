package fxml;

import com.tap.licencias.entity.Place;

public class PlaceStatic {

    private Place place;
    private int amount = 0;

    public Place getPlace(){
        return this.place;
    }

    public void setAppointment(Place p){
        this.place = p;
    }

    public int getAmount(){
        return this.amount;
    }

    public void addAmount(){
        this.amount = this.amount + 1;
    }


}

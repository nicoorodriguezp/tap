package fxml;

import com.tap.licencias.entity.LicenceState;

public class StateStatic {

    private LicenceState state;
    private int amount = 0;

    public void setState(LicenceState state){
        this.state = state;
    }

    public LicenceState getState(){
        return this.state;
    }

    public void addAmount(){
        this.amount = this.amount + 1;
    }

    public int getAmount(){
        return this.amount;
    }
}

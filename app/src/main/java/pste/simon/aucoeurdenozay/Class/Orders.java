package pste.simon.aucoeurdenozay.Class;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Simon on 15/03/2017.
 */

public class Orders {

    public ArrayList<Products> productList = new ArrayList<Products>();

    private int id;
    private Client Client;
    private int DueDate;
    private int State;

    private int Final_Price;//A inplementer

    public Orders()
    {
    }

    public Orders(Client _client, int _date)
    {
        Client = _client;
        DueDate = _date;
    }

    public Orders(Client _client, int _date, int _state)
    {
        Client = _client;
        DueDate = _date;
        State = _state;
    }

    //Getters

    public int getId(){return id;}
    public Client getClient(){return Client;}
    public int getDate(){return DueDate;}
    public int getState(){return State;}

    //Setters

    public void setId(int _id){id = _id;}

    public void setClient(pste.simon.aucoeurdenozay.Class.Client _client) {
        Client = _client;
    }

    public void setDueDate(int _dueDate) {
        DueDate =_dueDate;
    }

    public void setState(int _state){State = _state;}
}

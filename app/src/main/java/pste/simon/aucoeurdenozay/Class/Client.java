package pste.simon.aucoeurdenozay.Class;

import java.security.PublicKey;

/**
 * Created by Simon on 15/03/2017.
 */

public class Client {

    int Id;
    String Name;
    long Phone_N;

    public Client()
    {

    }

    public Client(String _Name, long _PhoneN)
    {
        Name = _Name;
        Phone_N = _PhoneN;
    }

    public Client(int _id,String _Name, long _PhoneN)
    {
        Id = _id;
        Name = _Name;
        Phone_N = _PhoneN;
    }

    //Getters
    public int getId()
    {
        return Id;
    }
    public String getName()
    {
        return Name;
    }
    public long getPhone_N()
    {
        return Phone_N;
    }

    //Setters

    public void setId(int _id)
    {
        Id = _id;
    }
    public void setName(String _Name)
    {
        Name = _Name;
    }
    public void setPhone_N(long _PhoneN)
    {
        Phone_N = _PhoneN;
    }
}

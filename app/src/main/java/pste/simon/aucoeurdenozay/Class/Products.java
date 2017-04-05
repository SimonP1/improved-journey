package pste.simon.aucoeurdenozay.Class;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;

import static pste.simon.aucoeurdenozay.Class.Size.INDIV;

/**
 * Created by Simon on 15/03/2017.
 */


public class Products {

    private int id;
    private String Name = "";
    private pste.simon.aucoeurdenozay.Class.Size Size;
    private pste.simon.aucoeurdenozay.Class.Size Size_Max;
    private pste.simon.aucoeurdenozay.Class.Type Type;
    private int Price;

    public Products (){}
    public Products(String _Name, pste.simon.aucoeurdenozay.Class.Size _Size)
    {
        Name = _Name;
        Size = _Size;

        //Faire une map/table dans un fichier qui permet de recup size max-prix-type auto
    }
     //Getters
    public int getId(){return id;}

    public String getName()
    {
        return Name;
    }

    public pste.simon.aucoeurdenozay.Class.Size getSize()
    {
        return Size;
    }

    public pste.simon.aucoeurdenozay.Class.Size getSize_Max()
    {
        return Size_Max;
    }

    public int getIntSize_Max(){
        int retour = 0;
        switch (Size_Max){
            case INDIV: retour = 1;
                break;
            case FOUR: retour = 4;
                break;
            case SIX: retour = 6;
                break;
            case EIGHT: retour = 8;
                break;
            case TWELVE: retour = 12;
                break;
        }
        return retour;
    }
    public Type getType()
    {
        return Type;
    }

    public int getPrice()
    {
        return Price;
    }


     // Setters


    public void setId(int _id) {
        id = _id;
    }

    public void setName(String _Name)
    {
         Name = _Name;
    }

    public void setSize(pste.simon.aucoeurdenozay.Class.Size _Size)
    {
        Size = _Size;
    }

    public void setSize_Max(pste.simon.aucoeurdenozay.Class.Size _SizeMax)
    {
        Size_Max = _SizeMax;
    }

    public void setType(Type _Type)
    {
        Type = _Type;
    }

    public void setPrice(int _Price)
    {
        Price = _Price;
    }

}

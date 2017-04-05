package pste.simon.aucoeurdenozay.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pste.simon.aucoeurdenozay.Class.Client;
import pste.simon.aucoeurdenozay.Class.Orders;
import pste.simon.aucoeurdenozay.Class.Products;

/**
 * Created by Simon on 31/03/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    //Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "internalDbAuCoeurDeN";

    //Noms des tables

    public static final String PRODUCT_TABLE_NAME = "Produit";
    public static final String CLIENT_TABLE_NAME = "Clients";
    public static final String ORDER_TABLE_NAME = "Commandes";

    public static final String ASSOCIATIVE_TABLE_NAME = "Association";


    // Table Produits

    public static final String PRODUCT_KEY = "IdProduit";
    public static final String PRODUCT_NAME = "NomProduit";
    public static final String PRODUCT_SIZEMAX = "TailleMax";
    public static final String PRODUCT_PRICE = "Prix";


    // Table Client
    public static final String CLIENT_KEY = "IdClient";
    public static final String CLIENT_NAME = "NomClient";
    public static final String CLIENT_PHONE = "NumClient";

    // Table Commandes
    public static final String ORDER_KEY = "IdCommande";
    public static final String ORDER_CLIENT_ID = "Order_IdClient";
    public static final String ORDER_STATE = "EtatCommande";
    public static final String ORDER_DUEDATE = "Date";


    //Creation table produit

    public static final String PRODUCT_TABLE_CREATE =
            "CREATE TABLE " +PRODUCT_TABLE_NAME + " (" +
                    PRODUCT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PRODUCT_NAME + " TEXT, " +
                    PRODUCT_SIZEMAX + " REAL, " +
                    PRODUCT_PRICE + " REAL);";

    //Creation table client

    public static final String CLIENT_TABLE_CREATE =
            "CREATE TABLE " + CLIENT_TABLE_NAME + " (" +
                    CLIENT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CLIENT_NAME + " TEXT, " +
                    CLIENT_PHONE + " REAL);";

    //Creation table order

    public static final String ORDER_TABLE_CREATE =
            "CREATE TABLE " + ORDER_TABLE_NAME + " (" +
                    ORDER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ORDER_CLIENT_ID + " INTEGER, " +
                    ORDER_DUEDATE + " INTEGER, " +
                    ORDER_STATE + " INTEGER);";


    //Creation table associative

    public static final String ASSOCIATIVE_TABLE_CREATE =
            "CREATE TABLE " + ASSOCIATIVE_TABLE_NAME + " (" +
                    ORDER_KEY + " INTEGER , " +
                    PRODUCT_KEY + " INTEGER);";




    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(PRODUCT_TABLE_CREATE);
        db.execSQL(CLIENT_TABLE_CREATE);
        db.execSQL(ORDER_TABLE_CREATE);
        db.execSQL(ASSOCIATIVE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CLIENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ASSOCIATIVE_TABLE_NAME);

        // create new tables
        onCreate(db);
    }

    /**  OPERATIONS DANS LA DB **/

    public long createClient(Client _client)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues line = new ContentValues();

        line.put(CLIENT_NAME, _client.getName());
        line.put(CLIENT_PHONE, _client.getPhone_N());

        long id = db.insert(CLIENT_TABLE_NAME, null, line);

        return id;
    }

    public long createProducts(Products _product)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues line = new ContentValues();

        line.put(PRODUCT_NAME, _product.getName());
        line.put(PRODUCT_SIZEMAX, _product.getIntSize_Max());
        line.put(PRODUCT_PRICE, _product.getPrice());


        long id = db.insert(PRODUCT_TABLE_NAME, null, line);

        return id;
    }

    public long createOrder(Orders _order)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues line = new ContentValues();
        ContentValues lineAssociative = new ContentValues();

        String query_client = "SELECT * FROM "+CLIENT_TABLE_NAME+" WHERE " + CLIENT_NAME + " = " + _order.getClient().getName();
        Cursor v = db.rawQuery(query_client, null);

        if (v != null)
        {
            v.moveToFirst();
            lineAssociative.put(ORDER_CLIENT_ID,v.getColumnIndex(CLIENT_KEY));
        }
        else
        {
            lineAssociative.put(ORDER_CLIENT_ID,_order.getClient().getId());
        }

        line.put(ORDER_STATE, _order.getState());

        long id = db.insert(ORDER_TABLE_NAME, null,line);

        for (int i = 0; i< _order.productList.size(); i++)
        {
            lineAssociative.clear();
            lineAssociative.put(ORDER_KEY, id);

            String query_produit = "SELECT * FROM "+PRODUCT_TABLE_NAME+" WHERE " + PRODUCT_NAME + " = " + _order.productList.get(i).getName();
            Cursor c = db.rawQuery(query_produit, null);

            if (c != null)
                c.moveToFirst();


            lineAssociative.put(PRODUCT_KEY,c.getColumnIndex(PRODUCT_KEY));

            db.insert(ASSOCIATIVE_TABLE_NAME, null, lineAssociative);
        }
        return id;
    }

    public Client getClient(long _id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + CLIENT_TABLE_NAME + " WHERE "
                + CLIENT_KEY + " = " + _id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Client tmp = new Client();
        tmp.setId(c.getInt(c.getColumnIndex(CLIENT_KEY)));
        tmp.setName((c.getString(c.getColumnIndex(CLIENT_NAME))));
        tmp.setPhone_N(c.getInt(c.getColumnIndex(CLIENT_PHONE)));

        return tmp;
    }

    public Orders getOrder(long _id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + ORDER_TABLE_NAME + " WHERE "
                + ORDER_KEY + " = " + _id;


        Cursor c = db.rawQuery(selectQuery, null);


        if (c != null)
            c.moveToFirst();

        Orders tmp = new Orders();
        tmp.setId(c.getInt(c.getColumnIndex(ORDER_KEY)));
        tmp.setState(c.getInt(c.getColumnIndex(ORDER_STATE)));
        tmp.setDueDate(c.getInt(c.getColumnIndex(ORDER_DUEDATE)));

        Client client = new Client();

        selectQuery = "SELECT  * FROM " + CLIENT_TABLE_NAME + " WHERE " + CLIENT_KEY + " = " + c.getColumnIndex(ORDER_CLIENT_ID);
        Cursor c1 = db.rawQuery(selectQuery, null);
        if (c1 != null)
            c1.moveToFirst();

        client.setId(c1.getInt(c1.getColumnIndex(CLIENT_KEY)));

        client.setPhone_N(c1.getInt(c1.getColumnIndex(CLIENT_PHONE)));
        client.setName(c1.getString(c1.getColumnIndex(CLIENT_NAME)));

        tmp.setClient(client);

        selectQuery = "SELECT  * FROM " + ASSOCIATIVE_TABLE_NAME + " WHERE " + ORDER_KEY + " = " + _id;
        Cursor c2 = db.rawQuery(selectQuery, null);
        if (c2.moveToFirst())
        {
            do{
                Products prod = new Products();

                selectQuery = "SELECT * FROM " + PRODUCT_TABLE_NAME + " WHERE " + PRODUCT_KEY + " = " + c2.getString(c2.getColumnIndex(PRODUCT_KEY));
                Cursor c3 = db.rawQuery(selectQuery, null);
                if (c3 != null)
                    c3.moveToFirst();


                prod.setName(c3.getString(c2.getColumnIndex(PRODUCT_NAME)));
                prod.setId(c2.getInt(c2.getColumnIndex(PRODUCT_KEY)));
                //prod.setSize_Max();
                //prod.setPrice();
            }
        }


        tmp.productList
        return tmp;
    }

    public



}

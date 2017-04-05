package pste.simon.aucoeurdenozay.Android_File;

import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import pste.simon.aucoeurdenozay.DB.DBHandler;
import pste.simon.aucoeurdenozay.R;

public class MainActivity extends AppCompatActivity {

    private Button Go_NewOrder = null;
    private Button Go_Calendar = null;
    private Button Go_Settings = null;


    private final static String KEYNEWORDER = "key.new_order";
    private final static String KEYCALENDAR = "key.calendar";
    private final static String KEYSETTINGS = "key.settings";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Go_NewOrder = (Button) findViewById(R.id.New_Order);
        Go_Calendar = (Button) findViewById(R.id.Calendar);
        Go_Settings = (Button) findViewById(R.id.Settings);

        Go_NewOrder.setOnClickListener(Go_NewOrder_Listener);
       // Go_Calendar.setOnClickListener(Go_Calendar_Listener);
        Go_Settings.setOnClickListener(Go_Settings_Listener);
    }

    private OnClickListener Go_NewOrder_Listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            Intent intent = new Intent(MainActivity.this, NewOrder.class);

            startActivity(intent);
        }
    };

    /*private OnClickListener Go_Calendar_Listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            Intent intent = new Intent(MainActivity.this, calendar.class);

            startActivity(intent);
        }
    };*/

    private OnClickListener Go_Settings_Listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            Intent intent = new Intent(MainActivity.this, Settings.class);

            startActivity(intent);
        }
    };
}

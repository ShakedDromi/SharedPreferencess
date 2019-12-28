package com.example.sharedpreferencess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Shaked Dromi
 * @since 10/12/19
 * @version alpha
 */

public class MainActivity extends AppCompatActivity {
    TextView counttv;
    int x=0;
    int y;
    EditText etname;
    String name;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counttv=(TextView) findViewById(R.id.counttv);
        etname=(EditText) findViewById(R.id.etname);

        SharedPreferences settings= getSharedPreferences("prefs_name", MODE_PRIVATE);
        y=settings.getInt("y", -10);
        name=settings.getString("name", "");
        etname.setText(name);
        if(y==-10)
            Toast.makeText(this, "an error was found in the shared files", Toast.LENGTH_SHORT).show();
        else {
            counttv.setText("this is click number:"+y);
            x=y;
        }
    }

    /**
     * set the options menu
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * here you move to credit activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("credits"))) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

    /**
     * this method counts the amount of times the button- 'count' was clicked and puts it in the text view.
     */
    public void count(View view) {
        x++;
        counttv.setText("this is click number:"+x);
    }

    /**
     * this method resets the text view.
     */
    public void reset(View view) {
        x=0;
        counttv.setText("click's count");
    }

    /**
     * this method saves the last data that was entered and exits the app.
     */
    public void exit(View view) {
        String name=etname.getText().toString();
        SharedPreferences settings= getSharedPreferences("prefs_name", MODE_PRIVATE);
        SharedPreferences.Editor editor= settings.edit();
        editor.putInt("y", x);
        editor.putString("name",name);
        editor.commit();
        finish();
    }
}

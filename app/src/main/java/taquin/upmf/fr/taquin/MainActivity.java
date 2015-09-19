package taquin.upmf.fr.taquin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import image.Image2Adapter;


public class MainActivity extends Activity implements View.OnClickListener {

    private Image2Adapter tt ;
    private GridView gridview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button debutant = (Button) findViewById(R.id.button);
        debutant.setOnClickListener(this);

        Button intermediaire = (Button) findViewById(R.id.button2);
        intermediaire.setOnClickListener(this);

        Button expert = (Button) findViewById(R.id.button3);
        expert.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(MainActivity.this, GridActivity.class) ;
        int id = v.getId();

        if (id == R.id.button){
            intent.putExtra("type","deb");
        }
        if (id == R.id.button2){
            intent.putExtra("type","inter");
        }
        if (id == R.id.button3){
            intent.putExtra("type","exp");
        }

        startActivity(intent);

    }

}

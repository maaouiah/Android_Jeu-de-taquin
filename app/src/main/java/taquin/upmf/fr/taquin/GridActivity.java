package taquin.upmf.fr.taquin;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import image.Image2Adapter;


public class GridActivity extends Activity implements AdapterView.OnItemClickListener {

    private Image2Adapter tt ;
    private GridView gridview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        gridview = (GridView) findViewById(R.id.gridview);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String value = extras.getString("type");

            if(  value.equals("deb") ){
                tt = new Image2Adapter(this , 3 , 3 ) ;
                gridview.setColumnWidth(metrics.widthPixels / 3 );
            }

            if ( value.equals("inter") ){
                tt = new Image2Adapter(this , 4 , 4 ) ;
                gridview.setColumnWidth(metrics.widthPixels / 4 );
            }
            if ( value.equals("exp") ){
                tt = new Image2Adapter(this , 5 , 5 ) ;
                gridview.setColumnWidth(metrics.widthPixels / 5 );
            }
            gridview.setAdapter(tt);

        }

        gridview.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        tt.change(position);
        gridview.invalidateViews();
        gridview.setAdapter(tt);

    }
}

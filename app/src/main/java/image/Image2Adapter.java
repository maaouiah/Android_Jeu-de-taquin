package image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

import taquin.upmf.fr.taquin.R;

/**
 * Created by artz on 27/12/14.
 */
public class Image2Adapter extends BaseAdapter {

    private Context mContext;
    private Bitmap img ;
    private Bitmap unlockImg ;
    private Bitmap hiddenImg ;
    private Bitmap[] mThumbIds  ;
    private int[] position = {0 , 0 , 0 , 0 } ;
    private int width ;
    private int height ;
    private int rannul =  0 ;
    private ArrayList<Integer> listeInts = new ArrayList<Integer>();

    public Image2Adapter(Context c , int w , int h ){

        mContext = c;
        img = BitmapFactory.decodeResource(c.getResources(), R.drawable.pp);
        unlockImg = BitmapFactory.decodeResource(c.getResources(), R.drawable.unlock);
        mThumbIds = new Bitmap[ w * h  ];

        this.width  = w ;
        this.height = h ;

        /*  boucle pour générer des valeurs aléatoire */
        for(int i=0;i< ( width * height ) ;i++)
        {
            this.listeInts.add(i);
        }
        Collections.shuffle(this.listeInts);

        /* calcule des dimensions des images */
        int boutWidth  = img.getWidth() / width ;
        int bouth = img.getHeight() / height ;


        /* hidden image */
        Bitmap tmpBitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.unlock);
        unlockImg =  Bitmap.createScaledBitmap(tmpBitmap , boutWidth, bouth, true);

        /* decoupage de la bitmap */
        int r = 0 ;
        for (int i = 0 ; i < width ; i++){
            for ( int j = 0 ; j < height ; j++){
                Bitmap unBout = Bitmap.createBitmap(img,  bouth   *  j   ,  boutWidth  * i   , boutWidth , bouth );
                mThumbIds[listeInts.get(r)] = unBout ;
                r++ ;
            }
        }

        // mettre l'image cachée à null

        hiddenImg = mThumbIds[rannul] ;
        rannul = 6 ;
        mThumbIds[rannul] = unlockImg ;


    }

    public void change( int pos ){

        int  Indice         = 0     ;
        boolean trouver     = false ;
        int positionUp      = 0     ;
        int positionDown    = 0     ;
        int positionLeft    = 0     ;
        int positionRight   = 0     ;
        int orderListe      = 0     ;


        /* calcule des position left & right */
        positionLeft = pos - 1 ;
        positionRight = pos + 1 ;

        if ( positionLeft >= 0  && ( pos % width ) != 0 )
            position[0] = positionLeft ;
        if ( positionRight < ( height * width ) && (( pos + 1 ) % width) != 0 )
            position[1] = positionRight ;

        /* calcule des position up & down */
        positionUp = pos - height ;
        positionDown = pos + height ;

        if ( positionUp >= 0 )
            position[2] = positionUp ;
        if ( positionDown < (height * width) )
            position[3] = positionDown ;


        /*  */
        for ( int i = 0 ; i < position.length ; i++){

                if ( position[i] == rannul  ){
                    trouver = true ;
                    Indice = position[i] ;
                    break;
                }
        }

        /* si le mouvement est possible */
        if ( trouver){
            rannul = pos ;
            mThumbIds[Indice] = mThumbIds[pos] ;
            mThumbIds[pos] = unlockImg ;

            // réorganier le lableau des ordre

            // orderListe = listeInts.get(Indice) ;

            listeInts.set( Indice , pos ) ;
            listeInts.set( pos , pos ) ;
            // listeInts.set( pos , orderListe ) ;

            Log.e("rrr" , listeInts.toString() );
        }



    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mThumbIds[position]);

        return imageView;
    }

}

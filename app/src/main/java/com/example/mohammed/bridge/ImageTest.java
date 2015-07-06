package com.example.mohammed.bridge;



        import android.content.Intent;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Typeface;
        import android.net.Uri;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;

        import android.util.Base64;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.parse.ParseException;
        import com.parse.ParseUser;

        import java.io.ByteArrayOutputStream;
        import java.io.InputStream;

public class ImageTest extends ActionBarActivity {
    ImageView b1,b2;
    ImageView iv;
    private TextView logo;
    private TextView sublogo;
    private TextView location;
    private TextView join,gallery;
    private int rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
       // logo =(TextView) findViewById(R.id.logo);
        sublogo =(TextView) findViewById(R.id.sublogo);
        location =(TextView) findViewById(R.id.location);
        TextView skip = (TextView) findViewById(R.id.skip);
        join =(TextView) findViewById(R.id.enterevent);
        gallery =(TextView) findViewById(R.id.gallery);
        Typeface hmBold=Typeface.createFromAsset(getAssets(),"fonts/hmBold.otf");
        Typeface bariol=Typeface.createFromAsset(getAssets(),"fonts/bariol.otf");
       // logo.setTypeface(hmBold);
        sublogo.setTypeface(bariol);
        location.setTypeface(bariol);
        join.setTypeface(bariol);
        gallery.setTypeface(bariol);
        skip.setTypeface(bariol);
        b1=(ImageView)findViewById(R.id.button);
        b2=(ImageView)findViewById(R.id.button2);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("MAIN");
                Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.def);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                ParseUser currentUser= ParseUser.getCurrentUser();

                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                currentUser.put("profile",encoded);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rc=0;
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
rc=1;

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
if(rc==0){
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        ParseUser currentUser= ParseUser.getCurrentUser();

        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        currentUser.put("profile",encoded);
    try {
        currentUser.save();
    } catch (ParseException e) {
        e.printStackTrace();
    }
    byte[] decodedString = Base64.decode(encoded, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);}
        else if(rc==1){


    if(resultCode == RESULT_OK){
        Uri selectedImage = data.getData();
        try{
        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            ParseUser currentUser= ParseUser.getCurrentUser();
currentUser.save();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            currentUser.put("profile",encoded);
        }
        catch (Throwable t){

            Toast.makeText(getApplicationContext(),
                    "Initializing",
                    Toast.LENGTH_LONG).show();
        }

    }

}




       // iv.setImageBitmap(decodedByte);
        new Thread(new Runnable() {
            public void run() {



                try {
                    // Sleep for 200 milliseconds.
                    //Just to display the progress slowly
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // Intent i= new Intent("MAIN");
             //   startActivity(i);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

        //noinspection SimplifiableIfStatement'
        if (id == R.menu.menu_main) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
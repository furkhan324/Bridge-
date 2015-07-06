package com.example.mohammed.bridge;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.GraphRequest;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class LoginActivity extends Activity {

    private Dialog progressDialog;
    public static Bitmap getFacebookProfilePicture(String userID){
        try{
            URL im = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
            Log.e("TAG", "https://graph.facebook.com/" + userID + "/picture?type=large");
            Bitmap bitmap = BitmapFactory.decodeStream(im.openConnection().getInputStream());

            Log.e("TAG", bitmap.toString());
            return bitmap;
        }
        catch(Exception E){
            Log.d("TAG", "Uh oh. Thme user cancelled the Facebook login1.");
            Log.e("TAG", Log.getStackTraceString(E));
        }
        Log.d("TAG", "Uh oh. The user cancelled the Facebook login2.");
        return null;

    }
    private void makeMeRequest() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        if (jsonObject != null) {
                            JSONObject userProfile = new JSONObject();
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            try {
                                userProfile.put("facebookId", jsonObject.getLong("id"));
                                userProfile.put("name", jsonObject.getString("name"));
                                currentUser.setUsername(jsonObject.getString("name"));

                                if (jsonObject.getString("gender") != null)
                                    userProfile.put("gender", jsonObject.getString("gender"));

                                if (jsonObject.getString("email") != null)
                                    userProfile.put("email", jsonObject.getString("email"));

                                // Save the user profile info in a user property

                                currentUser.put("profilejson", userProfile);
                                try {
                                    currentUser.save();
                                    Bitmap bitmap=getFacebookProfilePicture(userProfile.getString("facebookId"));
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                                    String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                                    currentUser.put("profile",encoded);
                                    LoginActivity.this.progressDialog.dismiss();
                                    Intent i = new Intent(getBaseContext(), MapsActivity.class);
                                    i.putExtra("comingFrom","facebook");
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }




                                // Show the user info
//move to location activity with intent to skip profile picture
                            } catch (JSONException e) {
                                Log.d("TAG",
                                        "Error parsing returned user data. " + e);
                            }
                        } else if (graphResponse.getError() != null) {
                            switch (graphResponse.getError().getCategory()) {
                                case LOGIN_RECOVERABLE:
                                    Log.d("TAG",
                                            "Authentication error: " + graphResponse.getError());
                                    break;

                                case TRANSIENT:
                                    Log.d("TAG",
                                            "Transient error. Try again. " + graphResponse.getError());
                                    break;

                                case OTHER:
                                    Log.d("TAG",
                                            "Some other error: " + graphResponse.getError());
                                    break;
                            }
                        }
                    }
                });

        request.executeAsync();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start);

        // Check if there is a currently logged in user
        // and it's linked to a Facebook account.
     TextView sublogo = (TextView) findViewById(R.id.sublogo);
        TextView signup2 = (TextView) findViewById(R.id.signup2);

        TextView login2 = (TextView) findViewById(R.id.login2);

        Typeface bariol=Typeface.createFromAsset(getAssets(),"fonts/bariol.otf");

        sublogo.setTypeface(bariol);
        login2.setTypeface(bariol);
        signup2.setTypeface(bariol);




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    public void onLoginClick(View v) {
        progressDialog = ProgressDialog.show(LoginActivity.this, "", "Logging in...", true);

        List<String> permissions = Arrays.asList("public_profile", "email");

        // (https://developers.facebook.com/docs/facebook-login/permissions/)

        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {

                if (user == null) {
                    Log.d("TAG", "Uh oh. The user cancelled the Facebook login.");
                    progressDialog.dismiss();
                } else if (user.isNew()) {
                    Log.d("TAG", "User signed up and logged in through Facebook!");
                    makeMeRequest();

                } else {
                    Log.d("TAG", "User logged in through Facebook!");
                    Intent intent = new Intent("MAIN");
                    startActivity(intent);
                }
            }
        });
    }
    public void onSign(View v) {
        Intent intent = new Intent("SIGNUP");
        startActivity(intent);
    }
    public void onLogin(View v) {
        Intent intent = new Intent("LOGIN");
        startActivity(intent);
    }
    private void showUserDetailsActivity() {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        startActivity(intent);
    }
}
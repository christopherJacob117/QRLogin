package vision.qr.com.qrlogin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private DBHelper mydb;
    EditText user,pass;
    Button button,select;
    private String pass1,user1;
    ArrayList<NameValuePair> nameValuePairs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);
        button=(Button)findViewById(R.id.signin);
        select=(Button)findViewById(R.id.select);
try{
         if(getIntent()!=null){

             user1=getIntent().getStringExtra("user");
             pass1=getIntent().getStringExtra("pass");
             user.setText(user1);
             pass.setText(pass1);
         }}catch (Exception e){
    e.printStackTrace();
}
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(!TextUtils.isEmpty(user.getText().toString())&&!TextUtils.isEmpty(pass.getText().toString())){

                  PostToServer();
              }
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectUser.class);
                startActivity(intent);

            }
        });


    }


    private void PostToServer() {
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username", user.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("password", pass.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("qrtag", Constants.Qrtext));
        new BackTask().execute();
    }



    public class BackTask extends AsyncTask<Void, Void, Void> {
        HttpResponse response;
        HttpEntity entity;

        String result = "";
        JSONObject jsonObject;

        String  stat;

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;


            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+Constants.IP+"/qrlogin/qrloginapi.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = httpClient.execute(httpPost);
                entity = response.getEntity();
                is = entity.getContent();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                Log.i("RESPONSE", "-->" + result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                jsonObject = new JSONObject(result);
                stat = jsonObject.getString("status");
                if (stat.equals("success")) {

                }
                // status=jsonObject.getString("status");
                Log.i("jkl", stat);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {

            if (stat.equals("Login Success")) {
                Toast.makeText(getApplicationContext(), "Login Success !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), LauncherActivity.class);

                startActivity(intent);
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Login Failed !", Toast.LENGTH_LONG).show();

        }
    }
}

package vision.qr.com.qrlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWebsite extends AppCompatActivity {

    private DBHelper mydb;
    EditText user,pass;
    Button b;
    private EditText web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_website);

        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        web=(EditText)findViewById(R.id.web);
        b=(Button)findViewById(R.id.save);
        mydb=new DBHelper(getApplicationContext());

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(TextUtils.isEmpty(user.getText().toString())&&TextUtils.isEmpty(pass.getText().toString())))
                {
                    if( mydb.insertData(user.getText().toString(),pass.getText().toString(),web.getText().toString())){


                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                        user.setText("");
                    }else {

                        Toast.makeText(getApplicationContext(),"Insertion error",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please insert valid details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

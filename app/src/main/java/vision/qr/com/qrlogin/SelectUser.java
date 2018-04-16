package vision.qr.com.qrlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectUser extends AppCompatActivity {
    ListView list;
    DBHelper db;
    ArrayList<User> g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        list=(ListView)findViewById(R.id.list);
        db=new DBHelper(getApplicationContext());
        g=db.getAllCotacts();
        ListAdapter c=new ListAdapter(SelectUser.this,g);
        list.setAdapter(c);

       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("TAG","click");

                Intent intent=new Intent(view.getContext(), Login.class);
                intent.putExtra("user",g.get(i).getUser());
                intent.putExtra("pass",g.get(i).getPass());
                view.getContext().startActivity(intent);

            }
        });*/
    }
}

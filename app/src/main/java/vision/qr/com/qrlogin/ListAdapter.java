package vision.qr.com.qrlogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class ListAdapter extends BaseAdapter implements OnClickListener {

    private static LayoutInflater inflater = null;
    public Resources res;

    int i = 0, j = 0;
    ProgressDialog progress;
    Object p;
    View view;
    /***********
     * Declare Used Variables
     *********/
    private Activity activity;
    private ArrayList<User> data;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;

    }


    /*************
     * SpendAdapter Constructor
     *****************/
    public ListAdapter(Activity a, ArrayList<User> d) {

        /********** Take passed values **********/
        activity = a;
        data = d;

        /*********** Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /********
     * What is the size of Passed Arraylist Size
     ************/
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    /******
     * Depends upon data size called for each row , Create each ListView row
     *****/
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos;
        pos = position;
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_item, null);
            /****** View Holder Object to contain tabitem.xml file elements ******/


            holder = new ViewHolder();
            holder.name = (TextView) vi.findViewById(R.id.name);
            holder.pass = (TextView) vi.findViewById(R.id.password);
            holder.web= (TextView) vi.findViewById(R.id.web);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();


        if (data.size() <= 0) {
            //holder.tv2.setText("No Data");
            vi.setVisibility(View.GONE);

            // holder.imei_no.setVisibility(View.GONE);
        } else {
            j = j + 1;
            /***** Get each Model object from Arraylist ********/

            holder.name.setText(data.get(position).getUser());
            holder.pass.setText(data.get(position).getPass());
            holder.web.setText(data.get(position).getWeb());


            vi.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(), Login.class);
                    intent.putExtra("user",data.get(i).getUser());
                    intent.putExtra("pass",data.get(i).getPass());
                    view.getContext().startActivity(intent);

                }
            });

        }
        return vi;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView name,pass, web, bacc,date;

    }


}
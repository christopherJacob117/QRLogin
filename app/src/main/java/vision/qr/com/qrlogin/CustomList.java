package vision.qr.com.qrlogin;

/**
 * Created by RogueRay on 3/3/18.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends BaseAdapter
{
    Activity context;
    ArrayList<User>d;

    public CustomList(Activity context,ArrayList<User>u) {
        super();
        this.context = context;
        this.d=u;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return d.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtViewTitle;
        TextView txtViewDescription;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;

        View vi=convertView;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtViewTitle = (TextView) vi.findViewById(R.id.name);
            holder.txtViewDescription = (TextView) vi.findViewById(R.id.password);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtViewTitle.setText(d.get(position).getUser());
        holder.txtViewDescription.setText(d.get(position).getPass());


        return convertView;
    }

}
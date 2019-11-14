package com.apps.cyberfox_ethicalhackingguide;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<CommandNames> commandNamesList = null;
    private ArrayList<CommandNames> arraylist;

    public ListViewAdapter(Context context, List<CommandNames> commandNamesList) {
        mContext = context;
        this.commandNamesList = commandNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<CommandNames>();
        this.arraylist.addAll(commandNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return commandNamesList.size();
    }

    @Override
    public CommandNames getItem(int position) {
        return commandNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(commandNamesList.get(position).getCommandName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        commandNamesList.clear();
        if (charText.length() == 0) {
            commandNamesList.addAll(arraylist);
        } else {
            for (CommandNames wp : arraylist) {
                if ( wp.getCommandName()!=null && wp.getCommandName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    commandNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
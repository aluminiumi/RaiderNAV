package com.deaftone.tableware.raidernav;

/* This is used by ScheduleActivity to do the list of schedules */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class InteractiveMasterListArrayAdapter extends ArrayAdapter<ScheduleEntryList> {

    public List<ScheduleEntryList> list;
    private final Activity context;

    public InteractiveMasterListArrayAdapter(Activity context, List<ScheduleEntryList> list) {
        //super(context, R.layout.rowbuttonlayout, list);
        super(context, R.layout.activity_schedule, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.rowbuttonlayout, null);
            //view = inflator.inflate(R.layout.activity_schedule, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("IMLAA: getView: onClickListener: creating SELActivity and sending position "+position);
                    context.startActivityForResult(new Intent(context.getApplicationContext(),ScheduleEntryListActivity.class).putExtra("index", position), 0);
                }
            });
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            ScheduleEntryList element = (ScheduleEntryList) viewHolder.checkbox
                                    .getTag();
                            element.setEnabled(buttonView.isChecked());

                        }
                    });
            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.text.setText(list.get(position).getName());
        holder.checkbox.setChecked(list.get(position).isEnabled());

        return view;
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.
                System.out.println(RESULT_OK);
                //startActivity(new Intent(Intent.ACTION_VIEW, data));
            }
        }
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged(){
        //this.list = null;
        super.notifyDataSetChanged();
    }
}
package com.deaftone.tableware.raidernav;

/* This is used by ScheduleActivity to do the list of schedules */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<ScheduleEntryList> {

    private final List<ScheduleEntryList> list;
    private final Activity context;

    public InteractiveArrayAdapter(Activity context, List<ScheduleEntryList> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            System.out.println("getview: convertview is null");
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.rowbuttonlayout, null);
            //view = inflator.inflate(R.layout.activity_schedule, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
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
            System.out.println("getview: convertview is not null");
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        System.out.println("getview: list.get(position): "+list.get(position).toString());
        System.out.println("getview: list.get(position).getName(): "+list.get(position).getName());
        System.out.println("getview: list.get(position).isEnabled(): "+list.get(position).isEnabled());
        holder.text.setText(list.get(position).getName());
        holder.checkbox.setChecked(list.get(position).isEnabled());
        System.out.println(holder.text.getText());
        System.out.println(holder.checkbox.isChecked());
        return view;
    }
}
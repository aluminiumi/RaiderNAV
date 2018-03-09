package com.deaftone.tableware.raidernav;

/* This is used by ScheduleActivity to do the list of schedules */

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.deaftone.tableware.raidernav.Activities.ScheduleSingleEntryActivity;

import static android.app.Activity.RESULT_OK;

public class InteractiveSELArrayAdapter extends ArrayAdapter<ScheduleSingleEntry> {

    public ScheduleEntryList list;
    private final Activity context;
    private int index;

    public InteractiveSELArrayAdapter(Activity context, ScheduleEntryList list) {
        super(context, R.layout.activity_schedule, list.toArrayList());
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView name;
        protected TextView building;
        protected TextView startAndEndTime;
        protected CheckBox checkbox;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.courserowlayout, null);
            //view = inflator.inflate(R.layout.activity_schedule, null);
            final ViewHolder viewHolder = new ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.courseText);

            viewHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent schedintent = new Intent(context.getApplicationContext(),ScheduleSingleEntryActivity.class);
                    System.out.println("ISELAA: getView: putting index "+index+" in schedintent");
                    schedintent.putExtra("entryindex", position); //currently selected item
                    schedintent.putExtra("index", index); //index clicked on previous activity
                    context.startActivityForResult(schedintent, 0);
                }
            });
            viewHolder.building = (TextView) view.findViewById(R.id.buildingText);
            viewHolder.building.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent schedintent = new Intent(context.getApplicationContext(),ScheduleSingleEntryActivity.class);
                    schedintent.putExtra("entryindex", position); //currently selected item
                    schedintent.putExtra("index", index); //index clicked on previous activity
                    context.startActivityForResult(schedintent, 0);
                }
            });
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.startAndEndTime = (TextView) view.findViewById(R.id.concatTimesText);
            viewHolder.startAndEndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent schedintent = new Intent(context.getApplicationContext(),ScheduleSingleEntryActivity.class);
                    schedintent.putExtra("entryindex", position); //currently selected item
                    schedintent.putExtra("index", index); //index clicked on previous activity
                    context.startActivityForResult(schedintent, 0);
                }
            });
            /*viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            ScheduleEntryList element = (ScheduleEntryList) viewHolder.checkbox
                                    .getTag();
                            element.setEnabled(buttonView.isChecked());

                        }
                    });*/
            view.setTag(viewHolder);
            //viewHolder.checkbox.setTag(list.getEntry(position));
        } else {
            view = convertView;
            //((ViewHolder) view.getTag()).checkbox.setTag(list.getEntry(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.name.setText(list.getEntry(position).getCourseNumber());
        holder.building.setText(list.getEntry(position).getBuilding());
        String timetext = list.getEntry(position).getStartTime()+" - "+list.getEntry(position).getEndTime();
        holder.startAndEndTime.setText(timetext);
        //holder.checkbox.setChecked(list.getEntry(position));

        return view;
    }

    public void setIndex(int i) {
        index = i;
    }

    public int getIndex() {
        return index;
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
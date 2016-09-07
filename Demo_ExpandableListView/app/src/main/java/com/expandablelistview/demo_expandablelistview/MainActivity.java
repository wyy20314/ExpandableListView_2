package com.expandablelistview.demo_expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 可扩展的Listview
 */
public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView)findViewById
                (R.id.expandablelistview);
        expandableListView.setAdapter(new MyExpandableAdapter());

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this,childs[groupPosition][childPosition],Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private  String[] groups = {"好友","扣丁学堂"};
    private  String[][] childs = {{"猪哥","阿克","黄金卡合适的"},{"老鬼","威哥","安可加快"}};
    /*
     *适配器
     */
    class  MyExpandableAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childs[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.group_layout,null);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview_icon);
            TextView textView = (TextView)convertView.findViewById(R.id.textview_title);
            //imageView.setImageResource();
            textView.setText(groups[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.child_layout,null);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview_icon);
            TextView textView = (TextView)convertView.findViewById(R.id.textview_title);
            //imageView.setImageResource();
            textView.setText(childs[groupPosition][childPosition]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}

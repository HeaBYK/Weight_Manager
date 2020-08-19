package com.example.weight_manager.handlerAndAdapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.weight_manager.R;
import com.example.weight_manager.getterSetter.Work;
import com.example.weight_manager.getterSetter.WorkMenu;

import java.util.ArrayList;


public class MyExpandableAdapter implements ExpandableListAdapter {
    Context context = null;
    ArrayList<WorkMenu> originalWorkMenuList;
    ArrayList<WorkMenu> workMenuList;

    public MyExpandableAdapter(Context context, ArrayList<WorkMenu> data) {
        this.context = context;
        this.originalWorkMenuList = new ArrayList<WorkMenu>();
        this.originalWorkMenuList.addAll(data);

        this.workMenuList = new ArrayList<WorkMenu>();
        this.workMenuList.addAll(data);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public boolean isEmpty() {
        if (workMenuList.size() == 0)
            return true;
        else
            return false;
    }

    @Override
    public int getGroupCount() {
        return workMenuList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return workMenuList.get(groupPosition).getWork().size();
    }

    @Override
    public WorkMenu getGroup(int groupPosition) {

        return workMenuList.get(groupPosition);
    }

    @Override
    public Work getChild(int groupPosition, int childPosition) {
        return workMenuList.get(groupPosition).getWork().get(childPosition);
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
        return true;
    }

    @Override
    public View getGroupView(int position, boolean b, View contentView, ViewGroup parent) {

        WorkMenu workMenu = workMenuList.get(position);
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.list_group, parent, false);
        }
        TextView tvContinentName = (TextView) contentView.findViewById(R.id.tvContinentName);
        tvContinentName.setText(workMenu.getName());


        return contentView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View contentView, ViewGroup parent) {
        Work work = workMenuList.get(groupPosition).getWork().get(childPosition);
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }
        TextView tvWorkName = (TextView) contentView.findViewById(R.id.tvWorkName);
        TextView tvWorkUrl = (TextView) contentView.findViewById(R.id.tvWorkUrl);
        ImageView ivWorkImg = (ImageView) contentView.findViewById(R.id.ivWorkImg);
        tvWorkName.setText(work.getName());
        tvWorkUrl.setText(work.getUrl());
        Glide.with(context).load(work.getFlag()).into(ivWorkImg);
        return contentView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return l1;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return l;
    }
}
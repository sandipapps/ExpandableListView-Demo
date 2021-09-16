package com.sandipbhattacharya.expandablelistviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mobileCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGroupList();
        createCollection();
        expandableListView = findViewById(R.id.elvMobiles);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, mobileCollection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i,i1).toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void createCollection() {
        String[] samsungModels = {"Samsung Galaxy M21", "Samsung Galaxy F41",
                "Samsung Galaxy M51", "Samsung Galaxy A50s"};
        String[] googleModels = {"Pixel 4 XL", "Pixel 3a", "Pixel 3 XL", "Pixel 3a XL",
                "Pixel 2", "Pixel 3"};
        String[] redmiModels = {"Redmi 9i", "Redmi Note 9 Pro Max", "Redmi Note 9 Pro"};
        String[] vivoModels = {"Vivo V20", "Vivo S1 Pro", "Vivo Y91i", "Vivo Y12"};
        String[] nokiaModels = {"Nokia 5.3", "Nokia 2.3", "Nokia 3.1 Plus"};
        String[] motorolaModels = { "Motorola One Fusion+", "Motorola E7 Plus", "Motorola G9"};
        mobileCollection = new HashMap<String, List<String>>();
        for(String group : groupList){
            if (group.equals("Samsung")){
                loadChild(samsungModels);
            } else if (group.equals("Google"))
                loadChild(googleModels);
            else if (group.equals("Redmi"))
                loadChild(redmiModels);
            else if (group.equals("Vivo"))
                loadChild(vivoModels);
            else if (group.equals("Nokia"))
                loadChild(nokiaModels);
            else
                loadChild(motorolaModels);
            mobileCollection.put(group, childList);
        }
    }

    private void loadChild(String[] mobileModels) {
        childList = new ArrayList<>();
        for(String model : mobileModels){
            childList.add(model);
        }
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("Samsung");
        groupList.add("Google");
        groupList.add("Redmi");
        groupList.add("Vivo");
        groupList.add("Nokia");
        groupList.add("Motorola");
    }
}
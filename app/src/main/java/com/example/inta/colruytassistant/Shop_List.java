package com.example.inta.colruytassistant;

import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.graphics.PorterDuff.*;

public class Shop_List extends AppCompatActivity {

    DbHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list_layout);

        dbHelper = new DbHelper(this);
        listView = findViewById(R.id.onelist);

        loadShoppingList();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);

        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_add_list:
                final EditText itemEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add new product")
                        .setMessage("Enter next item")
                        .setView(itemEditText)
                        .setPositiveButton("Add item", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String list = String.valueOf(itemEditText.getText());
                                dbHelper.insertList(list);
                                loadShoppingList();


                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();

                dialog.show();


                return true;


        }

        return super.onOptionsItemSelected(item);
    }


    private void loadShoppingList() {


        ArrayList<String> shopList = dbHelper.getList();
        if (mAdapter==null){

            mAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.listItemName, shopList);
            listView.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(shopList);
            mAdapter.notifyDataSetChanged();


        }
    }





    public void deleteItem(View view){

        View parent =  (View)view.getParent();
        TextView listText = (TextView)parent.findViewById(R.id.listItemName);
        String list = String.valueOf(listText.getText());
        dbHelper.deleteList(list);
        loadShoppingList();


    }
}

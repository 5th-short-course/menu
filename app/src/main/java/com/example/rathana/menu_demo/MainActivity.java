package com.example.rathana.menu_demo;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnContextMenu,btnContextualActionMode,btnPopupMenu;
    private ActionMode mActionMode;
    private ActionMode.Callback mCallback = new ActionMode.Callback() {

        //inflate menu
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.download:
                    // TODO: 8/4/2018
                    showMessage("Downloading");
                    return true;
                case R.id.share :
                    showMessage("sharing");
                    return true;
                case R.id.remove:
                    showMessage("remove");
                    return true;
                case R.id.edit :
                    showMessage("edit");
                    return true;

                default:return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode=null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        btnContextMenu=findViewById(R.id.btnContextMenu);
        btnContextualActionMode=findViewById(R.id.btnContextualActionMode);
        btnPopupMenu=findViewById(R.id.btnPopupMenu);
        registerForContextMenu(btnContextMenu);
        btnContextualActionMode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mActionMode!=null)
                    return true;

                mActionMode=MainActivity.this.startActionMode(mCallback);
                v.setSelected(true);
                return true;
            }
        });

        //create popupMenu here
        btnPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu =new PopupMenu(MainActivity.this,v);
                menu.inflate(R.menu.popup_menu);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.copy:
                                    showMessage("copy");
                                return true;
                            case R.id.cut:
                                showMessage("cut");
                                return true;
                            case R.id.past:
                                showMessage("past");
                                return true;

                            default: return false;
                        }
                    }
                });

                menu.show();
            }
        });
    }

    /*
    * create context menu item
    * */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.download:
                // TODO: 8/4/2018
                showMessage("Downloading");
                return true;
            case R.id.share :
                showMessage("sharing");
                return true;
            case R.id.remove:
                showMessage("remove");
                return true;
            case R.id.edit :
                showMessage("edit");
                return true;

            default:return super.onContextItemSelected(item);
        }

    }

    void showMessage(String smg){
        Toast.makeText(this, smg, Toast.LENGTH_SHORT).show();
    }

    /*
     * create option menu item
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_with_icon,menu);

        MenuItem menuItem= menu.findItem(R.id.search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showMessage(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e(TAG, "onQueryTextChange: " +newText );
                return false;
            }
        });
        return true;
    }

    private static final String TAG = "MainActivity";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newTab:
                    textView.setText("new Tab clicked");
                return true;
            case R.id.privateTab:
                textView.setText("new Private Tab clicked");
                return true;
            case R.id.bookmark:
                textView.setText("bookmark clicked");
                return true;
            case R.id.history:
                textView.setText("history clicked");
                return true;
                default: return super.onOptionsItemSelected(item);
        }
    }
}

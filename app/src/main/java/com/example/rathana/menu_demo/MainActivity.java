package com.example.rathana.menu_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

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

package com.ranzan.receivenameandage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView name;
    private TextView age;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=localBroadcastManager.getInstance(this);
        btn = findViewById(R.id.btnTV);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        registerLocalReceiver();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ranzan");
                intent.putExtra("name", "ranzan");
                intent.putExtra("age", "23");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }


    private void registerLocalReceiver() {
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter("ranzan");
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    private class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String Name = intent.getStringExtra("name");
                String Age = intent.getStringExtra("age");
                name.setText(Name);
                age.setText(Age);
            }
        }
    }
}
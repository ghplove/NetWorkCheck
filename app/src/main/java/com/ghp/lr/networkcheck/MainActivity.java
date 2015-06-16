package com.ghp.lr.networkcheck;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ghp.lr.networkcheck.task.DigTask;
import com.ghp.lr.networkcheck.task.PingTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button testbtn;
    private TextView pingResult;
    private TextView digResult;
    private static ExecutorService sExecutor =  Executors.newFixedThreadPool(2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

   private void initView(){
       testbtn= (Button) findViewById(R.id.testbtn);
       testbtn.setOnClickListener(this);
       pingResult= (TextView) findViewById(R.id.pingResult);
       digResult= (TextView) findViewById(R.id.digResult);
   }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.testbtn:
                pingResult.setText("");
                digResult.setText("");
                DigTask digUtils  = new DigTask(digResult);
                digUtils.executeOnExecutor(sExecutor);
                PingTask pingUtils  = new PingTask(pingResult);
                pingUtils.executeOnExecutor(sExecutor);
                break;
            default:
                break;
        }
    }
}

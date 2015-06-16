package com.ghp.lr.networkcheck.task;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import com.ghp.lr.networkcheck.MainActivity;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class DigTask extends AsyncTask {

    public TextView textView;

    public DigTask(TextView textView) {
        super();
        this.textView = textView;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("=====ip dig=====").append("\n");
        try {//解析域名或者ip
            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
            if (addresses == null || addresses.length <= 0) {
                stringBuffer.append("解析结果为空").append("\n");
            } else {
                for (InetAddress address : addresses) {
                    stringBuffer.append(address.getHostAddress()).append("\n");
                }
            }
            return stringBuffer;
        } catch (UnknownHostException e) {
            stringBuffer.append("\n").append(e).append("\n");
        }
        return stringBuffer;
    }

    @Override
    protected void onPostExecute(Object o) {
        StringBuffer sb = new StringBuffer();
        sb.append("DigTask:");

        if (o == null) {
            sb.append("\n");
            sb.append("所有ip dig 失败").append("\n");
            textView.setText(sb);
        } else {
            StringBuffer stringBuffer = (StringBuffer) o;
            if (stringBuffer.length() <= 0) {
                sb.append("\n");
                sb.append("所有ip dig 失败").append("\n");
                textView.setText(sb);
            } else {
                sb.append(stringBuffer).append("\n");
                textView.setText(sb);
            }
        }
    }
}

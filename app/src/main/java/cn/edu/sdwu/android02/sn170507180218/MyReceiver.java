package cn.edu.sdwu.android02.sn170507180218;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //当接收到广播时，自动调用本方法
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String content=intent.getStringExtra("key1");
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
}

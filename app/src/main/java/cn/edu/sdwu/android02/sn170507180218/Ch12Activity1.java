package cn.edu.sdwu.android02.sn170507180218;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class Ch12Activity1 extends AppCompatActivity {
    private ServiceConnection serviceConnection;
    private MyService2 myService2;
    private boolean bindSucc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch12_1);
        bindSucc=false;
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                //当调用者与服务建立起连接后，会自动调用本方法
                //第2个参数，是service中onbind方法的返回值
                MyService2.MyBinder myBinder=(MyService2.MyBinder) iBinder;
                myService2=myBinder.getRandomService();
                bindSucc=true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //当调用者与服务断开连接后，会自动调用本方法
                bindSucc=false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,MyService2.class);
        //绑定
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //解绑
        unbindService(serviceConnection);
    }

    public void start_service(View view){
        //使用intent启动服务
        Intent intent=new Intent(this,MyService.class);
        //使用startService以启动方式使用服务
        startService(intent);
    }

    public void stop_service(View view){
        //停止服务
        Intent intent=new Intent(this,MyService.class);
        stopService(intent);
    }

    public void getRandom(View view){
        if(bindSucc){
            int ran=myService2.genRandom();
            Toast.makeText(this,ran+"",Toast.LENGTH_LONG).show();
        }
    }
}

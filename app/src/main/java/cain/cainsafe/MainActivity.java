package cain.cainsafe;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private String Tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.bt_simulator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkDeviceIDS = SafeUtils.CheckDeviceIDS(MainActivity.this);
                Log.e(Tag,"->"+checkDeviceIDS);
                Boolean checkEmulatorBuild = SafeUtils.CheckEmulatorBuild(MainActivity.this);
                Log.e(Tag,"->"+checkEmulatorBuild);
                Boolean checkEmulatorFiles = SafeUtils.CheckEmulatorFiles();
                Log.e(Tag,"->"+checkEmulatorFiles);
            }
        });



        Log.e(Tag, "getSignature==" + getSignature("cain.cainsafe"));
        if (getSignature("cain.cainsafe") != 191077279) {
            Log.e(Tag, "被重新打包了");
        }

    }


    private int getSignature(String packageName) {
        PackageManager packageManager = this.getPackageManager();
        PackageInfo info = null;
        int sig = 0;
        try {
            info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature[] signatures = info.signatures;
            sig = signatures[0].hashCode();
        } catch (Exception e) {
            sig = 0;
            e.printStackTrace();
        }

        return sig;
    }

}

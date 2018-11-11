package customdialog.example.com.customdialog;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import customdialog.example.com.customdialogboxandroid.CustomFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();

        String permissionReason = "App would like to request for access to your contact list to enable you to find friends on the app";
        CustomFragment cf = CustomFragment.newInstance(permissionReason,"","","","","");
        cf.show(fm, "fragment_edit_name");


    }
}

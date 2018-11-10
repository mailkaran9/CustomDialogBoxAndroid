package customdialog.example.com.customdialogboxandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CustomFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class CustomFragment extends DialogFragment {
    private TextView textViewPermission,title;
    private String reason,buttonName;
    private String buttonColor,bgColor,buttonTextColor,reasonTextColor;
    private OnFragmentInteractionListener mListener;

    public CustomFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static CustomFragment newInstance(String reason, String bName, String bColor, String layoutBgColor,String buttonTextColor, String reasonTextColor) {
        CustomFragment frag = new CustomFragment();
        Bundle args = new Bundle();
        args.putString("reason", reason);
        args.putString("buttonName",bName);
        args.putString("buttonColor",bColor);
        args.putString("backgroundColor",layoutBgColor);
        args.putString("buttonTextColor",buttonTextColor);
        args.putString("reasonTextColor",reasonTextColor);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            reason = getArguments().getString("reason") +"\n";
            buttonName = getArguments().getString("buttonName");
            buttonColor = getArguments().getString("buttonColor");
            bgColor = getArguments().getString("backgroundColor");
            buttonTextColor = getArguments().getString("buttonTextColor");
            reasonTextColor = getArguments().getString("reasonTextColor");

        }
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setGravity(Gravity.CENTER);
        if (bgColor!=null && bgColor!=""){
            try {
                int lbgColor = Color.parseColor(bgColor);

                window.getDecorView().setBackgroundColor(lbgColor);
                //dismissButton.setBackgroundColor(lbgColor);
            }catch (IllegalArgumentException e){
            }
        }
        else{
            window.getDecorView().setBackgroundColor(Color.parseColor("#003450"));
        }

        dialog.setContentView(R.layout.fragment_custom);

        dialog.setCanceledOnTouchOutside(false);

        textViewPermission = dialog.findViewById(R.id.reason);
        textViewPermission.setText(reason);
        Button dismissButton = dialog.findViewById(R.id.dismiss);
        if (buttonColor!=null && buttonColor!=""){
            try {
                int bColor = Color.parseColor(buttonColor);
                dismissButton.setBackgroundColor(bColor);
            }catch (IllegalArgumentException e){

            }
        }
        if (buttonName!=null && buttonName!=""){
            try {
                dismissButton.setText(buttonName);
            }catch (IllegalArgumentException e){

            }
        }
        if (buttonTextColor!=null && buttonTextColor!=""){
            try {
                int bColor = Color.parseColor(buttonTextColor);
                dismissButton.setTextColor(bColor);
            }catch (IllegalArgumentException e){

            }
        }
        if (reasonTextColor!=null && reasonTextColor!=""){
            try {
                int rColor = Color.parseColor(reasonTextColor);
                textViewPermission.setTextColor(rColor);
            }catch (IllegalArgumentException e){

            }
        }
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return dialog;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

}
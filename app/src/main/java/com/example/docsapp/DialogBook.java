package com.example.docsapp;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

public class DialogBook extends DialogFragment {
    private int CALL_PERMISSION_CODE = 1;
    private Button buttonCall,buttonWhatsapp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_form_book_test,container,false);

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.bg_support_ll);
        buttonCall = view.findViewById(R.id.call_button);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {


                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + "+91 7348313151"));
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);

                    }


                } else {
                    requestCallPermission();
                }
            }
        });
        buttonWhatsapp = view.findViewById(R.id.whatsapp_button);
        buttonWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWhatsApp("+917348313151");
            }
        });
        return view;
    }


    private void requestCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CALL_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission GRANTED", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "+91 7348313151"));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);

                }


            } else {
                Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void openWhatsApp(String number) {
        try {
            number = number.replace(" ", "").replace("+", "");

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number)+"@s.whatsapp.net");
            // getApplication().startActivity(sendIntent);

            startActivity(Intent.createChooser(sendIntent, "Compartir en")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        } catch(Exception e) {
            Log.e("WS", "ERROR_OPEN_MESSANGER"+e.toString());
        }
    }
}

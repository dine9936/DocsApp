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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    ActivityNavigator navigator;
    BottomNavigationView navView;
    Toolbar toolbar;
    NavigationView navigationView;
    LinearLayout layout;
    private int CALL_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        findViewById(R.id.select_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectLoactionActivity.class);
                startActivity(intent);
            }
        });
        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigation_drawer);


        navigationView.findViewById(R.id.drawer_cash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, CashAct.class);
                startActivity(intent);
            }
        });
        navigationView.findViewById(R.id.drawer_coupons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, CouponsAct.class);
                startActivity(intent);            }
        });


        navigationView.findViewById(R.id.drawer_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, RecordAct.class);
                startActivity(intent);            }
        });


        navigationView.findViewById(R.id.drawer_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, TypelabtestAct.class);
                startActivity(intent);            }
        });



       layout= navigationView.findViewById(R.id.ll_contact_us_list);
        navigationView.findViewById(R.id.drawer_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                drawer.closeDrawer(GravityCompat.START);
//                Intent intent = new Intent(MainActivity.this, ContactAct.class);
//                startActivity(intent);



                if (layout.getVisibility()==View.VISIBLE){
                    layout.setVisibility(View.GONE);



                }
                else {
                    layout.setVisibility(View.VISIBLE);

                }


            }
        });

        findViewById(R.id.call_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {


                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + "+91 7348313151"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);

                    }


                } else {
                    requestCallPermission();
                }
                }

        });

        findViewById(R.id.support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this,Support.class);
                startActivity(intent);
            }
        });


        navigationView.findViewById(R.id.drawer_header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);            }
        });

        navigationView.findViewById(R.id.terms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, TermsAct.class);
                startActivity(intent);
            }
        });

        navigationView.findViewById(R.id.whatsapp_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWhatsApp("+917348313151");
            }
        });
        navigationView.findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, PrivacyAct.class);
                startActivity(intent);
            }
        });

        navigationView.findViewById(R.id.drawer_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Log Out ")
                        .setMessage("Do you want to logout?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                auth.signOut();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                        .show();
            }
        });

        navView = findViewById(R.id.bottomNavigationView);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.consultsFragment,
                R.id.rewardFragment,
                R.id.familyFragment

        )
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {


            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if ((destination.getId() == R.id.cashFragment)) {
                    toolbar.setVisibility(View.GONE);
                    //toolbarnav.setVisibility(View.VISIBLE);
                    navView.setVisibility(View.GONE);
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                    drawer.setPadding(5, 5, 5, 20);


                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    // toolbarnav.setVisibility(View.GONE);
                    navView.setVisibility(View.VISIBLE);
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

                }
            }
        });


    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        if (menuItem.getItemId() == R.id.action_query) {
//
//            Intent intent = new Intent(this, Support.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(menuItem);
//    }
private void requestCallPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
            Manifest.permission.CALL_PHONE)) {
        new AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permission is needed because of this and that")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
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
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
    }
}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CALL_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "+91 7348313151"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

                }


            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
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
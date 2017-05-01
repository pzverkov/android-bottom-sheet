package com.sc.bottomsheet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(
                R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetFragment();
            }
        });

        Snackbar.make(fab, getString(R.string.press_to_open_bottom_sheet),
                Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.action_snack), null).show();
    }

    public void showBottomSheetFragment() {
        BottomSheetDialogFragmentCustom fragment = new BottomSheetDialogFragmentCustom();
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }
}

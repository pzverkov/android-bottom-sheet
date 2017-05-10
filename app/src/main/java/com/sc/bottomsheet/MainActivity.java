package com.sc.bottomsheet;

import com.sc.bottomsheet.ui.BottomSheetDialogCustomBottom;
import com.sc.bottomsheet.ui.BottomSheetLayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BottomSheetLayout bottomSheetLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Snackbar.make(fab, getString(R.string.press_to_open_bottom_sheet),
                Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.action_snack), null).show();
    }

    private void initViews() {
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(
                R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetFragment();
            }
        });
    }

    public void showBottomSheetFragment() {
            BottomSheetDialogCustomBottom fragment = new BottomSheetDialogCustomBottom();
            fragment.show(getSupportFragmentManager(), fragment.getTag());
    }

    public void share(View v){
        BottomSheetHelper.showIntentDialogLazy(MainActivity.this,
                bottomSheetLayout);
    }

    public void dialog(View v){
        BottomSheetHelper.showMenuDialogLazy(MainActivity.this,
                bottomSheetLayout);
    }

}

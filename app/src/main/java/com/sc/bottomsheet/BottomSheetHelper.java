package com.sc.bottomsheet;

import java.util.Comparator;

import com.sc.bottomsheet.ui.BottomSheetLayout;
import com.sc.bottomsheet.ui.IntentPickerSheetView;
import com.sc.bottomsheet.ui.MenuSheetView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Peter on 09.05.2017.
 */

public class BottomSheetHelper {

    private static Intent generateFakeIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "BlablaText");
        shareIntent.setType("text/plain");
        return shareIntent;
    }

    private static IntentPickerSheetView.Filter generateFilter() {
        return new IntentPickerSheetView.Filter() {
            @Override
            public boolean include(IntentPickerSheetView.ActivityInfo info) {
                return !info.componentName.getPackageName()
                        .startsWith("com.android");
            }
        };
    }

    private static Comparator<IntentPickerSheetView.ActivityInfo> generateComparator() {
        return new Comparator<IntentPickerSheetView.ActivityInfo>() {
            @Override
            public int compare(IntentPickerSheetView.ActivityInfo lhs,
                    IntentPickerSheetView.ActivityInfo rhs) {
                return rhs.label.compareTo(lhs.label);
            }
        };
    }

    public static void showIntentDialog(final AppCompatActivity appCompatActivity,
            final BottomSheetLayout bottomSheetLayout, final Intent shareIntent,
            final IntentPickerSheetView.Filter pickerSheetFilter,
            final Comparator<IntentPickerSheetView.ActivityInfo> comparatorRule) {

        IntentPickerSheetView intentPickerSheet = new IntentPickerSheetView(
                appCompatActivity, shareIntent, "Share with...",
                new IntentPickerSheetView.OnIntentPickedListener() {
                    @Override
                    public void onIntentPicked(
                            IntentPickerSheetView.ActivityInfo activityInfo) {
                        bottomSheetLayout.dismissSheet();
                        appCompatActivity.startActivity(
                                activityInfo.getConcreteIntent(shareIntent));
                    }
                });
        // Filter out built in sharing options such as bluetooth and beam.
        intentPickerSheet.setFilter(pickerSheetFilter);
        // Sort activities in reverse order for no good reason
        intentPickerSheet.setSortMethod(comparatorRule);
        bottomSheetLayout.showWithSheetView(intentPickerSheet);
    }

    public static void showIntentDialogLazy(AppCompatActivity appCompatActivity,
            BottomSheetLayout bottomSheetLayout) {
        showIntentDialog(appCompatActivity, bottomSheetLayout,
                generateFakeIntent(), generateFilter(), generateComparator());
    }

    private static MenuSheetView.OnMenuItemClickListener generateMenuItemClickListener(
            final AppCompatActivity appCompatActivity,
            final BottomSheetLayout bottomSheetLayout) {
        return new MenuSheetView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(appCompatActivity, item.getTitle(),
                        Toast.LENGTH_SHORT).show();
                if (bottomSheetLayout.isSheetShowing()) {
                    bottomSheetLayout.dismissSheet();
                }
                return true;
            }
        };
    }

    public static void showMenuDialog(AppCompatActivity appCompatActivity,
            BottomSheetLayout bottomSheetLayout,
            MenuSheetView.OnMenuItemClickListener onMenuItemClickListener) {
        MenuSheetView menuSheetView = new MenuSheetView(appCompatActivity,
                MenuSheetView.MenuType.LIST, "Create...",
                onMenuItemClickListener);
        menuSheetView.inflateMenu(R.menu.create);
        bottomSheetLayout.showWithSheetView(menuSheetView);
    }

    public static void showMenuDialogLazy(AppCompatActivity appCompatActivity,
            BottomSheetLayout bottomSheetLayout) {
        showMenuDialog(appCompatActivity, bottomSheetLayout,
                generateMenuItemClickListener(appCompatActivity,
                        bottomSheetLayout));
    }

}

package com.sc.bottomsheet.ui;

/**
 * Created by Peter on 10.05.2017.
 */

public interface OnSheetDismissedListener {

    /**
     * Called when the presented sheet has been dismissed.
     *
     * @param bottomSheetLayout The bottom sheet which contained the presented sheet.
     */
    void onDismissed(BottomSheetLayout bottomSheetLayout);
}

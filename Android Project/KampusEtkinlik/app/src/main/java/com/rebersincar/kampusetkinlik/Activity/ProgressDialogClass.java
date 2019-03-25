package com.rebersincar.kampusetkinlik.Activity;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogClass {

    public void loading(Context context)
    {
        ProgressDialog loadProgress = new ProgressDialog(context);
        loadProgress.setCancelable(false);
        loadProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        loadProgress.setMessage("Yükleniyor.Lütfen Bekleyiniz..");
        loadProgress.show();
    }

    public void delete(Context context)
    {
        ProgressDialog delProgress = new ProgressDialog(context);
        delProgress.setCancelable(false);
        delProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        delProgress.setMessage("Yükleniyor.Lütfen Bekleyiniz..");
        delProgress.show();
    }
}

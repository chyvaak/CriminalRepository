package com.chyvaak.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.chyvaak.criminalintent.fragment.CrimeFragment;
import com.chyvaak.criminalintent.fragment.SingleFragmentActivity;

public class CrimeActivity extends SingleFragmentActivity {


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}

package com.chyvaak.criminalintent;

import android.support.v4.app.Fragment;

import com.chyvaak.criminalintent.fragment.CrimeListFragment;
import com.chyvaak.criminalintent.fragment.SingleFragmentActivity;

/**
 * Created by chyvaak on 26.03.2016.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}

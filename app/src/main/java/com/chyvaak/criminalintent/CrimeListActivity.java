package com.chyvaak.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.chyvaak.criminalintent.fragment.CrimeFragment;
import com.chyvaak.criminalintent.fragment.CrimeListFragment;
import com.chyvaak.criminalintent.fragment.SingleFragmentActivity;
import com.chyvaak.criminalintent.util.Crime;

/**
 * Created by chyvaak on 26.03.2016.
 */
public class CrimeListActivity extends SingleFragmentActivity
        implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getmId());
            startActivity(intent);
        } else {
          Fragment newDetail = CrimeFragment.newInstance(crime.getmId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}

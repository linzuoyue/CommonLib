package com.lzy.rxevent;

import android.support.v4.app.Fragment;

public class BusinessFragmentBase {


    /**
     * Fragment是否过期
     *
     * @param fragment fragment
     * @return true:过期
     */
    public static <T extends Fragment> boolean isFragmentDeprecated(T fragment) {
        return fragment == null || !fragment.isAdded()
                || fragment.getActivity() == null || fragment.getActivity().isFinishing();

    }

}

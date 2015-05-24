package com.elsennovraditya.materialdesign;

import com.dd.processbutton.iml.ActionProcessButton;
import com.elsennovraditya.materialdesign.responses.NoteResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by elsen on 5/10/15.
 */
public class MainFragment extends Fragment {

    @InjectView(R.id.image1)
    ImageView image1;

    @InjectView(R.id.get_library_button)
    ActionProcessButton getANoteButton;

    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                mActivity.startActivity(intent);
            }
        });

        image1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(mActivity, ImageDetailActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(mActivity, image1,
                                getString(R.string.image));
                // start the new activity
                mActivity.startActivity(intent, options.toBundle());
                return true;
            }
        });

        getANoteButton.setMode(ActionProcessButton.Mode.ENDLESS);
        getANoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getANoteButton.setProgress(1);
                Observable<NoteResponse> noteResponseObservable = new APIService().build()
                        .getANote(RetrofitService.USER_ID);
                noteResponseObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Func1<NoteResponse, String>() {
                            @Override
                            public String call(NoteResponse noteResponse) {
                                return noteResponse.getTitle();
                            }
                        })
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                getANoteButton.setProgress(100);
                                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}

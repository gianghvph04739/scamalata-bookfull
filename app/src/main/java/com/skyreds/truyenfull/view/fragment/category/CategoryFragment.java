package com.skyreds.truyenfull.view.fragment.category;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.adapter.AdapterCategory;
import com.skyreds.truyenfull.base.BaseFragment;
import com.skyreds.truyenfull.view.activity.listbook.ListBookActivity;
import com.skyreds.truyenfull.view.fragment.category.model.Category;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryFragment extends BaseFragment implements CategoryDataListener {
    private OnFragmentInteractionListener mListener;
    private CategoryPresenter categoryPresenter;
    private ArrayList<Category> lstCategory;
    private AdapterCategory adapterCategory;
    Unbinder unbinder;

    @BindView(R.id.rv_category)
    RecyclerView rvCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        categoryPresenter = new CategoryPresenter(getContext(), this);
        setUp(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void setUp(View view) {
        categoryPresenter.loadCategory();
    }

    @Override
    public void onLoadCategorySucessful(ArrayList<Category> lst) {
        lstCategory = lst;
        adapterCategory = new AdapterCategory(getContext(), lstCategory);
        rvCategory.setAdapter(adapterCategory);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rvCategory.setLayoutManager(manager);
        adapterCategory.setOnItemClicked(new AdapterCategory.OnItemClickListener() {
            @Override
            public void onClickitem(String url) {
                Intent i =  new Intent(getContext(), ListBookActivity.class);
                i.putExtra("url",url);
                startActivity(i);
            }
        });
    }

    @Override
    public void onLoadCategoryFailed(String message) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

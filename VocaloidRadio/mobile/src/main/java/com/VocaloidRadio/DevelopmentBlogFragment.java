package com.VocaloidRadio;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevelopmentBlogFragment extends Fragment {
    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DevelopmentBlogFragment.
     */
    public static DevelopmentBlogFragment newInstance(String param1, String param2) {
        // New fragment creation
        DevelopmentBlogFragment fragment = new DevelopmentBlogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        // Setting up arguments in the created fragment
        fragment.setArguments(args);
        return fragment;
    }

    public DevelopmentBlogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Initial setup of the activity
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer = inflater.inflate(R.layout.fragment_development_blog, container, false);

        // Webview Code
        WebView webView = (WebView) viewer.findViewById(R.id.developmentWebview);
        if (webView == null) {
            throw new AssertionError(); // Check for errors
        }
        else {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(false); // Check if Javascript is enabled
            webView.loadUrl("http://www.vocaloidradioapp.blogspot.com/p/android-windowsphone-version.html"); // Load HTTP url
        }
        return viewer; // Show it
    }

    @Override
    public void onAttach(Context context) { // Called when a fragment is first attached to its context
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() { // Called when a fragment is no longer attached to its activity
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

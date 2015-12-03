package com.VocaloidRadio;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RadioStreamFragment extends Fragment {
    // Fragment initialization parameters

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /** Media player */
    private static MediaPlayer mPlayer;
    /* Play Button */
    private Button buttonPlay;
    /** Stop Button */
    private Button buttonStop;

    /**
     * Factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RadioStreamFragment.
     */
    public static RadioStreamFragment newInstance(String param1, String param2) {
        // New fragment creation
        RadioStreamFragment fragment = new RadioStreamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        // Setting up arguments in the created fragment
        fragment.setArguments(args);
        return fragment;
    }

    public RadioStreamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {  // Initial setup of the activity
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
        View viewer = inflater.inflate(R.layout.fragment_radio_stream, container, false);

        // Webview Code
        WebView webView = (WebView) viewer.findViewById(R.id.currentSong);
        if (webView == null) {
            throw new AssertionError();
        }
        else {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true); // Enable Javascript
            webView.loadUrl("file:///android_res/raw/song_info.html");  // Load HTTP url
        }

        // Background Player Code
        initializePlayer(viewer);
        return viewer;
    }
    // Load the URI when the button is preesed
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {  // Called when a fragment is first attached to its context
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {  // Called when a fragment is no longer attached to its activity
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
        public void onFragmentInteraction(Uri uri);
    }

    /**
     * Creates a new mediaPlayer using a stream music URL,
     * Adds onClick Listeners to the Play and Stop buttons
     * @param view View that contains the buttons
     */
    private void initializePlayer(View view) {
        buttonPlay = (Button) view.findViewById(R.id.playButton);
        //Set Play listener
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mPlayer != null && mPlayer.isPlaying()) {
                    return;
                }
                mPlayer = new MediaPlayer(); // Create a new MediaPlayer object
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mPlayer.setDataSource(getString(R.string.stream_url)); // Streaming Audio URL
                }
                // Handle Exceptions
                  catch (IllegalArgumentException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    // Prepares the player for playback, asynchronously
                    mPlayer.prepareAsync();
                    final ProgressDialog nDialog = new ProgressDialog(getActivity());
                    nDialog.setMessage(getString(R.string.loading)); // Message shown while loading the streaming
                    nDialog.setTitle(getString(R.string.checking_net));  // Message shown while loading the streaming
                    nDialog.setIndeterminate(false);
                    nDialog.setCancelable(false);
                    nDialog.show();
                    // Register a callback to be invoked when the media source is ready for playback.
                    mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            nDialog.dismiss();
                            mp.start();
                        }
                    });
                    // Register a callback to be invoked when an error has happened during an asynchronous operation.
                    mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            return false;
                        }
                    });
                }
                // Handle Exceptions
                  catch (IllegalStateException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                }
                mPlayer.start(); // Starting the Media Player object
            }
        });

        //Set Stop Listener
        buttonStop = (Button) view.findViewById(R.id.stopButton);
        buttonStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mPlayer != null && mPlayer.isPlaying()) {
                    mPlayer.stop();
                }
            }
        });
    }
}
package com.yusfa.animal4;


import java.util.Locale;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ImageFragment extends Fragment implements OnInitListener, OnClickListener{
    int fragVal;
    ImageButton suara;
    
    private TextToSpeech mTts;
    private static final String TAG = "TextToSpeechDemo";
    private static final int MY_DATA_CHECK_CODE = 1234;
    
    
    View speak;
    
	private Integer[] mImageIds = { 
			R.drawable.camel, R.drawable.crab,R.drawable.deer, R.drawable.duck,
			R.drawable.frog, R.drawable.giraffe, R.drawable.kangaroo, 
			R.drawable.mosquito, R.drawable.owl,	R.drawable.penguin,
			R.drawable.shrimp
			
			
	};
	
	

	private String[] mName = { 
			"Camel","Crab","Deer",
			"Duck", "Frog", "Giraffe",
			"Kangaroo", "Mosquito", "Owl",
			"Penguin", "Shrimp"
	};
 
	private String[] arti =  { 
			"Unta","Kepiting","Rusa",
			"Bebek", "Katak", "Jerapah",
			"Kanguru", "Nyamuk", "Burung Hantu",
			"Penguin", "Udang"
	};
    

    String status_data ="";
    String speak_string="Sorry";
    static ImageFragment init(int val) {
        ImageFragment truitonFrag = new ImageFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        
        return truitonFrag;
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
       
        speak_string=mName[fragVal-1];
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_image, container,
                false);
        View tv = layoutView.findViewById(R.id.text);
        View tv2 = layoutView.findViewById(R.id.text2);
        
        
        speak = layoutView.findViewById(R.id.add);
        
        ((TextView) tv).setText(mName[fragVal-1]);
       
        ((TextView) tv2).setText(arti[fragVal-1]);
        ((Button) speak).setOnClickListener(this);
        
        ImageView image= (ImageView)layoutView.findViewById(R.id.imageView1);
        
        image.setImageResource(mImageIds[fragVal-1]);
                
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

       
        
        return layoutView;
    }
    
    @Override
    public void onDestroy() {
    	// TODO Auto-generated method stub
    	 if (mTts != null)
         {
             mTts.stop();
             mTts.shutdown();
         }
    	super.onDestroy();
    }

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
        if (status == TextToSpeech.SUCCESS) {
               status_data = "status 1";
            int result = mTts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
               status_data ="Status 2";
            } else {
               speak.setEnabled(true);
               status_data="Status 3";
            }
            } else {
            status_data = "status 4";
            
        }
        
        Log.e(TAG, status_data);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==speak){
		String myText1 = "It is";        
		mTts.speak(myText1, TextToSpeech.QUEUE_FLUSH, null);
        mTts.speak(speak_string, TextToSpeech.QUEUE_ADD, null);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == MY_DATA_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                // success, create the TTS instance
                status_data="Sukses";
                mTts = new TextToSpeech(getActivity(), this);
                mTts.setLanguage(Locale.US);
               
                speak.setEnabled(true);

            }
            else
            {
                status_data="Missing";
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
	}
	
    
}

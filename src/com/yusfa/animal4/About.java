package com.yusfa.animal4;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class About extends Activity {
	
TextView judul, isi;	
int id;
String nama;
String TAG_ID="id";
String TAG_NAMA="nama";
String TAG_KATEGORI="kategori";

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.detail);
	isi		=(TextView)findViewById(R.id.isi);
	
   
	int teks=R.string.about;
	isi.setText(Html.fromHtml(getString(teks)));
		
	}

}

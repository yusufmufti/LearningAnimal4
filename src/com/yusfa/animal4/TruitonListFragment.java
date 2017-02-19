package com.yusfa.animal4;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

 
public class TruitonListFragment extends ListFragment {
    int fragNum;
    String arr[] = { 
			"Camel","Crab","Deer",
			"Duck", "Frog", "Giraffe",
			"Kangaroo", "Mosquito", "Owl",
			"Penguin", "Shrimp"
	};
 
    static TruitonListFragment init(int val) {
        TruitonListFragment truitonList = new TruitonListFragment();
 
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonList.setArguments(args);
 
        return truitonList;
    }
 
    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;

        
    }
 
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_pager_list,
                container, false);
        View tv = layoutView.findViewById(R.id.text);
        ((TextView) tv).setText("Learning about animal 4 \n Mengenal Macam Hewan 4");
        
       
        return layoutView;
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arr));
    }
 
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("Truiton FragmentList", "Item clicked: " + id);
        MainActivity.mPager.setCurrentItem(position+1);
        
    }
}
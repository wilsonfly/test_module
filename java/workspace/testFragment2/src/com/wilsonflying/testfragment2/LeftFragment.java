package com.wilsonflying.testfragment2;


import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LeftFragment extends ListFragment {

	private boolean isDualPane;
	private int currentCheckedPosition;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		if( savedInstanceState != null){
			currentCheckedPosition = savedInstanceState.getInt("curChecked");
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_single_choice, Data.TITILES);
		
		setListAdapter(adapter);
		
		View detailFragment = getActivity().findViewById(R.id.fl_detail);
		isDualPane = detailFragment != null && detailFragment.getVisibility() == View.VISIBLE;
		if(isDualPane){
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(currentCheckedPosition);
		}
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		showDetails(position);
	}
	
	private void showDetails(int position) {
		// TODO Auto-generated method stub
		currentCheckedPosition = position;
		
		if(isDualPane){
			getListView().setItemChecked(position, true);
//			DetailFragment fg_detail = (DetailFragment) getFragmentManager().findFragmentById(R.id.fl_detail);//用的是FrameLayout，没有fragment
			DetailFragment fg_datail = DetailFragment.newInstance(position); // 创建一个新的DetailFragment实例用于显示当前选择项对应的详细内容

			FragmentTransaction trans = getFragmentManager().beginTransaction();
			trans.replace(R.id.fl_detail, fg_datail);//替换方法，新创建的fragment替换之前的显示内容
			trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			trans.commit();
		}else{
			Intent intent = new Intent(getActivity(), DetailAty.class);
			intent.putExtra("index", position);
			startActivity(intent);
		}
	}


}

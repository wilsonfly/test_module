package com.mingrisoft;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends android.app.ListFragment {

	boolean dualPane; // �Ƿ���һ����ͬʱ��ʾ�б����ϸ����
	int curCheckPosition = 0; // ��ǰѡ�������λ��

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_checked, Data.TITLES)); // Ϊ�б�����������

		View detailFrame = getActivity().findViewById(R.id.detail); // ��ȡ�����ļ�����ӵ�FrameLayout֡���ֹ�����
		dualPane = detailFrame != null && 
				detailFrame.getVisibility() == View.VISIBLE; // �ж��Ƿ���һ����ͬʱ��ʾ�б����ϸ����

		if (savedInstanceState != null) {
			curCheckPosition = savedInstanceState.getInt("curChoice", 0); // ���µ�ǰѡ�������λ��
		}

		if (dualPane) { // �����һ����ͬʱ��ʾ�б����ϸ����
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE); // �����б�Ϊ��ѡģʽ
			showDetails(curCheckPosition); // ��ʾ��ϸ����
		}
	}

	// ��дonSaveInstanceState()���������浱ǰѡ�е��б��������ֵ
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", curCheckPosition);
	}

	// ��дonListItemClick()����
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position); // ����showDetails()������ʾ��ϸ����
	}

	void showDetails(int index) {
		curCheckPosition = index; // ���±��浱ǰ����λ�õı�����ֵΪ��ǰѡ��ֵ

		if (dualPane) { // ����һ����ͬʱ��ʾ�б����ϸ����ʱ

			getListView().setItemChecked(index, true); // ����ѡ���б���Ϊѡ��״̬

			DetailFragment details = (DetailFragment) getFragmentManager()
					.findFragmentById(R.id.detail); // ��ȡ������ʾ��ϸ���ݵ�Fragment
			if (details == null || details.getShownIndex() != index) { // ������
				details = DetailFragment.newInstance(index); // ����һ���µ�DetailFragmentʵ��������ʾ��ǰѡ�����Ӧ����ϸ����

				// Ҫ��activity�й���fragment, ��Ҫʹ��FragmentManager
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();// ���һ��FragmentTransaction��ʵ��
				ft.replace(R.id.detail, details); // �滻ԭ����ʾ����ϸ����
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); // ����ת��Ч��
				ft.commit(); // �ύ����
			}

		} else { // ��һ����ֻ����ʾ�б����ϸ�����е�һ������ʱ

			// ʹ��һ���µ�Activity��ʾ��ϸ����
			Intent intent = new Intent(getActivity(),
					MainActivity.DetailActivity.class); // ����һ��Intent����
			intent.putExtra("index", index); // ����һ��Ҫ���ݵĲ���
			startActivity(intent); // ����һ��ָ����Activity
		}
	}

}

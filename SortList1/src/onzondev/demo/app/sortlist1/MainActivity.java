package onzondev.demo.app.sortlist1;

import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private String data[] = {"AA","BB","CC","DD","EE","FF","GG","HH","II","JJ","LL","MM","NN","OO","PP"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ListView lv = (ListView)findViewById(R.id.listView1);
		
		ArrayAdapter<String> listData = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
		lv.setAdapter(listData);
		
		mList = (TouchInterceptor)lv;
        mList.setDropListener(mDropListener);
        registerForContextMenu(mList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private TouchInterceptor mList;
	private TouchInterceptor.DropListener mDropListener =
			    new TouchInterceptor.DropListener() {
			        public void drop(int from, int to) {
			            System.out.println("Droplisten from:"+from+" to:"+to);

			            //Assuming that item is moved up the list
			            int direction = -1;
			            int loop_start = from;
			            int loop_end = to;

			            //For instance where the item is dragged down the list
			            if(from < to) {
			            	direction = 1;
			            }

			            String target = data[from];

			            for(int i=loop_start;i!=loop_end;i=i+direction){
			            	data[i] = data[i+direction];
			            }

			            data[to] = target;

			            System.out.println("Changed array is:"+Arrays.toString(data));
			            ((BaseAdapter) mList.getAdapter()).notifyDataSetChanged();
			        }
			    };

}

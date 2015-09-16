package com.example.volleytest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.model.shared;
import com.example.volley.VolleyHttpPath;
import com.example.volley.VolleyHttpRequest;
import com.example.volley.VolleyHandler;
import com.example.volley.VolleyApplication;
import com.example.volleytest.MovieActivity.MovieListAdapter;

public class MoviesActivity extends Activity {

	private ListView move_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movies);
		move_list=(ListView)findViewById(R.id.move_list);
		getMovieList();
		
	}
	
	/**
	 * ִ���������������listview
	 */
	private void getMovieList(){
		
		String url=VolleyHttpPath.getSharedAll();
		VolleyHandler<JSONObject> volleyRequest=new VolleyHandler<JSONObject>() {
			
			@Override
			public void reqSuccess(JSONObject response) {
				// �ɹ���Ľ���
				List<shared> list=new ArrayList<shared>();
				String str="�������";
				try {
					if(response.getString("msg").equals("success")&&response.getInt("code")==1){
						
						//json���� Ϊ List<shared> 
						JSONArray array=new JSONArray(response.getString("data"));
						for(int i=0;i<array.length();i++){
							JSONObject object=(JSONObject) array.get(i);
							shared s=new shared();
							s.setId(object.getInt("id"));
							s.setName(object.getString("name"));
							s.setPic(object.getString("pic"));
							s.setTotol(object.getString("totol"));
							list.add(s);
						}
						if(list.size()>0){
							//������� ������ listview
							MovieListAdapter adapter=new MovieListAdapter(list);
							move_list.setAdapter(adapter);
						}else{
							str="û�н�������"+response.toString();
							Show(str);
						}
						
					}else{
						Show(response.getString("msg"));
					}
				} catch (JSONException e) {
					// �����쳣
					Show(e.getMessage());
				}
				
	
			}
			
			@Override
			public void reqError(String error) {
				// ʧ��
				Show("JsonObject error��"+error);
				
			}
		};
		//��������
	   VolleyHttpRequest.JsonObject_Request(url, volleyRequest);
		
	}
	
	/**
	 * toast 
	 * @param msg
	 */
	private void Show(String msg){
		Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
	}
	
	
	/**
	 * �̳�baseAdapter ׼�������listview
	 * @author yuan
	 * 
	 */
	class MovieListAdapter extends BaseAdapter{

		List<shared> mlist=null;
		private ImageView imageView;
		public MovieListAdapter(List<shared> list) {
			//��ʼ�� List<shared> �б�
			this.mlist=list;
		}
		
		@Override
		public int getCount() {
			return mlist.size();
		}

		@Override
		public Object getItem(int position) {
			return mlist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//��ʼ�� �ؼ�
			convertView=View.inflate(getApplicationContext(), R.layout.list_item,null);
			
			imageView=(ImageView)convertView.findViewById(R.id.img_movie);
			TextView movie_name=(TextView)convertView.findViewById(R.id.movie_name);
			TextView movie_totol=(TextView)convertView.findViewById(R.id.movie_totol);
		    
			//���� ����Ϊ ��ɫ
			if(position%2==0){
			  convertView.setBackgroundColor(Color.GRAY);
			}
			
			//���ò���
			shared s=mlist.get(position);
			movie_name.setText(s.getName());
			movie_totol.setText(s.getTotol());
			//����ͼƬ
			String url=VolleyHttpPath.getBasicPath()+"/"+s.getPic();
			
			
			//ʵ��ImageListener
			ImageListener imageListener = ImageLoader.getImageListener(imageView,
					R.drawable.ic_launcher, R.drawable.ic_launcher);
		    //���� ͼƬ ����ͼƬ
			VolleyHttpRequest.Image_Loader(url, imageListener);
			
			return convertView;
	  }
		
	}
	
	
}


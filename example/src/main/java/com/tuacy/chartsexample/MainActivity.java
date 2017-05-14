package com.tuacy.chartsexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tuacy.charts.bean.Render;
import com.tuacy.charts.line.LineChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private LineChart mLineChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mLineChart = (LineChart) findViewById(R.id.line_chart);
	}

	private void initEvent() {

	}

	private void initData() {
		mLineChart.setData(getXData(), getYData());
	}

	private List<Number> getXData() {
		List<Number> xData = new ArrayList<>();
		xData.add(1);
		xData.add(2);
		xData.add(3);
		xData.add(4);
		xData.add(5);
		xData.add(6);
		xData.add(7);
		xData.add(8);
		xData.add(9);
		xData.add(10);
		xData.add(11);
		xData.add(12);
		return xData;
	}

	private List<Render> getYData() {
		List<Render> yData = new ArrayList<>();
		Render render = new Render();
		List<Number> data = new ArrayList<>();
		data.add(1);
		data.add(2);
		data.add(3);
		data.add(16);
		data.add(5);
		data.add(6);
		data.add(7);
		data.add(50);
		data.add(9);
		data.add(2);
		data.add(11);
		data.add(12);
		render.setData(data);
		render.setColor(Color.BLUE);
		yData.add(render);
		Render render1 = new Render();
		List<Number> data1 = new ArrayList<>();
		data1.add(2);
		data1.add(12);
		data1.add(5);
		data1.add(4);
		data1.add(3);
		data1.add(8);
		data1.add(2);
		data1.add(40);
		data1.add(38);
		data1.add(1);
		data1.add(5);
		data1.add(16);
		render1.setData(data1);
		render1.setColor(Color.RED);
		yData.add(render1);
		return yData;
	}
}

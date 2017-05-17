package com.tuacy.chartsexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tuacy.charts.bean.Render;
import com.tuacy.charts.data.Entry;
import com.tuacy.charts.data.LineData;
import com.tuacy.charts.data.LineDataSet;
import com.tuacy.charts.line.LineChart;
import com.tuacy.charts.line.LineChartPro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private LineChart    mLineChart;
	private LineChartPro mLineChartPro;

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
		mLineChartPro = (LineChartPro) findViewById(R.id.line_chart_pro);
	}

	private void initEvent() {

	}

	private void initData() {
		mLineChart.setData(getXData(), getYData());
		mLineChartPro.setData(obtainLineData());
	}

	private LineData obtainLineData() {
		List<Entry> entries = new ArrayList<>();
		entries.add(new Entry(10, 0));
		entries.add(new Entry(10, 1));
		entries.add(new Entry(10, 2));
		entries.add(new Entry(10, 3));
		entries.add(new Entry(10, 4));
		entries.add(new Entry(10, 5));
		entries.add(new Entry(10, 6));
		entries.add(new Entry(10, 7));
		LineDataSet dataSet = new LineDataSet(entries);
		List<String> xValues = new ArrayList<>();
		xValues.add("0");
		xValues.add("1");
		xValues.add("2");
		xValues.add("3");
		xValues.add("4");
		xValues.add("5");
		xValues.add("6");
		xValues.add("7");
		List<LineDataSet> setList = new ArrayList<>();
		setList.add(dataSet);
		return new LineData(xValues, setList);
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

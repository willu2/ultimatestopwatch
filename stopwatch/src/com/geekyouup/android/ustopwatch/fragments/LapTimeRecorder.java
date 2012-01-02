package com.geekyouup.android.ustopwatch.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

public class LapTimeRecorder {
	
	private static final ArrayList<Double> mLapTimes = new ArrayList<Double>();
	private static final String PREFS_NAME_LAPTIMES = "usw_prefs_laptimes";
	private static final String KEY_LAPTIME_X = "LAPTIME_";
	private static LapTimeRecorder mSelf;
	
	public static LapTimeRecorder getInstance()
	{
		if(mSelf == null) mSelf = new LapTimeRecorder();
		return mSelf;
	}
	
	public void loadTimes(Context cxt)
	{
		SharedPreferences settings = cxt.getSharedPreferences(PREFS_NAME_LAPTIMES, Context.MODE_PRIVATE);
		if (settings != null) {
            int lapTimeNum=0;
            mLapTimes.clear();
            while(settings.getLong(KEY_LAPTIME_X+lapTimeNum,-1L) != -1L)
            {
            	mLapTimes.add((double) settings.getLong(KEY_LAPTIME_X+lapTimeNum,0L));
            	lapTimeNum++;
            }
		}
	}
	
	public void saveTimes(Context cxt)
	{
		SharedPreferences settings = cxt.getSharedPreferences(PREFS_NAME_LAPTIMES, Context.MODE_PRIVATE);
		if (settings != null) {
			SharedPreferences.Editor editor = settings.edit();
			if (editor != null) {
				editor.clear();
                if(mLapTimes!= null && mLapTimes.size()>0)
                {
                	for(int i=0;i<mLapTimes.size();i++) editor.putLong(KEY_LAPTIME_X+i,mLapTimes.get(i).longValue());
                }
				editor.commit();
			}
		}
	}
	
	public void recordLapTime(double time)
	{
		mLapTimes.add(0,time);
	}
	
	public ArrayList<Double> getTimes()
	{
		return mLapTimes;
	}
	
	public void reset(Context cxt)
	{
		mLapTimes.clear();
		SharedPreferences settings = cxt.getSharedPreferences(PREFS_NAME_LAPTIMES, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}
}

package com.sufe.receiver;
import com.sufe.lecture.activity.AlarmAlertActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class CallAlertReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent i = new Intent(context,AlarmAlertActivity.class);
		Bundle bundleRet = new Bundle();
		bundleRet.putString("STR_CALLER","");
		i.putExtras(bundleRet);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
	
}

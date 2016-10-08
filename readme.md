# 003_Tutorial_internet_connection

## Purpose
Tutorial: get connectivity state, esp availability of internet
remark: in case of wifi login is required (e.g. for hot spot) to access to internet, that is not covered here

2016.10.08. by Szőke Sándor
Android Studio version: 2.2 (September 15, 2016)

## Steps needed
* create application
* drag button onto layout, change title text to whatever you like
* add code to: class MainActivity 

//check network on buttonclick, with toast message
```java
    public void buttonOnClick(View v)
    {
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            Toast.makeText(MainActivity.this, "Network Available", Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(MainActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();

        }
    }
```
    
* attach code to button onClick: buttonOnClick
* apply ALT+ENTER to extentend includes after the following words
  * (View v)
  * Context
  * Toast
  * ConnectivityManager
  * NetworkInfo
* create a new class:UpdateReceiver with the following content

// generating toast on network change (mobile/wifi)
```java
    public class UpdateReceiver extends BroadcastReceiver
    {
	@Override
	public void onReceive(Context context, Intent intent)
	{
	    ConnectivityManager connectivityManager = (ConnectivityManager)
		    context.getSystemService(Context.CONNECTIVITY_SERVICE );
	    // FIXME: 2016.10.06. api23 changed rewrite necessary
	    NetworkInfo activeNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    boolean isConnected = activeNetInfo != null && activeNetInfo.isConnectedOrConnecting();
	    if (isConnected)
		Log.i("NET", "InterNET Connected: " +isConnected);
	    else Log.i("NET", "InterNET Disconnected: " +isConnected);
	}
    }
```

* apply ALT+ENTER to extentend includes after the following words
  * BroadcastReceiver
  * Context
  * Intent
  * ConnectivityManager
  * NetworkInfo
  * Log
* in AndroidManifest.xml we need to add rights to check network after the </application> directive
```
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
```

* in AndroidManifest.xml we need to tell the system to call our brand new UpdateReceiver class in case CONNECTIVITY_CHANGE Broadcast are fired
```
        <receiver android:name=".UpdateReceiver" >
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
        </receiver>
```

* exetute and click

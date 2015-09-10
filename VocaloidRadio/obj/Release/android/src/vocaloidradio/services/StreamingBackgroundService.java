package vocaloidradio.services;


public class StreamingBackgroundService
	extends android.app.Service
	implements
		mono.android.IGCUserPeer,
		android.media.AudioManager.OnAudioFocusChangeListener
{
	static final String __md_methods;
	static {
		__md_methods = 
			"n_onCreate:()V:GetOnCreateHandler\n" +
			"n_onBind:(Landroid/content/Intent;)Landroid/os/IBinder;:GetOnBind_Landroid_content_Intent_Handler\n" +
			"n_onStartCommand:(Landroid/content/Intent;II)I:GetOnStartCommand_Landroid_content_Intent_IIHandler\n" +
			"n_onDestroy:()V:GetOnDestroyHandler\n" +
			"n_onAudioFocusChange:(I)V:GetOnAudioFocusChange_IHandler:Android.Media.AudioManager/IOnAudioFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n" +
			"";
		mono.android.Runtime.register ("VocaloidRadio.Services.StreamingBackgroundService, VocaloidRadio, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", StreamingBackgroundService.class, __md_methods);
	}


	public StreamingBackgroundService () throws java.lang.Throwable
	{
		super ();
		if (getClass () == StreamingBackgroundService.class)
			mono.android.TypeManager.Activate ("VocaloidRadio.Services.StreamingBackgroundService, VocaloidRadio, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onCreate ()
	{
		n_onCreate ();
	}

	private native void n_onCreate ();


	public android.os.IBinder onBind (android.content.Intent p0)
	{
		return n_onBind (p0);
	}

	private native android.os.IBinder n_onBind (android.content.Intent p0);


	public int onStartCommand (android.content.Intent p0, int p1, int p2)
	{
		return n_onStartCommand (p0, p1, p2);
	}

	private native int n_onStartCommand (android.content.Intent p0, int p1, int p2);


	public void onDestroy ()
	{
		n_onDestroy ();
	}

	private native void n_onDestroy ();


	public void onAudioFocusChange (int p0)
	{
		n_onAudioFocusChange (p0);
	}

	private native void n_onAudioFocusChange (int p0);

	java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}

using System;
using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using VocaloidRadio.Services;
using Android.Webkit;

namespace VocaloidRadio
{
    [Activity(Label = "Vocaloid Radio", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : Activity
    {
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.Main);

			// Get a reference to the button
			var goToSecond = FindViewById<Button> (Resource.Id.webViewPage);
            var play = FindViewById<Button>(Resource.Id.playButton);
            var stop = FindViewById<Button>(Resource.Id.stopButton);
			var mainWebView = FindViewById<WebView> (Resource.Id.webView1);
			var mainPageButton = FindViewById<Button> (Resource.Id.mainPageInfo);
			var extraInfoButton = FindViewById<Button> (Resource.Id.extraInfo);

			// SetUp main Webview
			string url = "file:///android_asset/SongInformation.html";
			mainWebView.Settings.JavaScriptEnabled = true;
			mainWebView.SetWebChromeClient (new WebChromeClient ());
			mainWebView.LoadUrl(url);

			// Add Handlers
            play.Click += (sender, args) => SendAudioCommand(StreamingBackgroundService.ActionPlay);
            stop.Click += (sender, args) => SendPauseCommand(StreamingBackgroundService.ActionStop);
			mainPageButton.Click += (sender, args) => switchMainPage(mainWebView,mainPageButton);
			extraInfoButton.Click += (sender, args) => showSimplePopUp();
		
			// You can use either this short form of StartActivity, which will create 
			// an intent internally, or the long form shown below.           
			goToSecond.Click += (sender, e) => {StartActivity (typeof(SecondActivity)); };

        }

		private void showSimplePopUp() {

			AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
			helpBuilder.SetTitle("VRApp Info");
			helpBuilder.SetMessage("Having issues? Fully close the App and try again! Persistent issues? Please visit VocaloidRadioApp.blogspot.com for more information. Translation and a form to contact the developer is available on the desktop version! Thank you for your understanding as this is still an early version");
			helpBuilder.SetPositiveButton("Ok", (sender, args) => { });
			// Remember, create doesn't show the dialog
			AlertDialog helpDialog = helpBuilder.Create();
			helpDialog.Show();
		}


		// Pause Method
     private void SendPauseCommand(string action)
        {
            var intent = new Intent(action);
            StartService(intent);
		}     

		// Play Method
		private void SendAudioCommand(string action)
		{
			try
			{
			var intent = new Intent(action);
			StartService(intent);
			}
			catch (Exception e) 
			{
				AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
				helpBuilder.SetTitle("VRApp Exception found!");
				helpBuilder.SetMessage("Fatal Error 1. Contact the Developer. SYS= " + e.ToString());
				helpBuilder.SetPositiveButton("Ok", (sender, args) => { });
				// Remember, create doesn't show the dialog
				AlertDialog helpDialog = helpBuilder.Create();
				helpDialog.Show();
			}
		}
		private void switchMainPage (WebView mainWebView, Button mainButton)
		{
			try
			{
			if (mainWebView.Url == "file:///android_asset/SongInformation.html") 
			{
				string newUrl = "file:///android_asset/PreviousSongs.html";
				mainWebView.LoadUrl(newUrl);
				mainButton.Text = "Current Song";
			} 
			else 
			{
				string newUrl = "file:///android_asset/SongInformation.html";
				mainWebView.LoadUrl(newUrl);
				mainButton.Text = "Recent Tracks";
			}
			}
			catch (Exception e) 
			{
				AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
				helpBuilder.SetTitle("VRApp Exception found!");
				helpBuilder.SetMessage("Fatal Error 2. Contact the Developer. SYS= " + e.ToString());
				helpBuilder.SetPositiveButton("Ok", (sender, args) => { });
				// Remember, create doesn't show the dialog
				AlertDialog helpDialog = helpBuilder.Create();
				helpDialog.Show();
			}
		}
    }
}


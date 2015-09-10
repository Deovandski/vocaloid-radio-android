using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.Webkit;

namespace VocaloidRadio
{
	[Activity(Label = "VR App Version 1.1", Icon = "@drawable/icon")]          
    public class SecondActivity : Activity
    {
        protected override void OnCreate (Bundle bundle)
        {
            base.OnCreate (bundle);

			// Load the UI defined in Second.axml
			SetContentView (Resource.Layout.Second);

			// Get a reference to the buttons
			var goToSecond = FindViewById<Button> (Resource.Id.webViewPage);

			var rssFeedButton = FindViewById<Button>(Resource.Id.rssFeed);
			var devPageButton = FindViewById<Button>(Resource.Id.devPage);
			var vrWebsiteButton = FindViewById<Button>(Resource.Id.vrWebsite);
			var featuredPagesButton = FindViewById<Button>(Resource.Id.featuredPages);
			WebView pageWebView = FindViewById<WebView> (Resource.Id.pageWebView);

			// Setup main Webview and Strings for navigation
			string devPageString = "http://www.vocaloidradioapp.blogspot.com/p/android-windowsphone-version.html";
			string featuredPagesString = "http://vocaloidradioapp.blogspot.com/2014/06/vocaloid-radio-featured-pages.html";
			string vrWebsiteString = "http://vocaloidradio.com/";
			string recentSongsString = "file:///android_asset/RSSFeed.html";

			pageWebView.Settings.JavaScriptEnabled = true;
			pageWebView.SetWebChromeClient (new WebChromeClient());

			// SetUp Webview
			string url = "file:///android_asset/RSSFeed.html";
			pageWebView.LoadUrl(url);

			// Add Handlers
			rssFeedButton.Click += (sender, args) => pageWebView.LoadUrl(recentSongsString);
			vrWebsiteButton.Click += (sender, args) => pageWebView.LoadUrl(vrWebsiteString);
			devPageButton.Click += (sender, args) => pageWebView.LoadUrl(devPageString);
			featuredPagesButton.Click += (sender, args) => pageWebView.LoadUrl(featuredPagesString);

        }
    }
}
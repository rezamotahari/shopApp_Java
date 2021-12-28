package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.shopapp.R;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class About_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element versionElement = new Element();
        versionElement.setTitle("نسخه 1.2");

        View aboutPage = new AboutPage(this)
                .isRTL(true)
                .enableDarkMode(false)
            //    .setCustomFont(String) // or Typeface
               // .setImage(R.drawable.ban)
               .addItem(versionElement)
               // .addItem(adsElement)
                .setDescription("فروشگاه اینترنتی مطهری نژاد")
                .addGroup("راه های ارتباط با ما")
                .addEmail("rezamotahari1394@gmail.com")
                .addWebsite("http://rezamotahari.ir/")
              //  .addFacebook("the.medy")
               // .addTwitter("medyo80")
               // .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
               // .addPlayStore("com.ideashower.readitlater.pro")
              //  .addGitHub("medyo")
                .addInstagram("reza_motahari75")
                .create();

        setContentView(aboutPage);

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
package com.example.naychi.cp3406alarmapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.naychi.cp3406alarmapp", appContext.getPackageName());
    }
    @Test
    public void testWithBoundService() throws TimeoutException {
        // Create the service Intent.
        Intent serviceIntent = new Intent(InstrumentationRegistry.getTargetContext(),
                RingtonePlayingService.class);

        // Data can be passed to the service via the Intent.
        serviceIntent.putExtra(RingtonePlayingService.ACCESSIBILITY_SERVICE, 42L);


    }
}


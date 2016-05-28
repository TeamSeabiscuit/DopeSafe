package edu.unh.cs.android.dopesafe;

import android.util.Log;

/**
 * Created by Chris Oelerich on 5/27/16.
 * <p/>
 * Singleton for holding user data.
 */
public class UserPrefs {
  private static final String TAG = "UserPrefs";

  private static UserPrefs prefs;

  private String phone;
  private String message;
  private boolean motion;
  private boolean location;
  private int time;

  private UserPrefs() {
  }

  public static UserPrefs getInstance() {
    if (prefs == null) {
      prefs = new UserPrefs();
    }
    return prefs;
  }

  public String getPhone() {
    return getInstance().phone;
  }

  public void setPhone(String phone) {
    getInstance().phone = phone;
    Log.d(TAG, "setPhone() called with: " + "phone = [" + phone + "]");

  }

  public boolean isMotion() {
    return getInstance().motion;
  }

  public void setMotion(boolean motion) {
    getInstance().motion = motion;
    Log.d(TAG, "setMotion() called with: " + "motion = [" + motion + "]");
  }

  public int getTime() {
    return getInstance().time;
  }

  public void setTime(int time) {
    getInstance().time = time;
    Log.d(TAG, "setTime() called with: " + "time = [" + time + "]");
  }

  public String getMsg() {
    return getInstance().message;
  }

  public void setMsg(String message) {
    getInstance().message = message;
    Log.d(TAG, "setMsg() called with: " + "message = [" + message + "]");
  }

  public boolean isLoc() {
    return getInstance().location;
  }

  public void setLoc(boolean location) {
    getInstance().location = location;
    Log.d(TAG, "setLoc() called with: " + "location = [" + location + "]");
  }
}
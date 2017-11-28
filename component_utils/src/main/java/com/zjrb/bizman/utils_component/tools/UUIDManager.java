package com.zjrb.bizman.utils_component.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;


import com.zjrb.bizman.utils_component.utils.EncryptUtils;
import com.zjrb.bizman.utils_component.utils.SharedPrefUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by lujialei
 */
public class UUIDManager {
    
    private static final String KEY = "jc20161124";
    
    private final String PREFS_FILE = "pref_uuid";
    
    private final String PREFS_DEVICE_ID = "pref_uuid_id";
    
    private final static String DEVICE_UUID_FILE_NAME = ".dev_id.txt";
    
    private UUID uuid;
    
    public UUIDManager(Context context) {
        if (uuid == null) {
            synchronized (UUIDManager.class) {
                if (uuid == null) {
                    final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
                    final String id = prefs.getString(PREFS_DEVICE_ID, null);
                    if (id != null) {
                        uuid = UUID.fromString(id);
                    } else {
                        if (recoverDeviceUuidFromSD(context) != null) {
                            uuid = UUID.fromString(recoverDeviceUuidFromSD(context));
                        } else {
                            final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                            try {
                                if (!"9774d56d682e549c".equals(androidId)) {
                                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                                    try {
                                        saveDeviceUuidToSD(EncryptUtils.encrypt(uuid.toString(), KEY, SharedPrefUtil.getState(context, String.class, EncryptUtils.IV_PARAM_KEY)));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                                    uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                                    try {
                                        saveDeviceUuidToSD(EncryptUtils.encrypt(uuid.toString(), KEY, SharedPrefUtil.getState(context, String.class, EncryptUtils.IV_PARAM_KEY)));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (UnsupportedEncodingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
                    }
                }
            }
        }
    }
    
    
    private static void saveDeviceUuidToSD(String uuid) {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File targetFile = new File(dirPath, DEVICE_UUID_FILE_NAME);
        if (targetFile != null) {
            if (targetFile.exists()) {
                
            } else {
                OutputStreamWriter osw;
                try {
                    osw = new OutputStreamWriter(new FileOutputStream(targetFile), "utf-8");
                    try {
                        osw.write(uuid);
                        osw.flush();
                        osw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    
    private static String recoverDeviceUuidFromSD(Context context) {
        try {
            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File dir = new File(dirPath);
            File uuidFile = new File(dir, DEVICE_UUID_FILE_NAME);
            if (!dir.exists() || !uuidFile.exists()) {
                return null;
            }
            FileReader fileReader = new FileReader(uuidFile);
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[100];
            int readCount;
            while ((readCount = fileReader.read(buffer)) > 0) {
                sb.append(buffer, 0, readCount);
            }
            //通过UUID.fromString来检查uuid的格式正确性
            UUID uuid = UUID.fromString(EncryptUtils.decrypt(sb.toString(), KEY, SharedPrefUtil.getState(context, String.class, EncryptUtils.IV_PARAM_KEY)));
            return uuid.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public UUID getUuid() {
        return uuid;
    }
}

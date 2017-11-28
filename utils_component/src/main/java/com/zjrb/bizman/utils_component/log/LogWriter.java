package com.zjrb.bizman.utils_component.log;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 保存日志到sdk卡
 * Created by lujialei
 */
public class LogWriter {
    
    public static void write(final String content, final String suffix) {
        new InnerAsyncTask().execute(new String[]{content, suffix});
    }
    
    static class InnerAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            FileOutputStream outStream = null;
            try {
                long currTime = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
                String[] times = sdf.format(new Date(currTime)).split("-");
                
                File parent = new File(Environment.getExternalStorageDirectory().getPath() +
                        File.separator + "dxFile" + File.separator + times[0]);
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                File[] files = parent.getParentFile().listFiles();
                if (files != null) {
                    List<File> fileList = new ArrayList<File>();
                    fileList.addAll(Arrays.asList(files));
                    int i = 0;
                    while (fileList.size() > i) {
                        if (fileList.get(i).isFile()) {
                            File delFile = fileList.remove(i);
                            delFile.delete();
                        } else {
                            i++;
                        }
                    }
                    i = 0;
                    long before4Day = currTime - 4 * 24 * 60 * 60 * 1000;
                    while (fileList.size() > 4) {
                        File file = fileList.get(i);
                        if (file.lastModified() < before4Day) {
                            fileList.remove(i);
                            file.delete();
                        } else {
                            i++;
                        }
                    }
                }
                String str = params[0];
                str = str.replace("Response", "");
                File file = new File(parent, str + "_" + times[1] + ".txt");
                outStream = new FileOutputStream(file);
                outStream.write(params[1].getBytes());
                outStream.flush();
                outStream.close();
            } catch (Exception e) {
                if (outStream != null) {
                    try {
                        outStream.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            }
            return null;
        }
    }
}

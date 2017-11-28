package com.zjrb.bizman.utils_component.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;


import com.zjrb.bizman.utils_component.log.LogUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {
    private static final String LOG_TAG = FileUtils.class.getSimpleName();
    
    public static File mkdirs(String path) {
        File fp = new File(path);
        if (!fp.exists()) {
            fp.mkdirs();
        }
        
        return fp;
    }
    
    public static File mkfile(String path) {
        File fp = new File(path);
        if (!fp.exists()) {
            try {
                fp.createNewFile();
            } catch (IOException e) {
                LogUtils.e(LOG_TAG, e.getMessage());
            }
        }
        
        return fp;
    }
    
    public static boolean WriteStringToFile(String filePath, String s) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(s);
            return true;
        } catch (FileNotFoundException e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return false;
        }
    }
    
    public static void saveFile(final String filePath, final InputStream in) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = in.read(b)) != -1) {
                out.write(b, 0, length);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    
    public static boolean saveFile(final String filePath, final Bitmap bitmap) {
        if (filePath == null || filePath.length() == 0 || bitmap == null || bitmap.isRecycled())
            return false;
        
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (!parent.exists())
            parent.mkdirs();
        
        if (file.exists())
            file.delete();
        
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return true;
    }
    
    public static boolean saveTxtFile(final String filePath, final String param) {
        if (filePath == null || filePath.length() == 0 || param == null)
            return false;
        
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (!parent.exists())
            parent.mkdirs();
        
        if (file.exists())
            file.delete();
        
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(param.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return true;
    }
    
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileUtils.readToBuffer(sb, filePath);
        return sb.toString();
    }
    
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            buffer.append("\n");
            line = reader.readLine();
        }
        reader.close();
        is.close();
    }
    
    public static String getPath(Activity context, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        
        Cursor actualimagecursor = context.managedQuery(uri, proj, null, null, null);
        if (actualimagecursor != null) {
            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            
            actualimagecursor.moveToFirst();
            
            String img_path = actualimagecursor.getString(actual_image_column_index);
            return null == img_path ? null : img_path;
        }
        
        return null;
    }
    
    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        
        return baos.toString();
    }
    
    public static boolean unzip(String zipPath, String storageDirectory) {
        if (zipPath == null || zipPath.length() == 0 || storageDirectory == null || storageDirectory.length() == 0)
            return false;
        
        boolean result = false;
        
        int buffer = 4096;
        String filePath = storageDirectory;
        ZipFile zipFile = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            zipFile = new ZipFile(zipPath);
            Enumeration<? extends ZipEntry> emu = zipFile.entries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = emu.nextElement();
                if (entry.isDirectory()) {
                    new File(filePath + entry.getName()).mkdirs();
                    continue;
                }
                bis = new BufferedInputStream(zipFile.getInputStream(entry));
                File file = new File(filePath + entry.getName());
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos, buffer);
                int count;
                byte data[] = new byte[buffer];
                while ((count = bis.read(data, 0, buffer)) != -1) {
                    bos.write(data, 0, count);
                }
                bos.flush();
            }
            result = true;
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            result = false;
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException e) {
                LogUtils.e(LOG_TAG, e.getMessage());
                result = false;
            }
        }
        
        if (!result) {
            new File(storageDirectory).delete();
        }
        
        return result;
    }
    
    public static String getFilePathFromContentUri(Uri selectedVideoUri, ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaColumns.DATA};
        
        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        
        cursor.moveToFirst();
        
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }
    
    public static String readParamFileToString(String path) {
        String param = null;
        if (path == null || path.length() == 0)
            return null;
        
        StringBuffer buffer = new StringBuffer();
        
        InputStream is = null;
        BufferedReader reader = null;
        
        try {
            String line;
            is = new FileInputStream(path);
            reader = new BufferedReader(new InputStreamReader(is));
            line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                buffer.append("\r\n");
                line = reader.readLine();
            }
            
            param = buffer.toString();
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LogUtils.e(LOG_TAG, e.getMessage());
                }
            }
            
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LogUtils.e(LOG_TAG, e.getMessage());
                }
            }
        }
        
        return param;
    }
    
    
    /**
     * 删除File方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
    
    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    
    public static void fileChmod777(File file){
        try {
            String command = "chmod 777 " + file.getAbsolutePath();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

package com.zjrb.bizman.utils_component.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;


import com.zjrb.bizman.utils_component.log.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapUtils {
    private static final String LOG_TAG = BitmapUtils.class.getSimpleName();
    public static final Config BITMAP_CONFIG = Config.ARGB_8888;
    
    public static final int MAX_PHOTO_WIDTH = 1024;
    public static final int MAX_PHOTO_HEIGHT = 1024;
    
    
    public static Bitmap scaleImage(Bitmap bitmap, int width, int height) {
        if (bitmap == null)
            return null;
        
        final int widthOri = bitmap.getWidth();
        final int heightOri = bitmap.getHeight();
        
        if (widthOri == 0 || heightOri == 0)
            return null;
        
        float scaleWidth = ((float) width) / widthOri;
        float scaleHeight = ((float) height) / heightOri;
        
        float scale = scaleWidth >= scaleHeight ? scaleWidth : scaleHeight;
        final Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        
        return Bitmap.createBitmap(bitmap, 0, 0, widthOri, heightOri, matrix, false);
    }
    
    public static Bitmap decodeAndScaleImage(String filePath, int width, int height) {
        try {
            BitmapFactory.Options scaleOptions = new BitmapFactory.Options();
            scaleOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, scaleOptions);
            
            int scale = 1;
            while (scaleOptions.outWidth / scale / 2 >= width && scaleOptions.outHeight / scale / 2 >= height) {
                scale *= 2;
            }
            
            BitmapFactory.Options outOptions = new BitmapFactory.Options();
            outOptions.inSampleSize = scale;
            return BitmapFactory.decodeFile(filePath, outOptions);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return null;
    }
    
    public static Bitmap decodeAndScaleImage(Resources res, int id, int width, int height) {
        try {
            BitmapFactory.Options scaleOptions = new BitmapFactory.Options();
            scaleOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, id, scaleOptions);
            
            int scale = 1;
            while (scaleOptions.outWidth / scale / 2 >= width && scaleOptions.outHeight / scale / 2 >= height) {
                scale *= 2;
            }
            
            BitmapFactory.Options outOptions = new BitmapFactory.Options();
            outOptions.inSampleSize = scale;
            return BitmapFactory.decodeResource(res, id, outOptions);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return null;
    }
    
    public static Bitmap decodeAndScaleImage(byte[] bytes, int width, int height) {
        try {
            // decode the image file
            BitmapFactory.Options scaleOptions = new BitmapFactory.Options();
            scaleOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bytes, 0, bytes.length, scaleOptions);
            
            // find the correct scale value as a power of 2.
            int scale = 1;
            while (scaleOptions.outWidth / scale / 2 >= width && scaleOptions.outHeight / scale / 2 >= height) {
                scale *= 2;
            }
            
            // decode with the sample size
            BitmapFactory.Options outOptions = new BitmapFactory.Options();
            outOptions.inSampleSize = scale;
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, outOptions);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return null;
    }
    
    public static byte[] bmpToByteArray(final Bitmap bmp, CompressFormat format, int quality, final boolean needRecycle) {
        if (bmp == null)
            return null;
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(format, quality, output);
        if (needRecycle) {
            bmp.recycle();
        }
        
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return result;
    }
    
    public static int[] getFitInSize(int srcW, int srcH, int dstW, int dstH) {
        int[] size = new int[2];
        size[0] = size[1] = 0;
        
        double scaleX = dstW / (double) srcW;
        double scaleY = dstH / (double) srcH;
        if (scaleX > scaleY) {
            size[0] = (int) (srcW * scaleY + 0.5);
            size[1] = dstH;
        } else {
            size[0] = dstW;
            size[1] = (int) (srcH * scaleX + 0.5);
        }
        
        return size;
    }
    
    public static Bitmap loadBitmap(final String path) {
        Bitmap bitmap = decode(path, MAX_PHOTO_WIDTH, MAX_PHOTO_HEIGHT);
        if (bitmap == null)
            return null;
        
        bitmap = handleOrientation(bitmap, path);
        bitmap = copy(bitmap);
        
        return bitmap;
    }
    
    public static Bitmap loadBitmap(final String path, boolean fitIn) {
        Bitmap bitmap = decode(path, MAX_PHOTO_WIDTH, MAX_PHOTO_HEIGHT);
        if (bitmap == null)
            return null;
        
        bitmap = handleOrientation(bitmap, path);
        if (fitIn)
            bitmap = handleFitIn(bitmap, MAX_PHOTO_WIDTH, MAX_PHOTO_HEIGHT);
        bitmap = copy(bitmap);
        
        return bitmap;
    }
    
    public static Bitmap fitInBitmap(Bitmap bitmap) {
        bitmap = handleFitIn(bitmap, MAX_PHOTO_WIDTH, MAX_PHOTO_HEIGHT);
        bitmap = copy(bitmap);
        return bitmap;
    }
    
    public static Bitmap loadBitmap(final String path, boolean fitIn, int width, int height) {
        Bitmap bitmap = decode(path, width, height);
        if (bitmap == null)
            return null;
        
        bitmap = handleOrientation(bitmap, path);
        if (fitIn)
            bitmap = handleFitIn(bitmap, width, height);
        bitmap = copy(bitmap);
        
        return bitmap;
    }
    
    public static Bitmap loadBitmap(final String path, boolean fitIn, boolean copy, int width, int height) {
        Bitmap bitmap = decode(path, width, height);
        if (bitmap == null)
            return null;
        
        bitmap = handleOrientation(bitmap, path);
        if (fitIn)
            bitmap = handleFitIn(bitmap, width, height);
        if (copy)
            bitmap = copy(bitmap);
        
        return bitmap;
    }
    
    private static Bitmap decode(final String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        if (options.outHeight <= 0 || options.outWidth <= 0)
            return null;
        
        int max = Math.max(width, height);
        
        int sWidth = options.outWidth / max;
        int sHeight = options.outHeight / max;
        
        int s = sWidth >= sHeight ? sWidth : sHeight;
        if (s < 1)
            s = 1;
        
        options.inSampleSize = s;
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPreferredConfig = Config.ARGB_8888;
        
        try {
            return BitmapFactory.decodeFile(path, options);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        } catch (Error e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return null;
    }
    
    private static Bitmap handleOrientation(Bitmap bitmap, final String path) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        int degrees = 0;
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                degrees = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                degrees = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                degrees = 270;
            }
        }
        
        if (degrees == 0)
            return bitmap;
        
        return BitmapUtils.rotate(bitmap, degrees);
    }
    
    private static Bitmap handleFitIn(Bitmap bitmap, int width, int height) {
        try {
            Matrix localMatrix = new Matrix();
            float f1 = (float) width / (float) bitmap.getWidth();
            float f2 = (float) height / (float) bitmap.getHeight();
            float f3;
            if (f1 < f2)
                f3 = f1;
            else
                f3 = f2;
            
            localMatrix.postScale(f3, f3);
            Bitmap bitmapFitIn = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), localMatrix, true);
            if (bitmapFitIn != bitmap) {
                bitmap.recycle();
                bitmap = bitmapFitIn;
            }
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        } catch (Error e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return bitmap;
    }
    
    public static Bitmap createRotateBitmap(Bitmap bitmap, int width, int height, float degree){
        Matrix m = new Matrix();
        m.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);
    }
    
    private static Bitmap copy(Bitmap bitmap) {
        try {
            Config localConfig = Config.ARGB_8888;
            Bitmap bitmapCopy = bitmap.copy(localConfig, true);
            if (bitmapCopy != bitmap) {
                bitmap.recycle();
                bitmap = bitmapCopy;
                bitmap.getWidth();
                bitmap.getHeight();
            }
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        } catch (Error e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return bitmap;
    }
    
    public static Bitmap loadBitmapOverdue(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Config.ARGB_8888;
        options.inInputShareable = true;
        options.inPurgeable = true;
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        if (options.outHeight <= 0 || options.outWidth <= 0)
            return null;
        
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int degrees = 0;
        int h = options.outHeight;
        int w = options.outWidth;
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                degrees = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                degrees = 90;
                h = options.outWidth;
                w = options.outHeight;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                degrees = 270;
                h = options.outWidth;
                w = options.outHeight;
            }
        }
        
        int width = MAX_PHOTO_WIDTH;
        int height = MAX_PHOTO_HEIGHT;
        int[] size = getFitInSize(w, h, width, height);
        width = size[0];
        height = size[1];
        int hScale = (int) Math.floor(h / (double) height);
        int wScale = (int) Math.floor(w / (double) width);
        options.inSampleSize = hScale;
        if (options.inSampleSize < wScale)
            options.inSampleSize = wScale;
        if (options.inSampleSize < 1)
            options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        
        try {
            bitmap = BitmapFactory.decodeFile(path, options);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        } catch (Error er) {
            er.printStackTrace();
            bitmap = null;
        }
        
        if (bitmap == null) {
            return bitmap;
        }
        
        if (degrees == 90 || degrees == 270) {
            int temp = width;
            width = height;
            height = temp;
        }
        
        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();
        if (srcWidth <= width && srcHeight <= height) {
            if (degrees > 0) {
                Bitmap b = rotate(bitmap, degrees);
                bitmap = b;
            }
            return bitmap;
        }
        
        Bitmap dstBitmap = null;
        try {
            dstBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            if (dstBitmap != bitmap) {
                bitmap.recycle();
                bitmap = null;
            }
        } catch (Error er) {
            er.printStackTrace();
        }
        
        if (degrees > 0) {
            Bitmap b = rotate(dstBitmap, degrees);
            dstBitmap = b;
        }
        
        return dstBitmap;
    }
    
    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
                if (b != b2) {
                    b.recycle();
                    b = b2;
                }
            } catch (Error er) {
                er.printStackTrace();
            }
        }
        return b;
    }
    
    public static Bitmap decodeFile(final String path) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Config.ARGB_8888;
            return BitmapFactory.decodeFile(path, options);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        } catch (Error e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }
        
        return null;
    }
    
    public static int getPixelBytes(Config config) {
        int b = 2;
        if (config == Config.RGB_565) {
            b = 2;
        } else if (config == Config.ARGB_8888) {
            b = 3;
        }
        return b;
    }
}
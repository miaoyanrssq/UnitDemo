package com.zjrb.bizman.net_component;

/**
 * Created by lujialei on 2017/11/24.
 */

public interface OnProgressListener {
    void onDownloadStart();
    void onDownloading(int progress);
    void onDownloadSuccess();
    void onDownloadError();
}

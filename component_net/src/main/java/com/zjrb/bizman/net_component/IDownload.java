package com.zjrb.bizman.net_component;

import com.zjrb.bizman.net_component.impl.DownloadRequester;

import java.io.File;

/**
 * Created by lujialei on 2017/11/24.
 */

public interface IDownload {
    void download(final String url, final File saveDir, final DownloadRequester.OnDownloadListener listener);

}

package com.yf666.myutils;

import android.os.AsyncTask;

/**
 * Created by toothwind on 2017/9/28.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
public class PingTest extends AsyncTask<String, Void, Boolean> {

    /*
    参数1 传递给异步任务执行时的参数类型
    参数2 异步任务执行的时候 返回给UI线程的参数类型
    参数3 异步任务执行完后 返回值给UI线程的结果类型
    空 -->Void
     */

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            //ping -c 3 -w 100  中  ，-c 是指ping的次数 3是指ping 3次 ，-w 100  以秒为单位指定超时间隔，是指超时时间为100秒
            Process p = Runtime.getRuntime().exec("ping -c " + 1 + " -w " + 100 + " " + "www.baidu.com");
            int status = p.waitFor();
            if (status == 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}

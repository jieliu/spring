package com.tianma.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * upload file
 */
public class UploadFile {

    private static final Logger logger = LoggerFactory.getLogger(UploadFile.class);

	public static String upload(String strUrl, String filePath) {
        try {
            String CRLF = "\r\n";
            String CHARSET = "utf-8";
            int count;
            byte[] buf = new byte[4096];

            URL url = new URL(strUrl);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection con = (HttpURLConnection) urlConnection;
            String boundary = Long.toHexString(System.currentTimeMillis());

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            OutputStream outputStream = con.getOutputStream();

            //获取文件输入流
            FileInputStream fileInputStream = new FileInputStream(filePath);

            // Send file.
            outputStream.write(("--" + boundary + CRLF).getBytes());
            outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + filePath + "\"" + CRLF).getBytes());
            outputStream.write(("Content-Type: application/octet-stream; charset=" + CHARSET + CRLF).getBytes());
            outputStream.write(CRLF.getBytes());

            while ((count = fileInputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, count);
            }

            outputStream.write(CRLF.getBytes());
            outputStream.write(("--" + boundary + "--" + CRLF).getBytes());
            outputStream.close();

            int retCode = con.getResponseCode();
            String result = "";
            if (retCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String tmpResult = "";
                while ((tmpResult = reader.readLine()) != null) {
                    result += tmpResult;
                }
            } else {
                result = "远程服务器连接失败,错误代码:" + retCode;
            }
            return result;
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return "error!";
    }
}

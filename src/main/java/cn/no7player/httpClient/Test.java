package cn.no7player.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.Environment;

import java.io.File;
import java.util.Map;

public class Test {

    public static void main(String args[]) throws Exception{

        HttpClientUtils httpClientUtils = HttpClientUtils.getInstance();
        String result =  httpClientUtils.httpGet("http://www.baidu.com/");
        System.out.println(result);

//        //定义httpclient连接池
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        // 设置最大连接数
//        cm.setMaxTotal(200);
//        // 设置每个主机地址的并发数
//        cm.setDefaultMaxPerRoute(20);
//
//        // 构建请求配置信息
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间
//                .setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
//                .setSocketTimeout(10 * 1000) // 数据传输的最长时间
//                .setStaleConnectionCheckEnabled(true) // 提交请求前测试连接是否可用
//                .build();
//
//        String result =  doGet(cm, requestConfig,"http://www.baidu.com/",null,null);
//        System.out.println("----------------------------*********---------------------------------");
//        System.out.println(result);


    }

    public static String doGet(HttpClientConnectionManager hccm, RequestConfig requestConfig,
                               String url, Map<String, String> params, String encode) throws Exception{
        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(hccm).build();
        if(null != params){
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.setParameter(entry.getKey(), entry.getValue());
            }
            url = builder.build().toString();
        }
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                if(encode == null){
                    encode = "UTF-8";
                }
                return EntityUtils.toString(response.getEntity(), encode);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
        return null;
    }







    public void uploadHttpClient(String murl){
//        HttpClient client=HttpClients.createDefault();
//        HttpPost httpPost=new HttpPost(murl);//通过post传递
//        /**绑定数据  这里需要注意  如果是中文  会出现中文乱码问题 但只要按设置好*/
//        MultipartEntity muit=new MultipartEntity();
//        // 上传 文本， 转换编码为utf-8 其中"text" 为字段名，
//        //Charset.forName(CHARSET)为参数值,可设置为UTF-8，其实就是正常的值转换成utf-8的编码格式
//        // 后边new StringBody(text,Charset.forName(CHARSET))
//
//        File parent= Environment.getExternalStorageDirectory();//路径
//        File fileupload=new File(parent,"zxy.jpg");
//        FileBody fileBody=new FileBody(fileupload);
//        muit.addPart("file",fileBody);
//        httpPost.setEntity(muit);
//        /**发送请求*/
//        try {
//            HttpResponse response=client.execute(httpPost);
//            //判断师傅上传成功  返回200
//            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
//                System.out.println(EntityUtils.toString(response.getEntity()));
//            }
//        } catch (ClientProtocolException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

    }


//    public String uploadFileImpl(String serverUrl, String localFilePath,
//                                 String serverFieldName, Map<String, String> params)
//            throws Exception {
//        String respStr = null;
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            HttpPost httppost = new HttpPost(serverUrl);
//            FileBody binFileBody = new FileBody(new File(localFilePath));
//
//            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
//                    .create();
//            // add the file params
//            multipartEntityBuilder.addPart(serverFieldName, binFileBody);
//            // 设置上传的其他参数
//            setUploadParams(multipartEntityBuilder, params);
//
//            HttpEntity reqEntity = multipartEntityBuilder.build();
//            httppost.setEntity(reqEntity);
//
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                System.out.println(response.getStatusLine());
//                HttpEntity resEntity = response.getEntity();
//                respStr = getRespString(resEntity);
//                EntityUtils.consume(resEntity);
//            } finally {
//                response.close();
//            }
//        } finally {
//            httpclient.close();
//        }
//        System.out.println("resp=" + respStr);
//        return respStr;
//    }

}


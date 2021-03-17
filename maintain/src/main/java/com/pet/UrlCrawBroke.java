package com.pet;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 如果设置了自定义域名，将博客域名前缀填写入19行的变量userId中，点击运行
 */
public class UrlCrawBroke {

    private static String userId = "weixin_38045214";
    private static final String homeUrl = "https://blog.csdn.net/" + userId + "/article/list/";
    private static Set<String> urlSet = new HashSet<>();

    private static void getUrls() throws IOException, InterruptedException {
        InputStream is;
        String pageStr;
        StringBuilder curUrl;
        int maxPages = 10;
        for (int i = 1; i < maxPages; i++) {
            Thread.sleep(500);
            System.out.println("正在查找第 " + i + " 页中的博客地址");
            curUrl = new StringBuilder(homeUrl);
            curUrl.append(i);
            System.out.println(curUrl);
            is = doGet(curUrl.toString());
            pageStr = inputStreamToString(is);// 一整页的html源码

            List<String> list = getMatherSubstrs(pageStr);
            urlSet.addAll(list);
            System.out.println("加入 " + list.size() + " 个url");
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        csdn();

    }

    private static void setNetWork() throws IOException {
        String str1 = "192.168.85.2";
        String str2 = "255.255.254.0";
        String[] command1 = {"netsh", "interface", "ip", "set", "address",
                "name=", "Local Area Connection", "source=static", "addr=", str1,
                "mask=", str2};
        Process pp = java.lang.Runtime.getRuntime().exec(command1);
    }

    private static String getWindowsMACAddress() throws SocketException {
        String allipaddress = null;
        ArrayList ar = new ArrayList();
        Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();

            Enumeration cardipaddress = ni.getInetAddresses();
//          InetAddress ip = (InetAddress) cardipaddress.nextElement();
//          System.out.println(ip.getHostAddress());
            ar.add(ni.getName());
//          if(!ip.getHostAddress().equalsIgnoreCase("127.0.0.1") )
//            {    ar.add(ni.getName()+":");
//                allipaddress=ip.getHostAddress();
//                while(cardipaddress.hasMoreElements())
//                 {
//                     ip = (InetAddress) cardipaddress.nextElement();
//                     allipaddress=allipaddress+" , "+ip.getHostAddress();
//                 }
//                 ar.add(allipaddress);
//                 System.out.println(ip.getHostAddress());
//                 System.out.println(allipaddress);
//            }
//          else
//              continue;
//

        }
        for (int i = 0; i < ar.size(); ) {
            System.out.println(ar.get(i++));
        }
        return allipaddress;
    }


    private static void csdn() throws InterruptedException {
        while (true) {
//
            // ----------------------------------------------遍历每一页 获取文章链接----------------------------------------------
//            if (urlAll()) {
//                return;
//            }

            urlSet.add("https://blog.csdn.net/weixin_38045214/article/details/114363996");
            urlSet.add("https://blog.csdn.net/weixin_38045214/article/details/114133678");
            // ---------------------------------------------------多线程访问每个链接---------------------------------------------------
            ExecutorService executor = Executors.newCachedThreadPool();
            int threadCount = 1; // 并发线程数量
            for (int i = 0; i < threadCount; i++) {
                executor.execute(new MyThread(urlSet));
            }
            executor.shutdown();

            Thread.sleep(20000);
        }
    }

    private static boolean urlAll() {
        try {
            getUrls();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return true;
        }

        // ---------------------------------------------------打印每个链接---------------------------------------------------
        System.out.println("打印每个链接");
        for (String s : urlSet) {
            System.out.println(s);
        }
        System.out.println("打印每个链接完毕");
        return false;
    }

    private static InputStream doGet(String urlstr) throws IOException {
        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int i = (int) (Math.random() * 100);
        System.err.println(i);
        if (i % 2 == 0) {
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        } else {
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
        }


        return conn.getInputStream();
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        int byteLength;
        StringBuilder sb = new StringBuilder();
        while ((byteLength = is.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, byteLength, StandardCharsets.UTF_8));
        }
        return sb.toString();
    }

    // 正则匹配
    private static List<String> getMatherSubstrs(String str) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile("(?<=href=\")https://blog.csdn.net/weixin_38045214/article/details/[0-9]{8,9}(?=\")");
        Matcher m = p.matcher(str);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }


}

class MyThread implements Runnable {
    private List<String> urlList;

    MyThread(Set<String> urls) {
        List<String> list = new ArrayList<>(urls);
        Collections.shuffle(list);
        this.urlList = list;
    }

    @Override
    public void run() {
        int i = 0;
        for (String s : urlList) {
            try {
                doGet(s);
                System.out.println(Thread.currentThread().getName() + "成功访问第" + (++i) + "个链接,共" + urlList.size() + "个:" + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doGet(String urlstr) throws IOException {
        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        conn.getInputStream();
    }
}
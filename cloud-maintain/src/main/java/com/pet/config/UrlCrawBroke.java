package com.pet.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlCrawBroke {

    private static String userId = "weixin_38045214";
    private static final String homeUrl = "https://blog.csdn.net/" + userId + "/article/list/";
    private static Set<String> urlSet = new HashSet<>();

    private static void getUrls() throws IOException, InterruptedException {
        InputStream is;
        String pageStr;
        StringBuilder curUrl;
        int maxPages = 38;
        for (int i = 1; i < maxPages; i++) {
            Thread.sleep(2500);
            System.out.println("正在查找第 " + i + " 页中的博客地址");
            curUrl = new StringBuilder(homeUrl);
            curUrl.append(i);
            System.out.println(curUrl);
            is = doGet(curUrl.toString());
            pageStr = inputStreamToString(is);

            List<String> list = getMatherSubstrs(pageStr);
            urlSet.addAll(list);
            System.out.println("加入 " + list.size() + " 个url");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        csdn();
    }

    private static void csdn() throws InterruptedException {
        while (true) {
            if (urlAll()) {
                return;
            }
            ExecutorService executor = Executors.newCachedThreadPool();
            int threadCount = 1;
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
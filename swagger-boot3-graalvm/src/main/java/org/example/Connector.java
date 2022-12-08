package org.example;

import org.json.JSONObject;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.xml.bind.DatatypeConverter;
public class Connector {
    private final String USER__AGENT = "Mozilla/5.0";
    private final String HTTPS       = "https://";
    private       String m_server;
    //+--------------------------------------------------------------+
    //| Disable SSL when using untrusted certificates                |
    //+--------------------------------------------------------------+
    private static void disableSslVerification() {
        try {
            //---
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        //---
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }
                        //---
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };
            //---
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            //---
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            //---
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch(KeyManagementException e) {
            e.printStackTrace();
        }
    }
    //+--------------------------------------------------------------+
    //| Initialize connection                                        |
    //+--------------------------------------------------------------+
    public boolean initConnection(String server) {
        disableSslVerification();
        m_server = HTTPS + server;
        return(true);
    }
    //+--------------------------------------------------------------+
    //| Authentication                                               |
    //+--------------------------------------------------------------+
    public boolean sendAuth(String login, String password, String build, String agent) {
        String response="";
        if(m_server.isEmpty()) {
            System.out.println("call initConnection with correct server first");
            return(false);
        }
        //---
        String path = "/api/auth/start?version=" + build + "&agent=" + agent + "&login=" + login + "&type=manager";
        //---
        try {
            System.out.println("sendAuth - send Http GET request");
            response=sendGet(path);
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        //---
        if(!response.isEmpty()) {
            JSONObject json_obj = new JSONObject(response);
            String retcode = json_obj.getString("retcode");
            System.out.println(retcode);
            if(retcode.equals("0 Done")) {
                String srv_rand = json_obj.getString("srv_rand");
                byte[] byte_array = DatatypeConverter.parseHexBinary(srv_rand);
                printByteResult(byte_array);
                //---
                byte[] cli_rand_buf = GetRandomHex();
                printByteResult(cli_rand_buf);
                //---
                String cli_rand_string = DatatypeConverter.printHexBinary(cli_rand_buf);
                //---
                String srv_rand_answer = GetHashFromPassword(password, byte_array);
                //---
                String auth_response="/api/auth/answer?srv_rand_answer=" + srv_rand_answer + "&cli_rand=" + cli_rand_string;
                try {
                    System.out.println("sendAuth - send Http GET request");
                    sendGet(auth_response);
                } catch(Exception ex) {
                    System.out.println(ex.toString());
                }
            }
            return(true);
        } else {
            return(false);
        }
    }
    //+--------------------------------------------------------------+
    //| Send GET request                                             |
    //+--------------------------------------------------------------+
    public String sendGet(String path) throws Exception {
        String url = m_server + path;
        //---
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //---
        con.setConnectTimeout(1000);
        con.setRequestMethod("GET");
        //---
        con.setRequestProperty("User-Agent", USER__AGENT);
        //---
        int responseCode = con.getResponseCode();
        //---
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        //---
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        //---
        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //---
        System.out.println(response.toString());
        return(response.toString());
    }
    //+------------------------------------------------------------------+
    //| Get a random sequence                                            |
    //+------------------------------------------------------------------+
    private byte[] GetRandomHex() {
        byte[] base_byte = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        //---
        SecureRandom random = new SecureRandom();
        random.nextBytes(base_byte);
        //---
        return base_byte;
    }
    //+------------------------------------------------------------------+
    //| Output the result                                                |
    //+------------------------------------------------------------------+
    private void printByteResult(byte[] array_to_print) {
        if(array_to_print!=null) {
            StringBuilder sb_unsigned_bytes = new StringBuilder();
            for(byte b : array_to_print)
                sb_unsigned_bytes.append((b & 0xff));
            System.out.println("result array =" + sb_unsigned_bytes.toString());
        } else {
            System.out.println("array_to_print is null");
        }
    }
    //+------------------------------------------------------------------+
    //| Get the result in HEX format                                     |
    //+------------------------------------------------------------------+
    private String getHEXResult(byte[] array_to_print) {
        if(array_to_print!=null) {
            StringBuilder sb = new StringBuilder();
            for(byte b : array_to_print)
                sb.append(String.format("%02X", b));
            return(sb.toString());
        } else {
            System.out.println("array_to_print is null");
            return null;
        }
    }
    //+------------------------------------------------------------------+
    //| Get a password hash                                              |
    //+------------------------------------------------------------------+
    private String GetHashFromPassword(String password, byte[] randCode) {
        if(password.isEmpty()||randCode==null)
            return null;
        //---
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] password_bytes_unicode = md.digest(password.getBytes(StandardCharsets.UTF_16LE));
            //---
            printByteResult(password_bytes_unicode);
            //---
            String api_word_str = "WebAPI";
            byte[] apiWord = api_word_str.getBytes(StandardCharsets.UTF_8);
            System.out.println("WebAPI byte array =" + Arrays.toString(apiWord));
            //---
            byte[] hashContains = CopyBuffer(password_bytes_unicode, apiWord);
            //---
            printByteResult(hashContains);
            //---
            byte[] final_hash = md.digest(hashContains);
            printByteResult(final_hash);
            //---
            byte[] final_hash_plus_rand = CopyBuffer(final_hash, randCode);
            byte[] final_hash_plus_rand_md5 = md.digest(final_hash_plus_rand);
            //---
            return getHEXResult(final_hash_plus_rand_md5);
        } catch(Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    //+------------------------------------------------------------------+
    //| Copy the buffer                                                  |
    //+------------------------------------------------------------------+
    public static byte[] CopyBuffer(byte[] ar1, byte[] ar2) {
        byte[] result = new byte[ar1.length + ar2.length];
        //---
        try {
            System.arraycopy(ar1, 0, result, 0, ar1.length);
            System.arraycopy(ar2, 0, result, ar1.length, ar2.length);
        } catch(Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
        //---
        return result;
    }
}
//+------------------------------------------------------------------+
package com.example.demo.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: zouye
 * Date: 1/9/19
 * Time: 1:29 PM
 */
public class StringUtil {


    public static final Pattern pattern = Pattern.compile("\\{([a-zA-Z0-9_\\-]+)\\}");

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 处理标签转义
     *
     * @param value
     * @return
     */
    public static String cleanX(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isEmpty(Object s) {
        if (s instanceof String) {
            return s == null || s.toString().length() == 0;
        }
        if (s instanceof Collection) {
            return null == s || ((Collection) s).size() <= 0;
        }
        if (s instanceof Map) {
            return null == s || ((Collection) s).size() <= 0;
        }
        if (s instanceof Integer) {
            return null == (Integer) s || (Integer) s <= 0;
        } else {
            return (s == null || "".equals(s));
        }
    }

    public static Boolean deviceIsMobile(String agent) {
        if (!isEmpty(agent)) {
            for (String s : Constants.phoneDevice) {
                if (agent.indexOf(s) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Map object2Map(Object obj) {
        Map hashMap = new HashMap();
        try {
            Class c = obj.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = new String(field.getName());
                hashMap.put(name, field.get(obj));

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 字符串为 null 或者内部字符全部为 ' ' '\t' '\n' '\r' 这四类字符时返回 true
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        int len = str.length();
        if (len == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            switch (str.charAt(i)) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    // case '\b':
                    // case '\f':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static String vagueTel(String tel) {
        return tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String vagueEmail(String email) {
        return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
    }

    public static byte[] xorEncode(byte[] data, String key) {

        //将任意长的key转变成长度为256的新key
        byte[] keyBytes = new byte[256];
        for (int i = 0; i < key.getBytes().length; i++) {
            keyBytes[i] = key.getBytes()[i % key.getBytes().length];
        }
        //该byte[]为输出密文
        byte[] encodeBytes = new byte[data.length];
        //在每一轮循环中对key下功夫，比如置换
        int j = 0;
        int k = 0;
        for (int i = 0; i < encodeBytes.length; i++) {
            k = 0xff & k + 1;
            j = 0xff & j + keyBytes[k];
            int m = keyBytes[k];
            keyBytes[k] = keyBytes[j];
            keyBytes[j] = (byte) m;
            int n = 0xff & keyBytes[j] + keyBytes[k];
            encodeBytes[i] = (byte) (data[i] ^ keyBytes[n]);
        }
        return encodeBytes;
    }

    public static String concat(String spliter, byte[] args) {
        if (args == null) {
            return "";
        } else if (args.length <= 1) {
            return args.length == 1 ? String.valueOf(args[0]) : "";
        } else {
            StringBuilder ret = new StringBuilder();
            int n = spliter.length();
            byte[] var4 = args;
            int var5 = args.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                byte s = var4[var6];
                ret.append(spliter).append(Byte.toUnsignedInt(s));
            }

            return ret.toString();
        }
    }

    public static String concat(String spliter, int[] args) {
        if (args == null) {
            return "";
        } else if (args.length <= 1) {
            return args.length == 1 ? String.valueOf(args[0]) : "";
        } else {
            StringBuilder ret = new StringBuilder();
            int n = spliter.length();
            int[] var4 = args;
            int var5 = args.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                int s = var4[var6];
                ret.append(spliter).append(s);
            }

            return ret.toString();
        }
    }

    public static String concat(String spliter, double[] args) {
        if (args == null) {
            return "";
        } else if (args.length <= 1) {
            return args.length == 1 ? String.valueOf(args[0]) : "";
        } else {
            StringBuilder ret = new StringBuilder();
            int n = spliter.length();
            double[] var4 = args;
            int var5 = args.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                double s = var4[var6];
                ret.append(spliter).append(s);
            }

            return ret.toString();
        }
    }

    public static String concat(String spliter, Object[] args) {
        if (args == null) {
            return "";
        } else if (args.length <= 1) {
            if (args.length == 1) {
                return args[0] == null ? "null" : args[0].toString();
            } else {
                return "";
            }
        } else {
            StringBuilder ret = new StringBuilder();
            int n = spliter.length();
            Object[] var4 = args;
            int var5 = args.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Object s = var4[var6];
                if (s == null) {
                    s = "null";
                    ret.append(spliter).append(s.toString());
                } else if (!s.getClass().isArray()) {
                    ret.append(spliter).append(s.toString());
                } else {
                    Object[] ss = (Object[]) ((Object[]) s);
                    ret.append("[");
                    Object[] var9 = ss;
                    int var10 = ss.length;

                    for (int var11 = 0; var11 < var10; ++var11) {
                        Object obj = var9[var11];
                        ret.append(obj.toString()).append(",");
                    }

                    ret.append("]");
                }
            }

            if (ret.length() > n) {
                ret.delete(0, n);
            }

            return ret.toString();
        }
    }

    public static String concat(String format, String spliter, List<Object> args) {
        int n = args.size();
        if (n > 1) {
            int len = spliter.length();
            StringBuilder ret = new StringBuilder();
            Iterator var6 = args.iterator();

            while (var6.hasNext()) {
                Object arg = var6.next();
                Object[] p;
                if (arg instanceof List) {
                    List<Object> l = (List) arg;
                    p = l.toArray();
                } else {
                    p = new Object[]{arg};
                }

                String s = String.format(format, p);
                ret.append(spliter).append(s);
            }

            if (ret.length() > len) {
                ret.delete(0, len);
            }

            return ret.toString();
        } else {
            if (n == 1) {
                ;
            }

            return "";
        }
    }

    public static class JsonHelper {
        public static final Gson gson = new Gson();

        public JsonHelper() {
        }

        public static String toJson(Object src) {
            return gson.toJson(src);
        }

        public static <T> T fromJson(String src, Class<T> c) {
            try {
                return gson.fromJson(src, c);
            } catch (JsonSyntaxException var3) {
                return null;
            }
        }

        public static <T> T fromJson(JsonElement src, Class<T> c) {
            try {
                return gson.fromJson(src, c);
            } catch (JsonSyntaxException var3) {
                return null;
            }
        }

        public static List fromJson(String src, Type c) {
            try {
                return (List) gson.fromJson(src, c);
            } catch (JsonSyntaxException var3) {
                return null;
            }
        }
    }

    public static class HashHandler {
        static final char[] byteToStr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
                'd', 'e', 'f'};

        public HashHandler() {
        }

        public static String getHashValue(String str) {
            return getHashValue(str, StringUtil.HashHandler.HashMethod.md5);
        }

        public static String getHashValue(byte[] str, StringUtil.HashHandler.HashMethod sh1) {
            MessageDigest md = sh1.method;
            md.reset();
            md.update(str);
            byte[] resultByte = md.digest();
            return encodeHex(resultByte);
        }

        public static String getHashValue(String str, StringUtil.HashHandler.HashMethod sh1) {
            MessageDigest md = sh1.method;
            md.reset();
            md.update(str.getBytes());
            byte[] resultByte = md.digest();
            return encodeHex(resultByte);
        }

        public static String encodeHex(byte[] in) {
            int n = in.length;
            int k = n * 2;
            int size = k >= 512 ? 512 : k;
            char[] re = new char[size];
            if (n > 0) {
                StringBuilder sb = new StringBuilder();
                int cur = 0;

                for (int i = 0; i < n; ++i) {
                    int hc = in[i] >> 4 & 15;
                    int lc = in[i] & 15;
                    re[cur] = byteToStr[hc];
                    re[cur + 1] = byteToStr[lc];
                    if (cur >= size) {
                        sb.append(re, 0, cur);
                        cur = 0;
                    } else {
                        cur += 2;
                    }
                }

                if (cur > 0) {
                    sb.append(re, 0, cur);
                }

                return sb.toString();
            } else {
                return "";
            }
        }

        public static String getFileHashCode(String loc) throws FileNotFoundException, IOException {
            return getFileHashCode(loc, StringUtil.HashHandler.HashMethod.md5);
        }

        public static String getFileHashCode(String loc, StringUtil.HashHandler.HashMethod sh1) throws FileNotFoundException, IOException {
            MessageDigest md = sh1.method;
            FileInputStream fis = new FileInputStream(loc);

            try {
                byte[] dataBytes = new byte[1024];
                boolean var5 = false;

                int nread;
                while ((nread = fis.read(dataBytes)) != -1) {
                    md.update(dataBytes, 0, nread);
                }

                byte[] mdbytes = md.digest();
                String var7 = encodeHex(mdbytes);
                return var7;
            } finally {
                if (fis != null) {
                    fis.close();
                }

            }
        }

        public static enum HashMethod {
            sha1("SHA1"),
            sha512("SHA-256"),
            md5("MD5");

            final MessageDigest method;

            private HashMethod(String mn) {
                MessageDigest m = null;

                try {
                    m = MessageDigest.getInstance(mn);
                } catch (NoSuchAlgorithmException var9) {
                    Logger.getLogger(StringUtil.HashHandler.class.getName()).log(Level.SEVERE, (String) null, var9);
                } finally {
                    this.method = m;
                }

            }
        }
    }

    public static String genStr(String prefix, String target, Map<String, Object> param) {
        StringBuilder ret = new StringBuilder(prefix);
        Matcher m = pattern.matcher(target);
        int start = 0;
        if (param != null) {
            while (m.find(start)) {
                String k = m.group(1);
                if (!param.containsKey(k)) {
                    throw new IllegalArgumentException();
                }

                String kk = param.get(k).toString();
                ret.append(target.subSequence(start, m.start()));
                ret.append(kk);
                start = m.end();
            }
        }

        if (start < target.length()) {
            ret.append(target.substring(start));
        }

        return ret.toString();
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

}

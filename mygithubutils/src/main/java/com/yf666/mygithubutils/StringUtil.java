package com.yf666.mygithubutils;

import android.content.ContentValues;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串辅助类 <br>
 *
 * @author zhuguipeng
 */
public class StringUtil {

    public static char split = 0x01;// 分隔符

    public static char feed = 0x0A;// 换行

    public static String KEY = "zgpg";

    /**
     * 判断字符串是否为 null/空/无内容
     *
     * @param str
     * @return
     * @author wwy
     */
    public static boolean isBlank(String str) {
        if (null == str)
            return true;
        if ("".equals(str.trim()))
            return true;
        if (str.equals("null"))
            return true;
        return false;
    }

    public static String str(String s) {
        if (s == null || s.length() <= 0) {
            return "";
        } else {
            return s;
        }

    }

    /**
     * 字符串相等 null和空字符串认为相等，忽略字符串前后空格
     *
     * @param str1
     * @param str2
     * @return
     * @author wwy
     */
    public static boolean compareString(String str1, String str2) {
        if (null == str1) {
            str1 = "";
        }
        if (null == str2) {
            str2 = "";
        }
        if (str1.trim().equals(str2.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 将对象转成String
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString().trim();
    }

    public static String encodePassword(String password, String algorithm) {
        if (algorithm == null)
            return password;
        byte unencodedPassword[] = password.getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return password;
        }
        md.reset();
        md.update(unencodedPassword);
        byte encodedPassword[] = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 16)
                buf.append("0");
            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String getEncryptPassword(String password) {
        try {
            // return Des.encrypt(password, KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public static String getEncryptPasswordMD5(String password) {
        return encodePassword(password, "MD5");
    }

    /**
     * 获取json节点值
     *
     * @param jsonObject
     * @param jsonNode
     * @return
     */
    public static String getJSONObject(JSONObject jsonObject, String jsonNode) {
        try {

            if (jsonObject.has(jsonNode))
                return jsonObject.get(jsonNode).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JSONObject getJSONNode(JSONObject jsonObject, String jsonNode) {
        try {
            if (jsonObject.has(jsonNode))
                return jsonObject.getJSONObject(jsonNode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 像数据库插入字段
     */
    public static ContentValues pubValues(ContentValues values, String cloumn,
                                          String str_value) {
        if (str_value != null) {
            values.put(cloumn, str_value);
        }
        return values;
    }

    /**
     * 字符串转整数
     *
     * @param l_ser
     * @return
     */

    public static int strToInt(String l_ser) {
        int covs = 0;
        try {
            covs = new Integer(l_ser);
        } catch (Exception e) {
        }
        return covs;
    }

    /**
     * 字符串转double
     *
     * @param gis
     * @return
     */

    public static double strToDouble(String gis) {
        double covs = 0d;
        try {
            covs = new Double(gis).doubleValue();
        } catch (Exception e) {
        }
        return covs;
    }

    /**
     * 字符串转long
     *
     * @param time
     * @return
     */

    public static long strToLong(String time) {
        long covs = 0l;
        try {
            covs = new Long(time).longValue();
        } catch (Exception e) {
        }
        return covs;
    }

    /**
     * 验证手机电话号码
     * <p/>
     * 手机号码 移动：134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
     * 联通：130,131,132,152,155,156,185,186 电信：133,1349,153,180,189
     *
     * @param mobilephone
     * @return
     */
    public static boolean checkMobilephone(String mobilephone) {
        String cm = "^1(34[0-8]|(3[5-9]|5[017-9]|8[278]|7[0-9])\\d)\\d{7}$";// 中国移动正则
        String cu = "^1(3[0-2]|5[256]|8[56])\\d{8}$";// 中国联通正则
        String ct = "^1((33|53|8[09])[0-9]|349)\\d{7}$";// 中国电信正则
        if (Pattern.matches(cm, mobilephone)
                || Pattern.matches(cu, mobilephone)
                || Pattern.matches(ct, mobilephone)) {
            return true;
        }
        return false;
    }

    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 * 新手机号 14 17开头的
		 */
        String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    /**
     * 验证密码 6-20位，至少含数字/字母/字符2种组合
     * 特殊字符
     * _~!@#$%
     *
     * @param text
     * @return
     */
    public static boolean isPassWorld(String text) {
        Pattern pattern = Pattern.compile("(?!^(\\d+|[a-zA-Z]+|[_~!@#$%^&*?]+)$)^[\\w_~!@#$%\\^&*?]{6,20}$");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
        // System.out.println(matcher.matches());
    }

    /**
     * 身份证号
     *
     * @param text
     * @return
     */
    public static boolean isIDcard(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /**
     * 返回原型图
     *
     * @param thumbnial
     * @return
     */
    public static String convertPrototype(String thumbnial) {
        try {
            if (null == thumbnial)
                return "";
            return (new StringBuilder())
                    .append(thumbnial.substring(0, thumbnial.lastIndexOf('.')))
                    .append("_prototype")
                    .append(thumbnial.substring(thumbnial.lastIndexOf('.')))
                    .toString();
        } catch (Exception e) {
            return thumbnial;
        }
    }

    /**
     * 将多个对象进行组装<br>
     *
     * @param parts
     * @return
     * @author wwy
     */
    // public static String assemblingString(Object... parts) {
    // StringBuilder builder = new StringBuilder();
    // if (CollectionUtil.isEmpty(parts)) {
    // return null;
    // }
    // for (Object part : parts) {
    // if(part == null){
    // part = Constant.EMPTY_STRING;
    // }
    // builder.append(part);
    // }
    // return builder.toString();
    // }

    /**
     * 转化时间字符转 类型：\/Date(1395396358000)\/
     */
    public static String date(String date) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？Date]";
        try {

            if (null == date || date.equals("")) {
                return "";
            } else {

                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(date);
                System.out.println(m.replaceAll(""));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String sd = sdf.format(new Date(Long.parseLong(m.replaceAll("")
                        .trim())));
                return sd;

            }

        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 是否包含特殊字符
     */
    public static boolean containsAny(String str) {

        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        // System.out.println("++++++++++++++++++++++++++++++++"+str.contains(regEx));
        if (str != null) {
            Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(str);
            return m.find();
        } else {
            return false;
        }

    }

    public static boolean isCardId(String str) {
        if (str != null && !str.equals("")) {
            Pattern idNumPattern = Pattern
                    .compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
            Matcher idNumMatcher = idNumPattern.matcher(str);
            return idNumMatcher.matches();
        } else {
            return false;
        }

    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 城市 北京市-->北京
     *
     * @param city
     * @return
     */
    public static String fromatCity(String city) {
        if (TextUtils.isEmpty(city)) {
            return "";
        }
        if (city.endsWith("市")) {
            int index = city.indexOf("市");
            return city.substring(0, index);
        }
        return city;
    }

    /**
     * 多余4个字的 转换为 哈哈哈啊啊啊啊啊  --->  哈哈哈...
     *
     * @param special
     * @return
     */
    public static String formatSpecial(String special) {
        if (!TextUtils.isEmpty(special) && special.length() > 4) {
            String temp = special.substring(0, 3);

            return temp + "...";
        } else {
            return special;
        }
    }

    /**
     * 将 abcd 转为 [a,b,c,d] 集合
     *
     * @param str
     * @return
     */
    public static List<String> str2list(String str) {
        List<String> list = new ArrayList<>();

        if (TextUtils.isEmpty(str)) {
            return list;
        } else {
//            String[] temp = str.split("");
            char[] chars = str.toCharArray();
            for (char c : chars) {
                list.add(c + "");
            }
//            list = Arrays.asList(temp);
            return list;
        }

    }

    /**
     * 将 abcd 转为 [a,b,c,d] 集合
     *
     * @param str
     * @param sub 以sub分割
     * @return
     */
    public static List<String> str2list(String str, String sub) {
        List<String> list = new ArrayList<>();

        if (TextUtils.isEmpty(str)) {
            return list;
        } else {
            String[] temp = str.split(sub);
            list = Arrays.asList(temp);
            return list;
        }

    }

    /**
     * 升序排列
     * cab--->abc
     *
     * @param str
     * @return
     */
    public static String sortWordUp(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        String[] str_temp = str.split("");
        Arrays.sort(str_temp);
        String result = "";
        for (int i = 0; i < str_temp.length; i++) {
            result += str_temp[i];
        }

        return result;
    }

    /**
     * 把集合转为字符串
     *
     * @param list
     * @return
     */
    public static String list2Str(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        String s = "";
        for (String string : list) {
            s += string;
        }
        return sortWordUp(s);
    }


    /**
     * 从地址里面取 看包含关系
     * 城市 北京市-->北京
     * 添加县级市  俩字的 滑县---->滑县
     * 巩义市、汝州市、邓州市、永城市、兰考县、滑县、长垣县、固始县、鹿邑县、新蔡县
     * 巩义、汝州、邓州、永城、兰考、滑县、长垣、固始、鹿邑、新蔡
     *
     * @param address 地区
     * @param city    城市
     * @return
     */
    public static String fromatCityLocation(String address, String city) {
        if (TextUtils.isEmpty(address) || TextUtils.isEmpty(city)) {
            return "";
        }
        if (address.contains("巩义市")) {
            return "巩义";
        }
        if (address.contains("汝州市")) {
            return "汝州";
        }
        if (address.contains("邓州市")) {
            return "邓州";
        }
        if (address.contains("永城市")) {
            return "永城";
        }
        if (address.contains("兰考县")) {
            return "兰考";
        }
        if (address.contains("滑县")) {
            return "滑县";
        }
        if (address.contains("长垣县")) {
            return "长垣";
        }
        if (address.contains("固始县")) {
            return "固始";
        }
        if (address.contains("鹿邑县")) {
            return "鹿邑";
        }
        if (address.contains("新蔡县")) {
            return "新蔡";
        }

        if (city.endsWith("市")) {
            int index = city.indexOf("市");
            return city.substring(0, index);
        }
        return city;
    }

    /**
     * 判断一个字符串是不是网址
     * @param url 地址
     * @return
     */
    public static boolean isWebSite(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        if (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("ftp://")) {
            return true;
        }
        return false;
    }

    /**
     * 包含某个字符串 改为某个字符串
     * @param origin
     * @param containsStr
     * @return
     */
    public static String containsStrchangeToStr(String origin, String containsStr){
        if (!TextUtils.isEmpty(origin) && origin.contains(containsStr)) {
            origin = containsStr;
        }
        return origin;
    }

    public static void main(String[] args) {
        System.out.println(isPassWorld("132456"));
    }

}

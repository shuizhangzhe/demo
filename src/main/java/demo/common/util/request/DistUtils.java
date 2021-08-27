package demo.common.util.request;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import demo.common.enumeration.StatusCodeEnum;
import demo.common.except.CsscException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class DistUtils {

    /**
     * 将对象或集合转换为JsonString
     * @author qzq
     * @date 2020.5.15
     * @param orc 对象或集合
     * @return string
     */
    public static String orc2Str(Object orc){
        return StringEscapeUtils.unescapeHtml4(JSONObject.toJSON(orc).toString());
    }

    public static String orc2Str(Collection<?> orc){
        return JSONObject.toJSON(orc).toString();
    }

    /**
     * @author qzq
     * @date 2020.5.13
     * @param data 数据传入
     * @return 打包code、msg、数据后转换为JSONString返回
     */
    public static String successAndRenderData(Object data){
        Map<String,Object> res = new HashMap<>();
        putCode2ResMap(res, StatusCodeEnum.SUCCESS);
        res.put("data",data);
        return orc2Str(res);
    }

    public static String successAndRenderData(Collection<?> data){
        Map<String,Object> res = new HashMap<>();
        putCode2ResMap(res,StatusCodeEnum.SUCCESS);
        res.put("data",data);

        return orc2Str(res);
    }

    public static String successAndRender(){
        Map<String,Object> res = new HashMap<>();
        putCode2ResMap(res,StatusCodeEnum.SUCCESS);
        return orc2Str(res);
    }

    /**
     * 打包状态码至map
     * @author qzq
     * @date 2020-4-22
     * @param map map对象
     * @param sc 状态码枚举常量
     * @param e 异常对象（可为空）
     * @return map对象
     */
    public static Map<String,Object> putCode2ResMap(Map<String,Object> map, StatusCodeEnum sc, Exception e){
        map.put("code",sc.getCode());
        map.put("msg",sc.getMsg());
//        if(e != null){
//            map.put("stack",getErrorInfoFromException(e));
//        }
        return map;
    }

    public static Map<String,Object> putCode2ResMap(Map<String,Object> map, StatusCodeEnum sc){
        return putCode2ResMap(map,sc,null);
    }

    public static Map<String,Object> putCode2ResMap(StatusCodeEnum sc, Exception e){
        Map<String,Object> res = new HashMap<>();
        return putCode2ResMap(res,sc,e);
    }

    public static Map<String,Object> putCode2ResMap(StatusCodeEnum sc){
        return putCode2ResMap(sc,null);
    }

    public static Map<String,Object> putCode2ResMap(int code,String msg,Exception e){
        Map<String,Object> res = new HashMap<>();
        res.put("code",code);
        res.put("msg",msg);

//        if(e != null){
//            res.put("stack",getErrorInfoFromException(e));
//        }
        return res;
    }

    public static Map<String,Object> putCode2ResMap(CsscException zfe){
        return putCode2ResMap(zfe.getCode(),zfe.getMsg(),zfe);
    }

    public static String putCode2JsonStr(Map<String,Object> map, StatusCodeEnum sc){
        return orc2Str(putCode2ResMap(map,sc));
    }

    public static String putCode2JsonStr(StatusCodeEnum sc, Exception e){
        return orc2Str(putCode2ResMap(sc,e));
    }

    public static String putCode2JsonStr(StatusCodeEnum sc){
        return orc2Str(putCode2ResMap(sc));
    }

    public static String putCode2JsonStr(int code,String msg,Exception e){
        return orc2Str(putCode2ResMap(code,msg,e));
    }

    public static String putCode2JsonStr(int code,String msg){
        return orc2Str(putCode2ResMap(code,msg,null));
    }

    public static String putCode2JsonStr(CsscException zfe){
        return orc2Str(putCode2ResMap(zfe));
    }

    public static String putJsonStr(Map<String,Object>map){
        return orc2Str(map);
    }


    /**
     * 随机字符串生成器
     * @author qzq
     * @date 2020-4-27
     * @param length 字符串长度
     * @return 随机生成的字符串
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}

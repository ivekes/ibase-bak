package com.wzk.proxy.dynamicproxy.wzkproxy;

import java.lang.reflect.Method;
import java.sql.ParameterMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成代理对象
 */
public class WZKProxy {

    private static final String BREAK_LINE = "\r\n";


    public static Object newProxyInstance(WZKClassLoader wzkClassLoader, Class<?>[] interfaces, WZKInvocationHandler h) {
        //1.动态生成源代码 .java 文件
        generateSrc(interfaces);
        //2.java 文件输出到磁盘
        //3.把生成的 .java 文件编译成 .class 文件
        //4、编译生成的 .class 文件加载到 jvm 中来
        //5、返回字节码重组以后的代理对象

        return null;
    }

    /**
     * 此方法目的拼接一个代理类字符串并输出到磁盘上
     * @param interfaces
     * @return
     */
    private static void generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.wzk.proxy.dynamicproxy.wzkproxy;" + BREAK_LINE);
        sb.append("package com.wzk.proxy.common.Person;" + BREAK_LINE);
        sb.append("import java.lang.reflect.*;" + BREAK_LINE);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + BREAK_LINE);
        sb.append("WZKInvocationHandler h;" +BREAK_LINE);
        sb.append("public $Proxy0(WZKInvocationHandler h){" + BREAK_LINE);
        sb.append("this.h = h;"+ BREAK_LINE);
        sb.append("}"+ BREAK_LINE);

        for (Method m:interfaces[0].getMethods()){
            Class<?>[] param = m.getParameterTypes();

            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i=0;i<param.length;i++){
                Class clazz = param[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName + ",");
                paramValues.append(paramName + ",");
                paramClasses.append(clazz.getName() + ".class,");
            }

            if (paramNames.length() > 0){
                paramNames.substring(0,paramNames.length()-1);
            }
            if (paramValues.length() > 0){
                paramValues.substring(0,paramValues.length()-1);
            }
            if (paramClasses.length() > 0){
                paramClasses.substring(0,paramClasses.length()-1);
            }

            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() +"){" + BREAK_LINE);
            sb.append("try{" + BREAK_LINE);
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() +"\",new Class[]{" + paramClasses.toString() +"});" + BREAK_LINE);
//            sb.append(hasReturnValue(m.getReturnType())?"return " :"") + getCaseCode("this.h.invoke(this,m,new Object[]{)"+paramValues+"})",m.getReturnType());
            sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + BREAK_LINE);
            sb.append("}catch(Throwable e){" + BREAK_LINE);
            sb.append("throw new UndeclaredThrowableException(e);" + BREAK_LINE);
            sb.append("}" + BREAK_LINE);
            sb.append(getReturnEmptyCode(m.getReturnType()));
            sb.append("}");
        }
        sb.append("}");

    }

    /**
     * 首字母变为小写
     * @param s
     * @return
     */
    private static String toLowerFirstCase(String s) {
        char[] chars = s.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }

    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }
    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }
}

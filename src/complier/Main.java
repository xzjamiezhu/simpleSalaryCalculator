package complier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Date: 13-2-15
 * Time: 下午1:40
 *
 * @author : zhuxm
 * @version : 1.x.0
 */
public class Main {

    public static void main(String[] args) {
        String clazzName = "Hello";
        String methodName = "hello";
        String clazzContent = "public class " + clazzName
                + " { public static void " + methodName + "() "
                + "{ System.out.println(\"hello world\");}}";

        RuntimeJavaSourceCompiling executeACompile = new RuntimeJavaSourceCompiling();
        RuntimeCompiledResult result = executeACompile.compileAJavaSource(clazzName, clazzContent);
        if (result.isSuccess()) {
            try {
                Class compiledClazz = result.getCompiledClazz();
                Method executingMethod = compiledClazz.getMethod("hello");
                executingMethod.invoke(compiledClazz);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        clazzContent = "public class " + clazzName
                + " { public static void " + methodName + "() "
                + "{ System.out.println(\"hello world two\");}}";
        result = executeACompile.compileAJavaSource(clazzName, clazzContent);
        if (result.isSuccess()) {
            try {
                Class compiledClazz = result.getCompiledClazz();
                Method executingMethod = compiledClazz.getMethod("hello");
                executingMethod.invoke(compiledClazz);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

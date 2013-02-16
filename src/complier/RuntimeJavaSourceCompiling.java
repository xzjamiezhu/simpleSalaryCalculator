package complier;

import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Java代码编译器
 * Date: 13-2-15
 * Time: 下午1:38
 *
 * @author : zhuxm
 * @version : 1.0.0
 */
public class RuntimeJavaSourceCompiling {

    /**
     * 动态编译java代码
     * @param clazzName 类名
     * @param clazzContent 类内容
     * @return 编译结果
     */
    public RuntimeCompiledResult compileAJavaSource(String clazzName, String clazzContent)
            throws RuntimeException{
        RuntimeJavaSourceObject javaSource = new RuntimeJavaSourceObject(clazzName, clazzContent);

        JavaCompiler aCompiler = ToolProvider.getSystemJavaCompiler();
        JavaCompiler.CompilationTask runtimeCpiTask = aCompiler.getTask(null,
                aCompiler.getStandardFileManager(null, null, Charset.forName("utf-8")),
                null,
                Arrays.asList("-d", Class.class.getClass().getResource("/").getPath()),
                null,
                Arrays.asList(javaSource));

        // prepare result
        RuntimeCompiledResult result = new RuntimeCompiledResult();
        result.setSuccess(runtimeCpiTask.call());
        if (result.isSuccess()) {
            try {
                result.setCompiledClazz(this.getClass().getClassLoader().loadClass(clazzName));
            } catch (ClassNotFoundException e) {
                result.setSuccess(false);
            }
        }

        return result;
    }

    /**
     * 运行时生成的Java源代码对象
     */
    static class RuntimeJavaSourceObject extends SimpleJavaFileObject {
        private String clazzContent = "";

        public RuntimeJavaSourceObject(String clazzName, String clazzContent) {
            super(createClazzURI(clazzName), Kind.SOURCE);
            this.clazzContent = clazzContent;
        }

        private static URI createClazzURI(String clazzName) {
            return URI.create("string:///" + clazzName.replace('.', '/') +
                    Kind.SOURCE.extension);
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return clazzContent;
        }
    }
}

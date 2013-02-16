package complier;

/**
 * 编译结果
 * Date: 13-2-15
 * Time: 下午2:51
 *
 * @author : zhuxm
 * @version : 1.0.0
 */
public class RuntimeCompiledResult {
    private boolean success;
    private Class compiledClazz;

    public RuntimeCompiledResult() {
    }

    public RuntimeCompiledResult(boolean success, Class compiledClazz) {
        this.success = success;
        this.compiledClazz = compiledClazz;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Class getCompiledClazz() {
        return compiledClazz;
    }

    public void setCompiledClazz(Class compiledClazz) {
        this.compiledClazz = compiledClazz;
    }
}

package calculator;

/**
 * 计算公式接口
 * Date: 13-2-15
 * Time: 下午2:56
 *
 * @author : zhuxm
 * @version : 1.0.0
 */
public interface INumberFormula {
    /**
     * 公式是否已准备好计算
     * @return 是否已准备好
     */
    boolean isReady();

    /**
     * 公式Key
     * @return 公式Key
     */
    String formulaKey();

    /**
     * 公式名称
     * @return 公式名称
     */
    String formulaName();

    /**
     * 原始公式内容
     * @return 公式内容
     */
    String originFormulaContent();

    /**
     * 准备好后的公式内容
     * @return 公式内容
     */
    String readyFormulaContent();

    /**
     * 添加用于计算的参数
     * @param key 参数Key
     * @param value 参数值
     */
    void applyVariables(String key, Double value);
}

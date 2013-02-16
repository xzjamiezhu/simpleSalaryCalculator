package calculator;

import jregex.Matcher;
import jregex.Pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数值计算公式
 * Date: 13-2-15
 * Time: 下午3:29
 *
 * @author : zhuxm
 * @version : 1.0.0
 */
public class NumberCalcFormula implements INumberFormula {
    private static String VARIABLE_KEY_STARTER = "@";

    Map<String, Double> variables = new HashMap<String, Double>();

    private String formulaKey = "";
    private String formulaName = "";
    private String originFormulaContent = "";

    public NumberCalcFormula(String formulaKey, String formulaName, String originFormulaContent) {
        this.formulaKey = formulaKey;
        this.formulaName = formulaName;
        this.originFormulaContent = originFormulaContent;
    }

    @Override
    public boolean isReady() {
        for (String variableKey : getRequiredVariables()) {
            if (!variables.containsKey(variableKey)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String formulaKey() {
        return formulaKey;
    }

    public String formulaName() {
        return formulaName;
    }

    @Override
    public String originFormulaContent() {
        return originFormulaContent;
    }

    @Override
    public String readyFormulaContent() {
        String readyContent = originFormulaContent();
        for (String variableKey : getRequiredVariables()) {
            readyContent = readyContent.replaceAll(VARIABLE_KEY_STARTER + variableKey, String.valueOf(variables.get(variableKey)));
        }

        return readyContent;
    }

    @Override
    public void applyVariables(String key, Double value) {
        variables.put(key, value);
    }

    private List<String> getRequiredVariables() {
        if (originFormulaContent() != null && !originFormulaContent().contains(VARIABLE_KEY_STARTER)) {
            return new ArrayList<String>();
        }

        // 正则查找@xxx格式
        Pattern requirePattern = new Pattern(".*({require}@\\w+).*");
        List<String> requireVariableKeys = new ArrayList<String>();
        String formulaContent = this.originFormulaContent();

        while (requirePattern.matches(formulaContent)) {
            Matcher matcher = requirePattern.matcher(formulaContent);
            if (matcher.matches()) {
                String key = matcher.group("require");
                if (key != null
                        && !key.equals(VARIABLE_KEY_STARTER)
                        && key.startsWith(VARIABLE_KEY_STARTER)) {
                    requireVariableKeys.add(key.substring(1));
                    formulaContent = formulaContent.substring(0, formulaContent.lastIndexOf(VARIABLE_KEY_STARTER));
                }
            }
        }

        return requireVariableKeys;
    }
}

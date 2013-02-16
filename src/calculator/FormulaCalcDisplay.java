package calculator;

/**
 * Date: 13-2-15
 * Time: 下午3:52
 *
 * @author : zhuxm
 * @version : 1.0.0
 */
public class FormulaCalcDisplay {

    private String formulaKey;
    private String formulaName;
    private String formulaContent;
    private String formulaResult;

    public FormulaCalcDisplay(String formulaKey, String formulaName, String formulaContent, String formulaResult) {
        this.formulaKey = formulaKey;
        this.formulaName = formulaName;
        this.formulaContent = formulaContent;
        this.formulaResult = formulaResult;
    }

    public String getFormulaKey() {
        return formulaKey;
    }

    public void setFormulaKey(String formulaKey) {
        this.formulaKey = formulaKey;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormulaContent() {
        return formulaContent;
    }

    public void setFormulaContent(String formulaContent) {
        this.formulaContent = formulaContent;
    }

    public String getFormulaResult() {
        return formulaResult;
    }

    public void setFormulaResult(String formulaResult) {
        this.formulaResult = formulaResult;
    }
}

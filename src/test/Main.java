package test;

import calculator.FormulaCalculator;
import calculator.INumberFormula;
import calculator.NumberCalcFormula;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-2-15
 * Time: 下午3:50
 *
 * @author : zhuxm
 * @version : 1.x.0
 */
public class Main {

    public static void main(String[] args) {
        FormulaCalculator calculator = new FormulaCalculator();
        List<INumberFormula> formulas = new ArrayList<INumberFormula>();
        formulas.add(new NumberCalcFormula("basicSalary", "基本工资", "(@level<2?1000:1500)"));
        formulas.add(new NumberCalcFormula("level", "等级", "3"));
        formulas.add(new NumberCalcFormula("isFullWork", "是否全勤", "1"));
        formulas.add(new NumberCalcFormula("fullWorkAllowance", "全勤补贴系数", "@isFullWork==1?1.1:1.0"));
        formulas.add(new NumberCalcFormula("bonus", "奖金", "500"));
        formulas.add(new NumberCalcFormula("tax", "个人所得税", "@basicSalary*0.2+@bonus*0.15"));
        formulas.add(new NumberCalcFormula("result", "税后所得", "@basicSalary*@fullWorkAllowance+@bonus-@tax"));
        calculator.calculate(formulas);
    }
}

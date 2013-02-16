package calculator;

import complier.RuntimeCompiledResult;
import complier.RuntimeJavaSourceCompiling;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 公式计算器
 * Date: 13-2-15
 * Time: 下午3:00
 *
 * @author : zhuxm
 * @version : 1.0.0
 */
public class FormulaCalculator {

    public void calculate(List<INumberFormula> formulas) {
        FormulaCollection collection = FormulaCollection.initInstance(formulas);
        INumberFormula beingCalcFormula = null;
        while ((beingCalcFormula = collection.getNextReadyFormula()) != null) {
            String clazzName = ("Calc" + UUID.randomUUID()).replaceAll("-", "");
            String methodName = "calc";
            String clazzContent =
                    "public class " + clazzName + " {"
                            + " public static double " + methodName + "() {"
                            + " return + " + beingCalcFormula.readyFormulaContent() + ";"
                            + "}" +
                            "}";

            RuntimeJavaSourceCompiling executeACompile = new RuntimeJavaSourceCompiling();
            RuntimeCompiledResult result = executeACompile.compileAJavaSource(clazzName, clazzContent);
            if (result.isSuccess()) {
                Class compiledClazz = result.getCompiledClazz();
                try {
                    Method executingMethod = compiledClazz.getMethod("calc");
                    Double d = (Double) executingMethod.invoke(compiledClazz);
                    collection.setFormulaCalculated(beingCalcFormula, d);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("------ calc completed ------");
        collection.display();
    }

    static class FormulaCollection {
        List<INumberFormula> unCalculatedFormulas = new ArrayList<INumberFormula>();
        List<INumberFormula> calculatedFormulas = new ArrayList<INumberFormula>();
        List<FormulaCalcDisplay> calcDisplays = new ArrayList<FormulaCalcDisplay>();

        static FormulaCollection initInstance(List<INumberFormula> formulas) {
            FormulaCollection c = new FormulaCollection();
            c.unCalculatedFormulas.addAll(formulas);
            return c;
        }

        void applyFormulaResult(String key, Double value) {
            for (INumberFormula anUnCalcFormula : unCalculatedFormulas) {
                anUnCalcFormula.applyVariables(key, value);
            }
        }

        INumberFormula getNextReadyFormula() {
            for (INumberFormula anUnCalcFormula : unCalculatedFormulas) {
                if (anUnCalcFormula.isReady()) {
                    return anUnCalcFormula;
                }
            }

            return null;
        }

        void setFormulaCalculated(INumberFormula calcFormula, Double result) {
            unCalculatedFormulas.remove(calcFormula);
            calculatedFormulas.add(calcFormula);
            applyFormulaResult(calcFormula.formulaKey(), result);
            calcDisplays.add(new FormulaCalcDisplay(calcFormula.formulaKey(),
                    calcFormula.formulaName(),
                    calcFormula.originFormulaContent(),
                    String.valueOf(result)));
        }

        void display() {
            for (FormulaCalcDisplay display : calcDisplays) {
                System.out.println("------------------------------");
                System.out.println(String.format("key[%s], name[%s], formula[%s]",
                        display.getFormulaKey(),
                        display.getFormulaName(),
                        display.getFormulaContent()));
                System.out.println(String.format("result[%s]", display.getFormulaResult()));
            }
        }
    }
}

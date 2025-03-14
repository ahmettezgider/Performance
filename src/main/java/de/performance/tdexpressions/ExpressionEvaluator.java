package de.performance.tdexpressions;

import groovy.lang.GroovyRuntimeException;
import groovy.util.Eval;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class ExpressionEvaluator {

    private final static Logger log = Logger.getLogger(ExpressionEvaluator.class.getName());

    public static String evaluate(String s) throws InvalidExpressionException {
        if (s == null)
            return null;
        while (s.contains("{") && s.contains("}")) {
            int p1 = s.indexOf("{");
            int p2 = s.indexOf("}");
            String s1 = s.substring(0, p1);
            String se = s.substring(p1 + 1, p2);
            String s2 = s.length() - 1 > p2 ? s.substring(p2 + 1) : "";
            s = s1 + evaluateSingleExpression(se) + s2;
        }
        return s;
    }

    private static String evaluateSingleExpression(String s) throws InvalidExpressionException {
        if (s == null)
            return null;
        try {
            String code = "import de.vvc.ikaros40.tdexpressions.ExpressionEvaluator\n" +
                    "import static de.vvc.ikaros40.tdexpressions.DateTime.today as today\n" +
                    "import static de.vvc.ikaros40.tdexpressions.DateTime.future as future\n" +
                    "import static de.vvc.ikaros40.tdexpressions.DateTime.futureWorkDay as futureWorkDay\n" +
                    "import static de.vvc.ikaros40.tdexpressions.DateTime.past as past\n" +
                    "import static de.vvc.ikaros40.tdexpressions.DateTime.now as now\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Properties.getProperty as get\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Properties.setProperty as set\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Properties.isPropertySet as isset\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Id.getUUID as uuid\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Id.getUniqueId as id\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Control.getValue as getvalue\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Characters.TAB as TAB\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Characters.ENTER as ENTER\n" +
                    "import static de.vvc.ikaros40.tdexpressions.Characters.SPACE as SPACE\n" +
                    "import static de.vvc.ikaros40.tdexpressions.DateTime.getTimestamp as getTimestamp\n" +
                    "def val() { return getvalue(ctrl) }\n" +
                    s;
            code = code.replaceAll("''", "\"");
            Object o = Eval.me("ctrl", null, code);
            o = o == null ? "" : o;
            return o.toString();
        } catch (GroovyRuntimeException re) {
            throw new InvalidExpressionException(MessageFormat.format("Invalid Expression: [{0}]", s));
        }
    }

}

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExpressionSolverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int[] numbers = new int[4];
        String[] operators = {"+", "-", "*", "/"};

        for (int i = 0; i < 4; i++) {
            numbers[i] = Integer.parseInt(request.getParameter("number" + (i + 1)));
        }

        String result = "与えられた数字を使って10を作ることができません。";

        outer:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i) continue;
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j) continue;
                    for (int l = 0; l < 4; l++) {
                        if (l == i || l == j || l == k) continue;

                        for (String op1 : operators) {
                            for (String op2 : operators) {
                                for (String op3 : operators) {
                                    String expression = numbers[i] + op1 + numbers[j] + op2 + numbers[k] + op3 + numbers[l];
                                    double evalResult = evalExpression(expression);
                                    if (Math.abs(evalResult - 10) < 1e-6) {
                                        result = "計算式: " + expression + " = 10";
                                        break outer;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }

    private static double evalExpression(String expression) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (double) engine.eval(expression);
        } catch (ScriptException e) {
            return Double.NaN;
        }
    }
}

<!DOCTYPE html>
<html>
<head>
    <title>Expression Solver</title>
</head>
<body>
    <h1>4つの整数を四則演算して10を作る計算式</h1>
    <form action="ExpressionSolverServlet" method="post">
        <p>4つの整数（0から9の範囲）を入力してください:</p>
        <input type="number" name="number1" min="0" max="9" required>
        <input type="number" name="number2" min="0" max="9" required>
        <input type="number" name="number3" min="0" max="9" required>
        <input type="number" name="number4" min="0" max="9" required>
        <br><br>
        <input type="submit" value="計算式を見つける">
    </form>
</body>
</html>

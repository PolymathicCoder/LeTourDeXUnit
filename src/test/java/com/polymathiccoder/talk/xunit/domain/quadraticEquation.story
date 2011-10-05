Meta:
@author Abdelmonaim Remani

Scenario: Solving a quadratic equations, whose coefficients yield a positive discriminant; therefore, it has two real solutions

Given the quadratic equation ax^2 + bx + c = 0, such that a = 1, b = 5, and c = 6
When the equation is solved
Then it yields the following real solutions: -2,-3

Scenario: Solving a quadratic equations, whose coefficients yield a negative discriminant; therefore, it has no real solution

Given the quadratic equation 5x^2 + 2x + 1 = 0
When the equation is solved
Then it yields no real solution

Scenario: Solving a quadratic equations, whose coefficients yield a positive discriminant; therefore, it has one real solution

Given the quadratic equation ax^2 + bx + c = 0, such that a = 9, b = 12, and c = 4
When the equation is solved
Then it yields the following real solutions: -0.67
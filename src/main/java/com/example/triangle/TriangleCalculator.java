package com.example.triangle;

public class TriangleCalculator {
    private double a, b, c;

    //конструктор
    public TriangleCalculator(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    //Рассчитать площадь треугольника
    public double getArea() throws SideException {
        double area = 0;

        if ((a >=1) && (a<100) &&(b>=1) && (b<100) && (c>=1) && (c < 100)) {
            double p = (a +b +c) / 2; //Полупериметр
            area = Math.sqrt(p * (p - a) * (p -b) * (p - c));//Формула Герона
        } else {
            throw new SideException("Стороны должны быть от до 100. ");
        }
        //Округлим до сотых
        return Math.round(area * 100) / 100.0;
    }
    //Расчитаем периметр треугольника
    public double getPerimetr() {
        double p = (a + b + c); //Периметр
        return p;
    }
    //Рассчитаем тип треугольника
    public String calculate() {
        String triangleType;
        //Проверка на доступные значения
        if(a < 1 || a > 100 || b < 1 || b > 100 || c < 1 || c > 100) {
            return " Недопустимые значения сторон. Стороны должны быть от 1 до 100";
        }
        //Проверка на существование треугольника
        if (a + b <= c || a + c <= b || b + c <= a){
            return " треугольник с такими сторонами не существует. ";
        }
       //Определение типа треугольника
        if (a == b && b == c) {
            triangleType = " равносторонний треугольник";
        } else if (a == b || a == c || b == c) {
            triangleType = " равнобедренный треугольник. ";
        } else {triangleType = " разносторонний треугольник";}

        // Проверка на прямоугольный треугольник теорема Пифагора
        if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
            triangleType += " прямоугольный треугольник";
        }
        //Проверка на остроугольный и тупоугольный треугольники
        if (Math.pow(a, 2) + Math.pow(b, 2) <Math.pow(c, 2)) {
            triangleType += " тупоугольный треугольник. ";
        }
        //Возвращаем результат - тип треугольник
        return String.format("%s", triangleType);
    }
}

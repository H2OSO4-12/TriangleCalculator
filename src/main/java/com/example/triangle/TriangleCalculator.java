package com.example.triangle;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TriangleCalculator {
    private final double a;
    private final double b;
    private final double c;
    private static final double EPSILON = 1e-9;
    private static final double MIN_SIDE = 1.0;
    private static final double MAX_SIDE = 100.0;

    //конструктор
    public TriangleCalculator(double a, double b, double c) throws SideException {
        this.a = a;
        this.b = b;
        this.c = c;
        validateSides();
    }

    private boolean areEqual(double x, double y) {
        return Math.abs(x - y) < EPSILON;
    }

    public enum TriangleType {
        EQUILATERAL("равносторонний"),
        ISOSCELES("равнобедренный"),
        SCALENE("разносторонний"),
        RIGHT("прямоугольный"),
        ACUTE("остроугольный"),
        OBTUSE("тупоугольный");

        private final String name;

        TriangleType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    //Рассчитать площадь треугольника
    public double getArea() throws SideException {
        double area;

        if ((a >= MIN_SIDE) && (a < MAX_SIDE) && (b >= MIN_SIDE) && (b < MAX_SIDE) && (c >= MIN_SIDE) && (c < MAX_SIDE)) {
            double p = (a + b + c) / 2; //Полупериметр
            area = Math.sqrt(p * (p - a) * (p - b) * (p - c));//Формула Герона
        } else {
            throw new SideException("Стороны должны быть от " + MIN_SIDE + "до " + MAX_SIDE);
        }
        //Округлим до сотых
        return Math.round(area * MAX_SIDE) / 100.0;
    }

    //Расчитаем периметр треугольника
    public double getPerimetr() {
        //Периметр
        return (a + b + c);
    }

    //Рассчитаем тип треугольника
    private void validateSides() throws SideException {
        //Проверка на доступные значения
        if (a < MIN_SIDE || a > MAX_SIDE || b < MIN_SIDE || b > MAX_SIDE || c < MIN_SIDE || c > MAX_SIDE) {
            throw new SideException("Стороны должны быть от 1 до 100.");
        }
        //Проверка на существование треугольника
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new SideException("Треугольник с такими сторонами не существует.");
        }
    }

    //Определение типа треугольника
    public Set<TriangleType> getTriangleTypes() {
        Set<TriangleType> types = new LinkedHashSet<>();
        if (areEqual(a, b) && areEqual(b, c)) {
            types.add(TriangleType.EQUILATERAL);
        } else if (areEqual(a, b) || areEqual(a, c) || areEqual(b, c)) {
            types.add(TriangleType.ISOSCELES);
        } else {
            types.add(TriangleType.SCALENE);
        }
        double[] sides = {a, b, c};
        Arrays.sort(sides);
        double sumSquares = sides[0] * sides[0] + sides[1] * sides[1];
        double longestSquare = sides[2] * sides[2];
        // Проверка на прямоугольный треугольник теорема Пифагора
        //Проверка на остроугольный и тупоугольный треугольники
        if (areEqual(sumSquares, longestSquare)) {
            types.add(TriangleType.RIGHT);
        } else if (sumSquares > longestSquare) {
            types.add(TriangleType.ACUTE);
        } else {
            types.add(TriangleType.OBTUSE);
        }

        return types;
    }
}


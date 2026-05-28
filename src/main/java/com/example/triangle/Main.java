package com.example.triangle;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws SideException {
        TriangleCalculator t = new TriangleCalculator(1, 1, 1);
        String rezult = t.getTriangleTypes().toString();
        System.out.println("Тип треугольника " + rezult);
        System.out.println("Площадь треугольника " + t.getArea());
        System.out.println("Периметр треугольника " + t.getPerimetr());

    }
}
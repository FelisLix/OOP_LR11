package lr11.oop_lr11.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lr11.oop_lr11.data.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PointService {

    @Getter
    private List<Point> points;

    @PostConstruct
    public void init(){
        points = new ArrayList<>(List.of(new Point(0.0,0.0)));
    }

    public double function(double x) {
        double a = 1.65;
        if (x < 1.3 - 1e-4) {
            return Math.PI * x * x - 7 / (x * x);
        }
        if (x > 1.3 + 1e-4) {
            return Math.log(x + 7 * Math.sqrt(Math.abs(x + a)));
        }
        return a * x * x * x + 7 * Math.sqrt(x);
    }

    public int steps(double x1, double x2, double step) {
        double count = ((x2 - x1) / step) + 1;
        return (int) count;
    }

    public void createPointsArray(double x1, double x2, double step) {
        List<Point> points = new ArrayList<>();
        double[] arrayX = arrayXs(x1, x2, step);
        double[] arrayY = arrayYs(arrayX);
        for (int i = 0; i < arrayY.length; i++) {
            points.add(new Point(arrayX[i], arrayY[i]));
        }
        this.points = points;
    }

    public void show_smallest(){
        List<Point> pointsArray = new ArrayList<>();
        points.sort(Comparator.comparingDouble(Point::getY));
        pointsArray.add(new Point(points.get(0).getX(), points.get(0).getY()));
        points = pointsArray;
    }
    public void show_biggest(){
        List<Point> pointsArray = new ArrayList<>();
        points.sort(Comparator.comparingDouble(Point::getY));
        pointsArray.add(new Point(points.get(points.size()-1).getX(), points.get(points.size()-1).getY()));
        points = pointsArray;
    }

    public double[] arrayXs(double x1, double x2, double step) {
        double[] array = new double[steps(x1, x2, step)];
        for (int i = 0; i < array.length; i++) {
            array[i] = x1 + i * step;
        }
        return array;
    }

    public double[] arrayYs(double[] arrX) {
        double[] array = new double[arrX.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = function(arrX[i]);
        }
        return array;
    }

    public double findSum() {
        double[] array = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            array[i] = points.get(i).getY();
        }
        double sum = 0;
        for (double v : array) {
            sum += v;
        }
        return sum;
    }

    public double findArithmeticMean() {
        double[] array = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            array[i] = points.get(i).getY();
        }
        return findSum() / array.length;
    }




}

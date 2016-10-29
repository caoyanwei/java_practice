package com.kwk;

import smile.classification.SVM;
import smile.data.Attribute;
import smile.data.AttributeDataset;
import smile.data.Datum;
import smile.data.NominalAttribute;
import smile.data.parser.ArffParser;
import smile.data.parser.DelimitedTextParser;
import smile.math.kernel.GaussianKernel;
import smile.math.Math;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        test0();

        test1();
    }

    private static void test0() throws IOException, ParseException {
        ArffParser arffParser = new ArffParser();
        arffParser.setResponseIndex(4);
        AttributeDataset weather = arffParser.parse(new FileInputStream("e:/smile_data/weka/weather.nominal.arff"));
        double[][] x = weather.toArray(new double[weather.size()][]);
        int[] y = weather.toArray(new int[weather.size()]);
        System.out.println(weather.getName());
        System.out.println(weather.getDescription());
        for (Attribute attribute : weather.attributes()) {
            System.out.println(attribute);
        }

        for (Datum<double[]> datum : weather) {
            System.out.println(datum.x);
            System.out.println(datum.y);
        }
        System.out.println(weather.response());
    }

    private static void test1() {
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setResponseIndex(new NominalAttribute("class"), 0);
        try {
            AttributeDataset train = parser.parse("USPS Train", new FileInputStream("e:/smile_data/usps/zip.train"));
            AttributeDataset test = parser.parse("USPS Test", new FileInputStream("e:/smile_data/usps/zip.test"));

            double[][] x = train.toArray(new double[train.size()][]);
            int[] y = train.toArray(new int[train.size()]);
            double[][] testx = test.toArray(new double[test.size()][]);
            int[] testy = test.toArray(new int[test.size()]);

            SVM<double[]> svm = new SVM<double[]>(new GaussianKernel(8.0), 5.0, Math.max(y)+1, SVM.Multiclass.ONE_VS_ONE);
            svm.learn(x, y);
            svm.finish();

            int error = 0;
            for (int i = 0; i < testx.length; i++) {
                if (svm.predict(testx[i]) != testy[i]) {
                    error++;
                }
            }

            System.out.format("USPS error rate = %.2f%%\n", 100.0 * error / testx.length);

            System.out.println("USPS one more epoch...");
            for (int i = 0; i < x.length; i++) {
                int j = Math.randomInt(x.length);
                svm.learn(x[j], y[j]);
            }

            svm.finish();

            error = 0;
            for (int i = 0; i < testx.length; i++) {
                if (svm.predict(testx[i]) != testy[i]) {
                    error++;
                }
            }
            System.out.format("USPS error rate = %.2f%%\n", 100.0 * error / testx.length);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }


}

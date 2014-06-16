package org.pochemuto.termo.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.pochemuto.termo.problem.Problem;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * @author pochemuto
 */
public class MainController implements Initializable {

    public LineChart<Number, Number> chart;

    private Problem p = new Problem();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double[][] result = p.solve();


        for (double[] doubles : result) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<Number, Number>> data = series.getData();
            int n = 0;
            for (double v : doubles) {
                data.add(new XYChart.Data<>(n++, v));
            }

            chart.getData().add(series);
        }

    }
}

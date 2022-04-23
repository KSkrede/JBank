package jbank.data;

import java.util.ArrayList;

public interface IValuable {

    Double getValue(String ticker);
    ArrayList <String> getTickers();
    void update(String name, int value);
    void nextDay();
}

package jbank.data;

import java.util.ArrayList;

public interface IValuable {

    Integer getValue(String ticker);

    ArrayList<String> getTickers();

    void update(String name, int value);

    void nextDay();
}

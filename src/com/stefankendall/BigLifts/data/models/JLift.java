package com.stefankendall.BigLifts.data.models;

import java.math.BigDecimal;

public class JLift extends JModel implements Orderable {
    public String name;
    public BigDecimal weight;
    public int order;
    public BigDecimal increment;
    public boolean usesBar;

    @Override
    public int getOrder() {
        return order;
    }
}

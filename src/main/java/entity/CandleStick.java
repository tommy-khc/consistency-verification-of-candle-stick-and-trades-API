package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Objects;

//public/get-candleStick response
public class CandleStick {

    private final static Logger logger = LogManager.getLogger(CandleStick.class);

    private double o = Double.NaN;

    private double c;

    private double h = Double.MIN_VALUE;

    private double l = Double.MAX_VALUE;

    private double v = 0;

    private long t;

    private String name;

    public CandleStick(double o, double c, double h, double l, double v, long t) {
        this.o = o;
        this.c = c;
        this.h = h;
        this.l = l;
        this.v = v;
        this.t = t;
    }

    public CandleStick(double o, double c, double h, double l, double v, long t, String name) {
        this.o = o;
        this.c = c;
        this.h = h;
        this.l = l;
        this.v = v;
        this.t = t;
        this.name = name;
    }

    public CandleStick(String name) {
        this.name = name;
    }

    public double getO() {
        return o;
    }

    public void setO(double o) {
        this.o = o;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CandleStick{" +
                "o=" + o +
                ", c=" + c +
                ", h=" + h +
                ", l=" + l +
                ", v=" + v +
                ", t=" + t +
                ", name='" + name + '\'' +
                '}';
    }

    public String getTradeToString() {
        return "CandleStick{" +
                "o=" + o +
                ", c=" + c +
                ", h=" + h +
                ", l=" + l +
                ", v=" + v +
                ", lastTradeTime - t=" + t +
                ", name='" + name + '\'' +
                '}';
    }

    public static CandleStick getLatestElementFromGetCandleStick(JSONArray arr) {

        if (arr == null) {
            logger.error("fromGetCandlestick, arr == null");
            return null;
        }

        //last element is the latest candle stick
        JSONObject o = (JSONObject) arr.get(arr.size()-1);

        return new CandleStick(
                Double.parseDouble(o.get("o").toString()),
                Double.parseDouble(o.get("c").toString()),
                Double.parseDouble(o.get("h").toString()),
                Double.parseDouble(o.get("l").toString()),
                Double.parseDouble(o.get("v").toString()),
                Long.parseLong(o.get("t").toString()),
                "getCandlestick"
        );
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;
        CandleStick that = (CandleStick) o1;
        return Double.compare(that.o, o) == 0 && Double.compare(that.c, c) == 0 && Double.compare(that.h, h) == 0 && Double.compare(that.l, l) == 0 && Double.compare(that.v, v) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(o, c, h, l, v);
    }
}

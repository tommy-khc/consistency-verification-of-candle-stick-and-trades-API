package Entity;

//public/get-candleStick response
public class CandleStick {

    private double o = Double.NaN;

    private double c;

    private double h = Double.MIN_VALUE;

    private double l = Double.MAX_VALUE;

    private double v;

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

    public CandleStick() {
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
}

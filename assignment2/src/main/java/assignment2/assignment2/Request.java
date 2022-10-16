package assignment2.assignment2;

public class Request {
    private long id;
    private String fromAmount;
    private String toAmount;
    private String from;
    private String to;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }

    public String getFrom() {
        return from;
    }

    public void setDateFrom(String dateFrom) {
        this.from = dateFrom;
    }

    public String getTo() {
        return to;
    }

    public void setDateTo(String dateTo) {
        this.to = dateTo;
    }
}

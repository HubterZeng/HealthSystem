package entity;

public class ReadHistory {
    private Integer id;

    private Integer consId;

    private Integer docid;

    public ReadHistory(Integer id, Integer consId, Integer docid) {
        this.id = id;
        this.consId = consId;
        this.docid = docid;
    }

    public ReadHistory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsId() {
        return consId;
    }

    public void setConsId(Integer consId) {
        this.consId = consId;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }
}
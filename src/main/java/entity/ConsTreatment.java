package entity;

public class ConsTreatment {
    private Integer id;

    private Integer docid;

    private Integer consId;

    private String treatments;

    public ConsTreatment(Integer id, Integer docid, Integer consId, String treatments) {
        this.id = id;
        this.docid = docid;
        this.consId = consId;
        this.treatments = treatments;
    }

    public ConsTreatment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }

    public Integer getConsId() {
        return consId;
    }

    public void setConsId(Integer consId) {
        this.consId = consId;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments == null ? null : treatments.trim();
    }
}
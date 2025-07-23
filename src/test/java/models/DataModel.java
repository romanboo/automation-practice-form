package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class DataModel {
    private long version;
    private String mdmId;
    private int sourcechannel;
    private boolean deleted;
    private List<Integer> sourcechannels;

    @JsonProperty("last_update_dt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastUpdateDt;

    public long getVersion() { return version; }
    public void setVersion(long version) { this.version = version; }

    @JsonProperty("mdm_id")
    public String getMdmId() { return mdmId; }
    @JsonProperty("mdm_id")
    public void setMdmId(String mdmId) { this.mdmId = mdmId; }

    public int getSourcechannel() { return sourcechannel; }
    public void setSourcechannel(int sourcechannel) { this.sourcechannel = sourcechannel; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public List<Integer> getSourcechannels() { return sourcechannels; }
    public void setSourcechannels(List<Integer> sourcechannels) { this.sourcechannels = sourcechannels; }

    public LocalDateTime getLastUpdateDt() { return lastUpdateDt; }
    public void setLastUpdateDt(LocalDateTime lastUpdateDt) { this.lastUpdateDt = lastUpdateDt; }
}
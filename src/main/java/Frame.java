import java.time.Instant;

public abstract class Frame {
    protected String appVersion;
    protected FrameTypes frameType;

    protected long utc;

    public Frame(String appVersion, FrameTypes typeOfFrame, long utc) {
        setAppVersion(appVersion);
        setFrameType(typeOfFrame);
        setUtc(utc);
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public FrameTypes getFrameType() {
        return frameType;
    }

    public void setFrameType(FrameTypes frameType) {
        this.frameType = frameType;
    }

    public long getUtc() {
        return utc;
    }

    public void setUtc(long utc) {
        this.utc = utc;
    }

    public long getCurrentDateInMs(){
        return Instant.now().getEpochSecond();
    }
}

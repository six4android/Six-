package com.adjust.sdk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Locale;
import org.json.JSONObject;

public class AdjustAttribution implements Serializable {
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 1;
    public String adgroup;
    public String campaign;
    public String clickLabel;
    public String creative;
    public String network;
    public String trackerName;
    public String trackerToken;

    static {
        serialPersistentFields = new ObjectStreamField[]{new ObjectStreamField("trackerToken", String.class), new ObjectStreamField("trackerName", String.class), new ObjectStreamField("network", String.class), new ObjectStreamField("campaign", String.class), new ObjectStreamField("adgroup", String.class), new ObjectStreamField("creative", String.class), new ObjectStreamField("clickLabel", String.class)};
    }

    public static AdjustAttribution fromJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        AdjustAttribution attribution = new AdjustAttribution();
        attribution.trackerToken = jsonObject.optString("tracker_token", null);
        attribution.trackerName = jsonObject.optString("tracker_name", null);
        attribution.network = jsonObject.optString("network", null);
        attribution.campaign = jsonObject.optString("campaign", null);
        attribution.adgroup = jsonObject.optString("adgroup", null);
        attribution.creative = jsonObject.optString("creative", null);
        attribution.clickLabel = jsonObject.optString("click_label", null);
        return attribution;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        AdjustAttribution otherAttribution = (AdjustAttribution) other;
        if (!Util.equalString(this.trackerToken, otherAttribution.trackerToken)) {
            return false;
        }
        if (!Util.equalString(this.trackerName, otherAttribution.trackerName)) {
            return false;
        }
        if (!Util.equalString(this.network, otherAttribution.network)) {
            return false;
        }
        if (!Util.equalString(this.campaign, otherAttribution.campaign)) {
            return false;
        }
        if (!Util.equalString(this.adgroup, otherAttribution.adgroup)) {
            return false;
        }
        if (!Util.equalString(this.creative, otherAttribution.creative)) {
            return false;
        }
        if (Util.equalString(this.clickLabel, otherAttribution.clickLabel)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((Util.hashString(this.trackerToken) + 629) * 37) + Util.hashString(this.trackerName)) * 37) + Util.hashString(this.network)) * 37) + Util.hashString(this.campaign)) * 37) + Util.hashString(this.adgroup)) * 37) + Util.hashString(this.creative)) * 37) + Util.hashString(this.clickLabel);
    }

    public String toString() {
        return String.format(Locale.US, "tt:%s tn:%s net:%s cam:%s adg:%s cre:%s cl:%s", new Object[]{this.trackerToken, this.trackerName, this.network, this.campaign, this.adgroup, this.creative, this.clickLabel});
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
    }
}

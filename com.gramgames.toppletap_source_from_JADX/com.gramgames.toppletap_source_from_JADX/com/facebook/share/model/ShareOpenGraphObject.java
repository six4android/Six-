package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.NativeProtocol;

public final class ShareOpenGraphObject extends ShareOpenGraphValueContainer<ShareOpenGraphObject, Builder> {
    public static final Creator<ShareOpenGraphObject> CREATOR;

    static class 1 implements Creator<ShareOpenGraphObject> {
        1() {
        }

        public ShareOpenGraphObject createFromParcel(Parcel in) {
            return new ShareOpenGraphObject(in);
        }

        public ShareOpenGraphObject[] newArray(int size) {
            return new ShareOpenGraphObject[size];
        }
    }

    public static final class Builder extends com.facebook.share.model.ShareOpenGraphValueContainer.Builder<ShareOpenGraphObject, Builder> {
        public Builder() {
            putBoolean(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY, true);
        }

        public ShareOpenGraphObject build() {
            return new ShareOpenGraphObject();
        }

        Builder readFrom(Parcel parcel) {
            return (Builder) readFrom((ShareOpenGraphValueContainer) (ShareOpenGraphObject) parcel.readParcelable(ShareOpenGraphObject.class.getClassLoader()));
        }
    }

    private ShareOpenGraphObject(Builder builder) {
        super((com.facebook.share.model.ShareOpenGraphValueContainer.Builder) builder);
    }

    ShareOpenGraphObject(Parcel in) {
        super(in);
    }

    static {
        CREATOR = new 1();
    }
}

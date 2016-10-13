package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.EmailSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import org.json.simple.parser.Yytoken;

public class zzp implements Creator<SignInConfiguration> {
    static void zza(SignInConfiguration signInConfiguration, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, signInConfiguration.versionCode);
        zzb.zza(parcel, 2, signInConfiguration.zznk(), false);
        zzb.zza(parcel, 3, signInConfiguration.zzmR(), false);
        zzb.zza(parcel, 4, signInConfiguration.zznl(), i, false);
        zzb.zza(parcel, 5, signInConfiguration.zznm(), i, false);
        zzb.zza(parcel, 7, signInConfiguration.zznn(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzV(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaQ(i);
    }

    public SignInConfiguration zzV(Parcel parcel) {
        String str = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        GoogleSignInOptions googleSignInOptions = null;
        EmailSignInOptions emailSignInOptions = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    emailSignInOptions = (EmailSignInOptions) zza.zza(parcel, zzat, EmailSignInOptions.CREATOR);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    googleSignInOptions = (GoogleSignInOptions) zza.zza(parcel, zzat, GoogleSignInOptions.CREATOR);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new SignInConfiguration(i, str3, str2, emailSignInOptions, googleSignInOptions, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public SignInConfiguration[] zzaQ(int i) {
        return new SignInConfiguration[i];
    }
}

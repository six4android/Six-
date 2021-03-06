package com.google.android.gms.games.internal.constants;

import com.google.android.gms.games.internal.GamesLog;
import org.json.simple.parser.Yytoken;

public final class TurnBasedMatchTurnStatus {
    public static String zzgw(int i) {
        switch (i) {
            case Yylex.YYINITIAL /*0*/:
                return "TURN_STATUS_INVITED";
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return "TURN_STATUS_MY_TURN";
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "TURN_STATUS_THEIR_TURN";
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return "TURN_STATUS_COMPLETE";
            default:
                GamesLog.zzA("MatchTurnStatus", "Unknown match turn status: " + i);
                return "TURN_STATUS_UNKNOWN";
        }
    }
}

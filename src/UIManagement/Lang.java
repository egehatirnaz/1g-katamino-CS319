package UIManagement;
import java.lang.reflect.Array;

/*
 * Lang.java
 *
 * A class to hold English and Turkish strings and change languages within the game.
 * Translation: Allahım dünyanın en büyük ameleliği bence.
 *
 * -Ege, Dec 18 2018
 */


enum LangType{
    US,
    TR
}
public class Lang {
    LangType langtype = LangType.US;
    boolean isTurkish = false;
    public String[] strings;

    Lang(){
        changeLocale(langtype.US);
    }

    public void setTurkish(){
        isTurkish = true;
        langtype = LangType.TR;
    }

    public void setEnglish(){
        isTurkish = false;
        langtype = LangType.US;
    }

    public boolean getTurkish(){
        return isTurkish;
    }

    public void changeLocale(LangType LT){
        switch(LT) {
            case TR:
                setTurkish();
                strings = new String[]{
                        "OYUNU BAŞLAT",
                        "SIRALAMA",
                        "YAPIMCILAR",
                        "OYUNDAN ÇIK",
                        "MENÜYE GERİ DÖN",
                        "AYARLAR",
                        "Bir Nickname Gir",
                        "Kaydet",
                        "İptal",
                        "Giriş Yap",
                        "Müzik",
                        "Süre",
                        "Kullanıcı Adı"
                };

                break;
            default:
                setEnglish();
                strings = new String[]{
                        "START GAME",
                        "LEADERBOARD",
                        "CREDITS",
                        "QUIT GAME",
                        "RETURN TO MENU",
                        "SETTINGS",
                        "Enter Your Nickname",
                        "Save",
                        "Cancel",
                        "Sign In",
                        "Music",
                        "Time",
                        "Username"
                };
                break;
        }
    }
}

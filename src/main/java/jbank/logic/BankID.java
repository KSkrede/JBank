package jbank.logic;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BankID {

    // List of norwegian words from the web
    private static List<String> adjectives = Arrays.asList("UGREI", "VITTIG", "BLAUT", "VERDIG", "GYLDIG", "BRYSOM",
            "KALD", "RYDDIG", "BREIAL", "STRANDA", "SINNA", "STILIG", "SOLFYLT",
            "RUND", "TRENERT", "DIGER", "STYRKET", "PLUMP", "GOD", "VARM", "HET", "RAUS", "KRY", "FISEFIN", "KOSETE",
            "TRENT", "GNIEN", "TRO", "GREI", "VIRAL", "PLATT", "TANDER", "GALANT",
            "FARLIG", "SART", "BILLIG", "KILEN", "KNAPP", "PREGA", "NY", "TAM", "SMIDIG", "BABELSK", "DIGG", "DYRKET",
            "SVEKKET", "SMEKKER", "LUMPEN", "SYK", "FORSERT", "PEN", "SPORTY",
            "TENKSOM", "BETALT", "DYKTIG", "GLAD", "GRUNDIG", "DYRISK", "FAST", "GARVA", "VISUELL", "KARSLIG", "KNALL",
            "TVERR", "FIFFIG", "STA", "KRESEN", "HYPER", "TRYGG", "SMUL",
            "SERVIL", "GILD", "MYNDIG", "SMURT", "GRELL", "MYK", "FUL", "VETTUG", "BLEIK", "SMART", "DIGITAL", "FYSEN",
            "TOM", "SOSIAL", "MAGISK", "KUNSTIG", "SNAR", "SEGEN", "PROPER",
            "KUL", "BRA", "MEKTIG", "TRAVEL", "PREKTIG", "SMIDD", "VOKSEN", "LUNKEN", "ENSOM", "STERK", "LEGENDARISK");

    private static List<String> subjects = Arrays.asList("ALLERGI", "APP", "BAJAS", "BEDRING", "BIKINI", "BILLE", "BOA",
            "BOBIL", "BRUSK", "BRYGGE", "DAG", "DANS", "DRENG", "DRIKK",
            "DUGG", "DUGNAD", "DUKKE", "ELV", "ERT", "KOST", "FERGE", "FERIE", "FISK", "FRITID", "GJENG", "GLEDE",
            "GNU", "GRAUT", "HYTTE", "ISBIT", "JOLLE", "KARPE", "KJERRE", "KLEM",
            "KLO", "KNOLL", "KNURR", "KOE", "KONSERT", "KOS", "KRABBE", "KROPP", "KULE", "KULP", "LATHANS", "LATTE",
            "LEKE", "LOMME", "LUKSUS", "MAGE", "MAKE", "MAN", "MANN", "MORT",
            "MOTOR", "MUGGE", "MYGG", "NABO", "PARADE", "STOL", "PENGE", "PERSON", "PLANET", "POTET", "PURRE", "RAKETT",
            "REISE", "RETT", "RYE", "SALAT", "SANDAL", "SANG", "SILD",
            "SJEL", "SKUTE", "SNEKKE", "SNELLE", "SNUE", "SNAKK", "SOFTIS", "SOL", "SPEEDO", "SPRETT", "STRAND",
            "STUND", "SVANE", "SVELE", "SYKKEL", "TAMP", "TONE", "TOPP", "TOTT",
            "TROST", "TUR", "URT", "UTEDO", "VASK", "VEGETAR", "VEI", "VEKST");

    public static String getBankIDText() {
        Random rand = new Random();
        String ranAdj = adjectives.get(rand.nextInt(adjectives.size()));
        String ranSub = subjects.get(rand.nextInt(subjects.size()));

        return ranAdj + " " + ranSub;

    }

}

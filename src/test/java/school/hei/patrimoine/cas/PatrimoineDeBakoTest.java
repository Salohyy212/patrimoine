package school.hei.patrimoine.cas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class PatrimoineBakoTest {

    @Test
    void patrimoineBako_en_2025() {
        // Initialisation des valeurs
        double compteBNI = 2_000_000;
        double compteBMOI = 625_000;
        double coffreFort = 1_750_000;
        double ordinateur = 3_000_000;

        // Salaire mensuel et virements
        double salaireMensuel = 2_125_000;
        double virementEpargne = 200_000;
        double loyer = 600_000;
        double depenses = 700_000;

        // Simulation des flux mensuels pour 12 mois de 2025 (de mai à décembre)
        for (int month = 1; month <= 12; month++) {
            // Versement du salaire le 2 du mois
            compteBNI += salaireMensuel;

            // Virement d'épargne le 3 du mois
            compteBNI -= virementEpargne;
            compteBMOI += virementEpargne;

            // Dépenses mensuelles le 1er du mois
            compteBNI -= depenses;

            // Paiement du loyer le 26 du mois
            compteBNI -= loyer;
        }

        // Dépréciation de l'ordinateur (12% par an)
        ordinateur -= ordinateur * 0.12;

        // Valeur totale du patrimoine à la fin de l'année 2025
        double patrimoineTotal = compteBNI + compteBMOI + coffreFort + ordinateur;

        // Vérification de l'évolution correcte du patrimoine de Bako jusqu'à fin décembre 2025
        double expectedPatrimoineTotal = 16_915_000; // valeur attendue basée sur les calculs

        // Vérification de l'égalité du patrimoine total calculé et attendu avec une marge de tolérance
        assertEquals(expectedPatrimoineTotal, patrimoineTotal, 0.01, "Le patrimoine total de Bako à la fin de l'année doit être correct.");
    }
}

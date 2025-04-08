package school.hei.patrimoine.cas.examenPro3;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.util.Set;
import java.util.Map;

import static java.time.Month.APRIL;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

public class BakoCas extends Cas {

    public BakoCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Situation de Bako au 8 avril 2025";
    }

    @Override
    protected void init() {
    }

    @Override
    protected void suivi() {
    }

    @Override
    public Set<Possession> possessions() {
        var AU_8_AVRIL_2025 = LocalDate.of(2025, APRIL, 8);

        var compteBNI = new Compte("Compte courant BNI", AU_8_AVRIL_2025, ariary(2_000_000));
        var compteBMOI = new Compte("Compte épargne BMOI", AU_8_AVRIL_2025, ariary(625_000));
        var coffreMaison = new Compte("Coffre maison", AU_8_AVRIL_2025, ariary(1_750_000));

        var ordinateur = new Materiel(
                "Ordinateur portable",
                AU_8_AVRIL_2025,
                AU_8_AVRIL_2025,
                ariary(3_000_000),
                0.12f // 12% d'amortissement annuel
        );

        // Dépenses de vie : 700 000 Ar tous les 1er du mois
        for (int month = 4; month <= 12; month++) {
            var dateVie = LocalDate.of(2025, month, 1);
            if (!dateVie.isBefore(ajd)) {
                new FluxArgent("Dépenses de vie", compteBNI, dateVie, dateVie, 1, ariary(-700_000));
            }
        }

        // Salaire le 2 du mois
        for (int month = 4; month <= 12; month++) {
            var dateSalaire = LocalDate.of(2025, month, 2);
            if (!dateSalaire.isBefore(ajd)) {
                new FluxArgent("Salaire mensuel", compteBNI, dateSalaire, dateSalaire, 2, ariary(2_125_000));
            }
        }

        // Virement épargne le 3 du mois
// Virement épargne le 3 du mois
        for (int month = 4; month <= 12; month++) {
            var dateEpargne = LocalDate.of(2025, month, 3);
            if (!dateEpargne.isBefore(ajd)) {
                // Le virement épargne doit être effectué avant la réception sur le compte BMOI
                new FluxArgent("Virement épargne", compteBNI, dateEpargne, dateEpargne, 3, ariary(-200_000));
                new FluxArgent("Réception épargne", compteBMOI, dateEpargne, dateEpargne, 3, ariary(200_000));
            }
        }

        // Paiement du loyer tous les 26 du mois
        for (int month = 4; month <= 12; month++) {
            var dateLoyer = LocalDate.of(2025, month, 26);
            if (!dateLoyer.isBefore(ajd)) {
                new FluxArgent("Loyer colocation", compteBNI, dateLoyer, dateLoyer, 26, ariary(-600_000));
            }
        }

        return Set.of(compteBNI, compteBMOI, coffreMaison, ordinateur);
    }
}

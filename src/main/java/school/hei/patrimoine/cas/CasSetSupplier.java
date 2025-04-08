package school.hei.patrimoine.cas;

import school.hei.patrimoine.cas.examenPro3.BakoCas;
import school.hei.patrimoine.modele.Personne;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static school.hei.patrimoine.modele.Argent.ariary;

public class CasSetSupplier implements Supplier<CasSet> {
  @Override
  public CasSet get() {
    BakoCas bako = new BakoCas(
            LocalDate.of(2025, APRIL, 8),
            LocalDate.of(2025, DECEMBER,31),
            Map.of(new Personne("Bako"), 1.)
    );
    return new CasSet(Set.of(bako),ariary(8_463_342) );
  }
}

package be.vdab.fietsacademy.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class DocentTest
{
    private final static BigDecimal WEDDE = BigDecimal.valueOf(200);
    private Docent docent1;
    private Docent docent2;
    private Docent nogEensDocent1;
    private Campus campus1;

    @Before
    public void  before()
    {
        campus1 = new Campus("test", new Adres("test", "test", "test", "test"));
        docent1 = new Docent("test", "test", WEDDE, "test@fietsacademy.be", Geslacht.MAN/*, campus1*/);
        docent2 = new Docent("test2", "test2", WEDDE, "test2@fietsacademy.be", Geslacht.MAN);
        nogEensDocent1 = new Docent("test", "test", WEDDE, "test@fietsacademy.be",
                Geslacht.MAN);

    }

    @Test
    public void opslag()
    {
        docent1.opslag(BigDecimal.TEN);
        assertThat(docent1.getWedde()).isEqualByComparingTo("220");
    }

    @Test
    public void opslagMet0Mislukt()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> docent1.opslag(BigDecimal.ZERO));
    }

    @Test
    public  void nagatieveOpslagMislukt()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> docent1.opslag(BigDecimal.valueOf(-1)));
    }

    @Test
    public void eenNieuweDocentHeeftGeenBijnamen() {
        assertThat(docent1.getBijnamen()).isEmpty();
    }
    @Test
    public void bijnaamToevoegen() {
        assertThat(docent1.addBijnaam("test")).isTrue();
        assertThat(docent1.getBijnamen()).containsOnly("test");
    }
    @Test
    public void tweeKeerDezelfdeBijnaamMislukt() {
        docent1.addBijnaam("test");
        assertThat(docent1.addBijnaam("test")).isFalse();
        assertThat(docent1.getBijnamen()).containsOnly("test");
    }
    @Test
    public void nullAlsBijnaamMislukt() {
        assertThatNullPointerException().isThrownBy(() -> docent1.addBijnaam(null));
    }
    @Test
    public void eenLegeBijnaamMislukt() {
        assertThatIllegalArgumentException().isThrownBy(() -> docent1.addBijnaam(""));
    }
    @Test
    public void eenBijnaamMetEnkelSpatiesMislukt() {
        assertThatIllegalArgumentException().isThrownBy(() -> docent1.addBijnaam(" "));
    }
    @Test
    public void bijnaamVerwijderen() {
        docent1.addBijnaam("test");
        assertThat(docent1.removeBijnaam("test")).isTrue();
        assertThat(docent1.getBijnamen()).isEmpty();
    }
    @Test
    public void eenBijnaamVerwijderenDieJeNietToevoegdeMislukt() {
        docent1.addBijnaam("test");
        assertThat(docent1.removeBijnaam("test2")).isFalse();
        assertThat(docent1.getBijnamen()).containsOnly("test");
    }
    /*@Test
    public void eenNullCampusInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Docent("test", "test",
                WEDDE, "test@fietsacademy.be", Geslacht.MAN));
    }*/
    @Test
    public void meerdereDocentenKunnenTotDezelfdeCampusBehoren() {
        assertThat(campus1.add(docent1)).isTrue();
        assertThat(campus1.add(docent2)).isTrue();
    }
    @Test
    public void docentenZijnGelijkAlsHunEmailAdressenGelijkZijn() {
        assertThat(docent1).isEqualTo(nogEensDocent1);
    }
    @Test
    public void docentenZijnVerschillendAlsHunEmailAdressenVerschillen() {
        assertThat(docent1).isNotEqualTo(docent2);
    }
    @Test
    public void eenDocentVerschiltVanNull() {
        assertThat(docent1).isNotEqualTo(null);
    }
    @Test
    public void eenDocentVerschiltVanEenAnderTypeObject() {
        assertThat(docent1).isNotEqualTo("");
    }
    @Test
    public void gelijkeDocentenGevenDezelfdeHashCode() {
        assertThat(docent1).hasSameHashCodeAs(nogEensDocent1);
    }
}

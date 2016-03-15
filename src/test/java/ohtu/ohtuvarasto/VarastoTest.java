package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void VarastoLuoTyhjanTilavuudenJosTilavuusNeg() {
        Varasto varasto1 = new Varasto(-10);
        assertEquals(0, varasto1.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamVarastoLuoOikeanSaldon() {
        Varasto varasto1 = new Varasto(10,5);
        assertEquals(5, varasto1.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamVarastoLuoOikeanSaldonJosSaldoNeg() {
        Varasto varasto1 = new Varasto(10,-5);
        assertEquals(0, varasto1.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamVarastoLuoOikeanSaldonJosSaldoLiianSuuri() {
        Varasto varasto1 = new Varasto(10,15);
        assertEquals(10, varasto1.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamVarastoLuoOikeantilavuuden() {
        Varasto varasto1 = new Varasto(15,5);
        assertEquals(15, varasto1.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamVarastoLuoOikeantilavuudenJosTilavuusNeg() {
        Varasto varasto1 = new Varasto(-15,5);
        assertEquals(0, varasto1.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysnegEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-8);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLiikaaToimii() {
        varasto.lisaaVarastoon(11);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLiikaaLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(12);

        
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysnedEiPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(-8);

        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottetaanLiikaaPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(11);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottetaanLiikaaNollaaSaldon() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(11);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottetaanNegPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-11);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegEiLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-2);

        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenLiikaaTilaTyhja() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(11);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    
    
    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}
package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static  org.mockito.Mockito.*;
import java.io.IOException;
import java.util.ArrayList;
@RunWith(MockitoJUnitRunner.class)
class LaptopDaoSerzializableFileTest {
    private LaptopDao laptopDao;

    @Mock
    private LaptopDaoSerzializableFile lDaoSer;

    @BeforeEach
    void postavi()
    {
        try {
            laptopDao = new LaptopDaoSerzializableFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void dodajLaptopUFile() throws IOException {
        postavi();
        Laptop l = new Laptop();
        l.setProcesor("intel");
        l.setRam(4);
        l.setGrafickaKartica("Nvidia");
        Laptop l2 = new Laptop();
        l2.setCijena(1200.0);
        l2.setProcesor("R5-3600");
        l2.setBrend("AMD");
        //laptopDao.dodajLaptopUFile(l);
        //laptopDao.dodajLaptopUFile(l2);
        when(lDaoSer.dodajLaptopUFile(l).getProcesor()).thenReturn("intel");


    }

    @Test
    void getLaptop() {



    }

    @Test
    void napuniListu() throws IOException {
        ArrayList<Laptop> laptopi = new ArrayList<>();
        Laptop l = new Laptop();
        l.setProcesor("intel");
        l.setRam(4);
        l.setGrafickaKartica("Nvidia");
        Laptop l2 = new Laptop();
        l2.setCijena(1200.0);
        l2.setProcesor("R5-3600");
        l2.setBrend("AMD");
        LaptopDaoSerzializableFile lDao = new LaptopDaoSerzializableFile();
        laptopi.add(l);
        laptopi.add(l2);
        assertEquals(2,lDao.napuniListu(laptopi).size());
        assertEquals(true,lDao.napuniListu(laptopi).contains(l2));

    }

    @Test
    void vratiPodatkeIzDatoteke() throws IOException {
        ArrayList<Laptop> laptopi = new ArrayList<>();
        Laptop l = new Laptop();
        l.setProcesor("intel");
        l.setRam(4);
        l.setGrafickaKartica("Nvidia");
        Laptop l2 = new Laptop();
        l2.setCijena(1200.0);
        l2.setProcesor("R5-3600");
        l2.setBrend("AMD");
        LaptopDaoSerzializableFile lDao = new LaptopDaoSerzializableFile();
        lDao.dodajLaptopUFile(l);
        lDao.dodajLaptopUFile(l2);
        assertEquals(2,lDao.vratiPodatkeIzDatoteke().size());
    }
}
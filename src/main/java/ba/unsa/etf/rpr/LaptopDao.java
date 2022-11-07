package ba.unsa.etf.rpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public interface LaptopDao extends Serializable {

    Laptop dodajLaptopUListu(Laptop laptop);
    Laptop dodajLaptopUFile(Laptop laptop) throws IOException;
    Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException;
    ArrayList<Laptop> napuniListu(ArrayList<Laptop> laptopi);
    ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException;
}

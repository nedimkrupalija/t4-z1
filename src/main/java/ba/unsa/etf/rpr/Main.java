package ba.unsa.etf.rpr;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Laptop l = new Laptop();
        l.setProcesor("i7");
        l.setBrend("intel");
        l.setCijena(1200.0);
        LaptopDao lDao = new LaptopDaoJsonFile();
        lDao.dodajLaptopUFile(l);
      System.out.println(lDao.vratiPodatkeIzDatoteke());


    }
}
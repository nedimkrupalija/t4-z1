package ba.unsa.etf.rpr;

import java.io.*;
import java.text.FieldPosition;
import java.util.ArrayList;

public class LaptopDaoSerzializableFile implements LaptopDao {
    private File file;
    private ArrayList<Laptop> laptopi;


    private void sacuvaj() {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
            o.writeObject(this.laptopi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public LaptopDaoSerzializableFile() throws IOException {
        file = new File("binarna.dat");
        laptopi = new ArrayList<Laptop>();

    }

    @Override
    public Laptop dodajLaptopUListu(Laptop laptop) {
       laptopi.add(laptop);
       return laptop;
    }

    @Override
    public Laptop dodajLaptopUFile(Laptop laptop) {
        this.dodajLaptopUListu(laptop);
        sacuvaj();
        return laptop;
    }

    @Override
    public Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException {
        for(Laptop  l : laptopi)
        {
            if(procesor.equals(l.getProcesor())) return l;
        }
        throw new NeodgovarajuciProcesorException("Laptop ne postoji!");
    }

    @Override
    public ArrayList<Laptop> napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi.addAll(laptopi);
        return laptopi;
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Laptop> l = (ArrayList<Laptop>) in.readObject();
            this.laptopi = l;
        } catch (ClassNotFoundException e) {
            this.laptopi = new ArrayList<Laptop>();
            throw new RuntimeException(e);
        }
        return laptopi;
    }
}

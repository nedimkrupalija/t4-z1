package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;

public class LaptopDaoJsonFile implements LaptopDao {

    private File file;
    private ArrayList<Laptop> laptopi;
    private ObjectWriter o;
    private ObjectMapper mapper;

    private void sacuvaj() throws IOException {
        o = new ObjectMapper().writer();
        String json = o.writeValueAsString(this.laptopi);
        FileOutputStream o = new FileOutputStream(file);
        o.write(json.getBytes());
    }


    public LaptopDaoJsonFile() throws IOException {
        file = new File("laptopi.json");
        laptopi = new ArrayList<Laptop>();
        mapper = new ObjectMapper();
    }

    @Override
    public Laptop dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
        return laptop;
    }

    @Override
    public Laptop dodajLaptopUFile(Laptop laptop) throws IOException {
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
       this.laptopi = mapper.readValue(this.file, new TypeReference<ArrayList<Laptop>>() {
       });
       return laptopi;
    }
}

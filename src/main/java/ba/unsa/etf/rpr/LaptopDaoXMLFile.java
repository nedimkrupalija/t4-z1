package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

import javax.sql.rowset.spi.XmlReader;
import javax.sql.rowset.spi.XmlWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LaptopDaoXMLFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;
    private XmlMapper mapper;
    private void sacuvaj() throws IOException {
        mapper = new XmlMapper();
        String xml = mapper.writeValueAsString(this.laptopi);
        FileOutputStream out = new FileOutputStream(file);
        out.write(xml.getBytes());
    }


    public LaptopDaoXMLFile() throws IOException {
        file = new File("laptopi.json");
        laptopi = new ArrayList<Laptop>();

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
        mapper = new XmlMapper();
        this.laptopi = mapper.readValue(file, new TypeReference<ArrayList<Laptop>>() {
        });
        return laptopi;
    }

}

package hu.uni.miskolc.webalk.dao.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import hu.uni.miskolc.webalk.dao.HallgatoDAO;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoMarLetezikException;
import hu.uni.miskolc.webalk.dao.exceptions.HallgatoNemTalalhatoException;
import hu.uni.miskolc.webalk.model.Hallgato;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class HallgatoDAOMongo implements HallgatoDAO {

    private final MongoCollection<Hallgato> collection;

    public HallgatoDAOMongo(String uri, String database, String collection) {
        ConnectionString connectionString = new ConnectionString(uri);

        //Change neptunKod as _id in MongoDB, the id field can be omitted
        ClassModel<Hallgato> hallgatoClassModel = ClassModel.builder(Hallgato.class).idPropertyName("neptunKod").build();

        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).register(hallgatoClassModel).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();

        MongoClient client = MongoClients.create(clientSettings);
        this.collection = client.getDatabase(database).getCollection(collection, Hallgato.class);
    }

    @Override
    public List<Hallgato> getAllHallgato() {
        return collection.find().into(new ArrayList<>());
    }

    @Override
    public void createHallgato(Hallgato hallgato) throws HallgatoMarLetezikException {
        try {
            collection.insertOne(hallgato);
        } catch (MongoWriteException exception) {
            throw new HallgatoMarLetezikException(hallgato.getNeptunKod());
        }
    }

  /* Since id is no longer the stored as the _id, it is not working
    @Override
    public Hallgato getHallgatoById(int id) throws HallgatoNemTalalhatoException {
        Hallgato hallgato = collection.find(Filters.eq("_id", id)).first();
        if (hallgato == null){
            throw new HallgatoNemTalalhatoException(id);
        }
        return hallgato;
    }*/

    @Override
    public Hallgato getHallgatoById(String id) throws HallgatoNemTalalhatoException {
        Hallgato hallgato = collection.find(Filters.eq("_id", id)).first();
        if (hallgato == null) {
            throw new HallgatoNemTalalhatoException(id);
        }
        return hallgato;
    }

    @Override
    public void updateHallgato(Hallgato hallgato) {
        //TODO
    }

    @Override
    public void deleteHallgato(Hallgato hallgato) throws HallgatoNemTalalhatoException {
        //TODO
    }
}

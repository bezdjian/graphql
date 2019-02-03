package repository;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import helpers.Functions;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);
    private DBCollection collection;

    public PersonRepository(DBCollection collection) {
        System.out.println("PersonRepository Collection: " + collection.getName());
        this.collection = collection;
    }

    public List<Person> getAllPersons() {
        List<Person> allPersons = new ArrayList<>();
        try{
            List<DBObject> cursor = collection.find().toArray();
            for(DBObject obj : cursor){
                ObjectId id = new ObjectId(obj.get("_id").toString());
                System.out.println("Person Object: ");
                obj.keySet().forEach(f -> System.out.println("f.toString(): " + f));
                Person person = new Person(id, obj.get("name").toString(), obj.get("job").toString(), obj.get("address").toString());
                allPersons.add(person);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error when fetching person: " + e.getMessage());
        }
        return allPersons;
    }

    public void savePerson(Person person) {
        try{
            //Insert a new Link.
            ObjectId id = new ObjectId();
            person.set_id(id); // New ID for the new Person.
            logger.info("Saving Person to DB: " + person.toString());
            DBObject personObject = Functions.toDBObject(person);
            collection.insert(personObject);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("Error while saving to DB: " + e.getMessage());
        }
    }
}
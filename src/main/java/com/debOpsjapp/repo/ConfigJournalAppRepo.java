package com.debOpsjapp.repo;

import com.debOpsjapp.entity.ConfigJournalAppEntity;
import com.debOpsjapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
//controller --> service --> repo
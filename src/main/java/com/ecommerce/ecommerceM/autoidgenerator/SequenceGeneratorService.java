package com.ecommerce.ecommerceM.autoidgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import java.util.Objects;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;

	public int getSequenceNumber(String seqName) {
		Query query = new Query(Criteria.where("id").is(seqName));
		Update update = new Update().inc("seq", 1);

		DbSequence sequence = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DbSequence.class);

		return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
	}
}

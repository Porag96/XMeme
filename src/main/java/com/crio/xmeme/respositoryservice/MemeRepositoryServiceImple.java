package com.crio.xmeme.respositoryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Provider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.crio.xmeme.data.MemeEntity;
import com.crio.xmeme.dto.Meme;
import com.crio.xmeme.repository.MemeRepositiry;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class MemeRepositoryServiceImple implements MemeRepositoryService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MemeRepositiry memeRepository;

    @Autowired
    Provider<ModelMapper> modelMapperProvider;

    @Override
    public List<Meme> getMemes() {

        // Sort the documents in descending order by id
        Sort sort = Sort.by("id").descending();

        // Limiting Number of documents returns ( Latest 100)
        Pageable pageable = PageRequest.of(0, 100, sort);

        // Get a page of documents from the collection
        Page<MemeEntity> memeEntities = memeRepository.findAll(pageable);

        ModelMapper modelMapper = modelMapperProvider.get();
        List<Meme> memes = new ArrayList<>();

        for(MemeEntity memeEntity : memeEntities){
            memes.add(modelMapper.map(memeEntity, Meme.class));
        }
        return memes;
    }

    @Override
    public Integer postMeme(MemeEntity memeEntity) {
        MemeEntity meme =  mongoTemplate.save(memeEntity);
        return meme.getId();
    }

    @Override
    public Meme findMemeById(Integer id) {
        ModelMapper modelMapper = modelMapperProvider.get();
        Optional<MemeEntity> meme = memeRepository.findById(id);
        return modelMapper.map(meme, Meme.class);
    }

    @Override
    public Integer countMemes() {
        List<MemeEntity> memeEntities = memeRepository.findAll();
        return memeEntities.size();
    }

    @Override
    public Boolean isDuplicate(String name, String caption, String url) {
        // Create a query to search for a document with a matching name and address
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name).and("caption").is(caption).and("url").is(url));

        // Use the MongoTemplate to search for a matching document
        MemeEntity memeEntity = mongoTemplate.findOne(query, MemeEntity.class);

        // If a matching document is found, return true
        return memeEntity != null;
    }
    
}

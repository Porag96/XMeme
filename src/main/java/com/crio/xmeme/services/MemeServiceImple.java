package com.crio.xmeme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.xmeme.data.MemeEntity;
import com.crio.xmeme.dto.Meme;
import com.crio.xmeme.exchange.GetMemeResponse;
import com.crio.xmeme.exchange.PostMemeRequest;
import com.crio.xmeme.respositoryservice.MemeRepositoryService;

@Service
public class MemeServiceImple implements MemeService {

    @Autowired
    MemeRepositoryService memeRepositoryService;


    @Override
    public GetMemeResponse findMemes() {
        return new GetMemeResponse(memeRepositoryService.getMemes());
    }

    @Override
    public Meme findMemeById(Integer id) {
        Meme meme = null;
        try {
            meme = memeRepositoryService.findMemeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meme;
    }

    @Override
    public Integer postMeme(PostMemeRequest postMemeRequest) {
        postMemeRequest.setId(memeRepositoryService.countMemes() + 1);

        MemeEntity memeEntity = new MemeEntity(postMemeRequest.getId(), postMemeRequest.getName(), postMemeRequest.getCaption(), postMemeRequest.getUrl());
        return memeRepositoryService.postMeme(memeEntity);

    }

    @Override
    public Boolean isDuplicate(String name, String caption, String url) {
        return Boolean.TRUE.equals(memeRepositoryService.isDuplicate(name, caption, url));
    }
    
}

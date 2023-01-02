package com.crio.xmeme.respositoryservice;

import java.util.List;

import com.crio.xmeme.data.MemeEntity;
import com.crio.xmeme.dto.Meme;

public interface MemeRepositoryService {
    
    List<Meme> getMemes();
    Integer postMeme(MemeEntity memeEntity);
    Meme findMemeById(Integer id);
    Integer countMemes();
    Boolean isDuplicate(String name, String caption, String url);
}

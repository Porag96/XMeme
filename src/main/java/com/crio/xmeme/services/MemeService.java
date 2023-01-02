package com.crio.xmeme.services;

import com.crio.xmeme.dto.Meme;
import com.crio.xmeme.exchange.GetMemeResponse;
import com.crio.xmeme.exchange.PostMemeRequest;

public interface MemeService {

    GetMemeResponse findMemes();
    Meme findMemeById(Integer id);
    Integer postMeme(PostMemeRequest postMemeRequest);
    Boolean isDuplicate(String name, String caption, String url);
    
}

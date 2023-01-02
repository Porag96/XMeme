package com.crio.xmeme.controller;

import lombok.extern.log4j.Log4j2;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.xmeme.dto.Meme;
import com.crio.xmeme.exchange.GetMemeResponse;
import com.crio.xmeme.exchange.PostMemeRequest;
import com.crio.xmeme.services.MemeService;

@RestController
@RequestMapping(MemeController.MEME_API_ENDPOINT)
@Log4j2
public class MemeController {

    public static final String MEME_API_ENDPOINT = "api/v1";
    public static final String MEME_API = "/memes/";

    @Autowired
    private MemeService memeService;

    @GetMapping(MEME_API)
    public ResponseEntity<?> getMemes(){

        log.info("getMemes called");

        GetMemeResponse getMemeResponse = memeService.findMemes();
        log.info("getMemes returned {}", getMemeResponse);

        if(getMemeResponse.getMemes().size() > 0){
            return ResponseEntity.ok().body(getMemeResponse.getMemes());
        }
        return new ResponseEntity<>(getMemeResponse.getMemes(),HttpStatus.OK);
    }

    @PostMapping(MEME_API)
    @ResponseBody
    public ResponseEntity<String> postMemes(@Valid @RequestBody PostMemeRequest postMemeRequest){

        log.info("postMemes called with {}", postMemeRequest);
        String name = postMemeRequest.getName();
        String caption = postMemeRequest.getCaption();
        String url = postMemeRequest.getUrl();

        if( name != null && caption != null && url != null){
            
            if(memeService.isDuplicate(name, caption, url)){

                return new ResponseEntity<>("Duplicate Payload!!", HttpStatus.CONFLICT);
            }
                        
            Integer response = memeService.postMeme(postMemeRequest);
            return ResponseEntity.ok(String.format("{\"id\": %s}",response));
            
        }
        log.info("postMemes returned {}");

        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(MEME_API + "{id}")
    public ResponseEntity<?> getMemeById(@PathVariable("id") Integer id){

        log.info("getMemeById called with {}", id);
        Meme meme = memeService.findMemeById(id);
        log.info("getMemeById returned {}", meme);
        if(meme == null){
            return new ResponseEntity<>("Invalid Id", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(meme));
    }

    
}

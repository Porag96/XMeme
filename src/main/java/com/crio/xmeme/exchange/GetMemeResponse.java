package com.crio.xmeme.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.crio.xmeme.dto.Meme;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMemeResponse {

    private List<Meme> memes;
    
}

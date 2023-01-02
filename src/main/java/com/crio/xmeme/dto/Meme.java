package com.crio.xmeme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meme {


    private String id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String caption;
    
}

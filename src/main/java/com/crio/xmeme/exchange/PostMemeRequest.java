package com.crio.xmeme.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMemeRequest {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String caption;

    @NotNull
    private String url;
    
}

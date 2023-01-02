package com.crio.xmeme.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "memes")
public class MemeEntity {

    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String caption;

    @NotNull
    private String url;
    
}

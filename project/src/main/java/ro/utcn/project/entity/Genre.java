package ro.utcn.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private int id;
    private String name;

    public Genre(String name){
        this.name=name;
    }
}
